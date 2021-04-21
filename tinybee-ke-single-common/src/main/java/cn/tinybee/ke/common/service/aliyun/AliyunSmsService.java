package cn.tinybee.ke.common.service.aliyun;

import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.service.cloud.SmsService;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/8 10:12
 */
@Component("aliyunSmsService")
public class AliyunSmsService implements SmsService {

    @Autowired
    private Aliyun aliyun;

    @Override
    public void sendSms(String target, String code, String param) {
        DefaultProfile profile = DefaultProfile.getProfile(aliyun.getRegionId(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", aliyun.getRegionId());
        request.putQueryParameter("PhoneNumbers", target);
        request.putQueryParameter("SignName", aliyun.getSignName());
        request.putQueryParameter("TemplateCode", aliyun.getTemplateCode());
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
