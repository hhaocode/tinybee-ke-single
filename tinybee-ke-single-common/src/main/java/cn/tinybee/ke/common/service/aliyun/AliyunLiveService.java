package cn.tinybee.ke.common.service.aliyun;

import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.service.cloud.LiveService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.live.model.v20161101.DescribeLiveSnapshotConfigRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/2/4 13:29
 */
@Component("aliyunLiveService")
public class AliyunLiveService implements LiveService {

    @Autowired
    private Aliyun aliyun;

    public DefaultAcsClient init() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.aliyuncs.com"); //添加自定义endpoint
        DefaultAcsClient client = new DefaultAcsClient(profile);
        //System.setProperty("http.proxyHost", "127.0.0.1"); //用于设置代理，可用fiddler拦截查看HTTP请求，便于调试
        //System.setProperty("http.proxyPort", "8888");
        return client;
    }


    public void requestInitSample() {
        DescribeLiveSnapshotConfigRequest describeLiveSnapshotConfigRequest = new DescribeLiveSnapshotConfigRequest();
        describeLiveSnapshotConfigRequest.setDomainName("live.aliyunlive.com");
        //describeLiveSnapshotConfigRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
        //describeLiveSnapshotConfigRequest.setAcceptFormat(FormatType.JSON); //指定API返回格式
        //describeLiveSnapshotConfigRequest.setMethod(MethodType.POST); //指定请求方法
        //describeLiveSnapshotConfigRequest.setRegionId("cn-shanghai");//指定要访问的Region，仅对当前请求生效，不改变client的默认设置

        try {
            DefaultAcsClient client = init();
            HttpResponse httpResponse = client.doAction(describeLiveSnapshotConfigRequest);
            System.out.println(httpResponse.getSysUrl());
            System.out.println(new String(httpResponse.getHttpContentString()));
            //todo something
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
