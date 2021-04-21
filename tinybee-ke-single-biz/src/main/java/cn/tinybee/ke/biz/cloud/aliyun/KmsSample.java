package cn.tinybee.ke.biz.cloud.aliyun;

import com.aliyuncs.exceptions.ClientException;

import java.util.*;
import com.aliyuncs.kms.model.v20160120.*;
import com.aliyuncs.kms.model.v20160120.ListKeysResponse.Key;

public class KmsSample {

    public static void main(String[] args) {
        String accessKeyId = System.getenv("ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("ACCESS_KEY_SECRET");

        KmsClient kmsClient = KmsClient.getClientForPublicEndpoint("cn-hangzhou", accessKeyId, accessKeySecret);
        //KmsClient kmsClient = KmsClient.getClientForVpcEndpoint("cn-hangzhou-vpc", accessKeyId, accessKeySecret, "kms-vpc.cn-hangzhou.aliyuncs.com");
        String keyId = null;
        String plainText = "hello world";
        String cipherBlob = null;

        /*List all MasterKeys in your account*/
        try {
            final ListKeysResponse listKeysRes = kmsClient.ListKey(1, 100);

            /**
             * Parse response and do more further
             */
            System.out.println("TotalCount: " + listKeysRes.getTotalCount());
            System.out.println("PageNumber: " + listKeysRes.getPageNumber());
            System.out.println("PageSize: " + listKeysRes.getPageSize());

            List<Key> keys = listKeysRes.getKeys();
            Iterator<Key> iterator = keys.iterator();

            while (iterator.hasNext()) {
                keyId = iterator.next().getKeyId();
                System.out.println("KeyId: " + keyId);
            }

            System.out.println("List All MasterKeys success!\n");
        } catch (ClientException eResponse) {
            System.out.println("Failed.");
            System.out.println("Error code: " + eResponse.getErrCode());
            System.out.println("Error message: " + eResponse.getErrMsg());
        }


        /*Describe the Key */
        try {
            final DescribeKeyResponse decKeyRes = kmsClient.DescribeKey(keyId);

            /**
             * Parse response and do more further
             */
            System.out.println("DescribeKey Response: ");
            DescribeKeyResponse.KeyMetadata meta = decKeyRes.getKeyMetadata();

            System.out.println("KeyId: " + meta.getKeyId());
            System.out.println("Description: " + meta.getDescription());
            System.out.println("KeyState: " + meta.getKeyState());
            System.out.println("KeyUsage: " + meta.getKeyUsage());

            System.out.println("===========================================");
            System.out.println("Describe the MasterKey success!");
            System.out.println("===========================================\n");
        } catch (ClientException eResponse) {
            System.out.println("Failed.");
            System.out.println("Error code: " + eResponse.getErrCode());
            System.out.println("Error message: " + eResponse.getErrMsg());
        }

        /*Generate DataKey*/
        /**
         * Request and got response
         */
        try {
            final GenerateDataKeyResponse genDKResponse = kmsClient.GenerateDataKey(keyId, "AES_256", 64);

            /**
             * Parse response and do more further
             */
            System.out.println("CiphertextBlob: " + genDKResponse.getCiphertextBlob());
            System.out.println("KeyId: " + genDKResponse.getKeyId());
            System.out.println("Plaintext: " + genDKResponse.getPlaintext());

            System.out.println("===========================================");
            System.out.println("Generate DataKey success!");
            System.out.println("===========================================\n");
        } catch (ClientException eResponse) {
            System.out.println("Failed.");
            System.out.println("Error code: " + eResponse.getErrCode());
            System.out.println("Error message: " + eResponse.getErrMsg());
        }

        /**
         * Encrypt the plain text and got a cipher one
         */
        try {
            EncryptResponse encResponse = kmsClient.Encrypt(keyId, plainText);

            cipherBlob = encResponse.getCiphertextBlob();
            System.out.println("CiphertextBlob: " + cipherBlob);
            System.out.println("KeyId: " + encResponse.getKeyId());

            System.out.println("===========================================");
            System.out.println("Encrypt the plain text success!");
            System.out.println("===========================================\n");
        } catch (ClientException eResponse) {
            System.out.println("Failed.");
            System.out.println("Error code: " + eResponse.getErrCode());
            System.out.println("Error message: " + eResponse.getErrMsg());
        }

        /**
         * Decrypt the cipher text and verify result with original plain text.
         */
        try {
            DecryptResponse decResponse = kmsClient.Decrypt(cipherBlob);

            System.out.println("Plaintext: " + decResponse.getPlaintext());
            String verifyPlainText = decResponse.getPlaintext();
            int isMatch = verifyPlainText.compareTo(plainText);
            System.out.println("KeyId: " + decResponse.getKeyId());
            System.out.println("===========================================");
            System.out.printf("Decrypt the cipher text success, result " + (isMatch == 0 ? "match" : "mismatch" + "\n"));
            System.out.println("===========================================\n");
        } catch (ClientException eResponse) {
            System.out.println("Failed.");
            System.out.println("Error code: " + eResponse.getErrCode());
            System.out.println("Error message: " + eResponse.getErrMsg());
        }
    }

}
