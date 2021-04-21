package cn.tinybee.ke.biz.cloud.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpClientConfig;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;

//Current KMS SDK version:2016-01-20
import com.aliyuncs.kms.model.v20160120.CreateKeyRequest;
import com.aliyuncs.kms.model.v20160120.CreateKeyResponse;
import com.aliyuncs.kms.model.v20160120.DecryptRequest;
import com.aliyuncs.kms.model.v20160120.DecryptResponse;
import com.aliyuncs.kms.model.v20160120.DescribeKeyRequest;
import com.aliyuncs.kms.model.v20160120.DescribeKeyResponse;
import com.aliyuncs.kms.model.v20160120.EncryptRequest;
import com.aliyuncs.kms.model.v20160120.EncryptResponse;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyRequest;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyResponse;
import com.aliyuncs.kms.model.v20160120.ListKeysRequest;
import com.aliyuncs.kms.model.v20160120.ListKeysResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class KmsClient
{
    private DefaultAcsClient kmsClient;

    /**
     * 创建KmsClient，只需要指定regionId，SDK自动配置相应地域的公网Endpoint
     */
    public static KmsClient getClientForPublicEndpoint(String regionId, String accessKeyId, String accessKeySecret) {
        /**
         * Construct an Aliyun Client:
         * Set RegionId, AccessKeyId and AccessKeySecret
         */
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return new KmsClient(client);
    }

    /**
     * 创建KmsClient，使用自定义Endpoint，通常用于访问VPC Endpoint
     */
    public static KmsClient getClientForVpcEndpoint(String regionId, String accessKeyId, String accessKeySecret, String endpoint) {
        //添加自定义Endpoint
        DefaultProfile.addEndpoint(regionId, "kms", endpoint);

        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        HttpClientConfig clientConfig = HttpClientConfig.getDefault();
        profile.setHttpClientConfig(clientConfig);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return new KmsClient(client);
    }

    private KmsClient(DefaultAcsClient acsClient) {
        this.kmsClient = acsClient;
    }

    public CreateKeyResponse CreateKey(String keyDesc, String keyUsage) throws ClientException {
        final CreateKeyRequest ckReq = new CreateKeyRequest();

        ckReq.setProtocol(ProtocolType.HTTPS);
        ckReq.setAcceptFormat(FormatType.JSON);
        ckReq.setMethod(MethodType.POST);
        ckReq.setDescription(keyDesc);
        ckReq.setKeyUsage(keyUsage);

        final CreateKeyResponse response = kmsClient.getAcsResponse(ckReq);
        return response;
    }

    public DescribeKeyResponse DescribeKey(String keyId) throws ClientException {
        final DescribeKeyRequest decKeyReq = new DescribeKeyRequest();

        decKeyReq.setProtocol(ProtocolType.HTTPS);
        decKeyReq.setAcceptFormat(FormatType.JSON);
        decKeyReq.setMethod(MethodType.POST);
        decKeyReq.setKeyId(keyId);

        final DescribeKeyResponse decKeyRes = kmsClient.getAcsResponse(decKeyReq);
        return decKeyRes;
    }

    public ListKeysResponse ListKey(int pageNumber, int pageSize) throws ClientException {
        final ListKeysRequest listKeysReq = new ListKeysRequest();

        listKeysReq.setProtocol(ProtocolType.HTTPS);
        listKeysReq.setAcceptFormat(FormatType.JSON);
        listKeysReq.setMethod(MethodType.POST);
        listKeysReq.setPageNumber(pageNumber);
        listKeysReq.setPageSize(pageSize);

        final ListKeysResponse listKeysRes = kmsClient.getAcsResponse(listKeysReq);
        return listKeysRes;
    }

    public GenerateDataKeyResponse GenerateDataKey(String keyId, String keyDesc, int numOfBytes) throws ClientException {
        final  GenerateDataKeyRequest genDKReq = new GenerateDataKeyRequest();

        genDKReq.setProtocol(ProtocolType.HTTPS);
        genDKReq.setAcceptFormat(FormatType.JSON);
        genDKReq.setMethod(MethodType.POST);

        /**
         * Set parameter according to KMS openAPI document:
         * 1.KeyId
         * 2.KeyDescription
         * 3.NumberOfBytes
         */
        genDKReq.setKeySpec(keyDesc);
        genDKReq.setKeyId(keyId);
        genDKReq.setNumberOfBytes(numOfBytes);

        final GenerateDataKeyResponse genDKRes = kmsClient.getAcsResponse(genDKReq);
        return genDKRes;
    }

    public EncryptResponse Encrypt(String keyId, String plainText) throws ClientException {
        final EncryptRequest encReq = new EncryptRequest();

        encReq.setProtocol(ProtocolType.HTTPS);
        encReq.setAcceptFormat(FormatType.JSON);
        encReq.setMethod(MethodType.POST);
        encReq.setKeyId(keyId);
        encReq.setPlaintext(plainText);
        final EncryptResponse encResponse = kmsClient.getAcsResponse(encReq);
        return encResponse;
    }


    public DecryptResponse Decrypt(String cipherBlob) throws ClientException {
        final DecryptRequest decReq = new DecryptRequest();

        decReq.setProtocol(ProtocolType.HTTPS);
        decReq.setAcceptFormat(FormatType.JSON);
        decReq.setMethod(MethodType.POST);
        decReq.setCiphertextBlob(cipherBlob);
        final DecryptResponse decResponse = kmsClient.getAcsResponse(decReq);
        return decResponse;
    }
}