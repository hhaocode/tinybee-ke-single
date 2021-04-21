package cn.tinybee.ke.test;

import cn.tinybee.ke.AdminApplication;
import cn.tinybee.ke.common.service.cloud.VodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.ke.test
 * @Description : TODO
 * @Create on : 2020/6/28 12:07
 **/

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class AliyunVodTest {

//    @Autowired
//    private AliyunVodService aliyunVodService;

    @Autowired
    private VodService vodService;

    @Test
    public void testVod() throws Exception {

//        aliyunVodService.upload();
//        aliyunService.createUploadVideo();
    }

    @Test
    public void testGetVod() throws Exception {
//        4d50962627934cba8497ebb8153d5ded
        //69c56ad723cd421bb31083c2c1551d36
//        GetVideoPlayAuthResponse videoPlayAuth = aliyunVodService.getVideoPlayAuth("4d50962627934cba8497ebb8153d5ded");
//        System.out.println(videoPlayAuth);

//        GetVideoPlayAuthResponse videoPlayAuth = vodService.getVideoPlayAuth("4d50962627934cba8497ebb8153d5ded");
//        System.out.println(videoPlayAuth);
        vodService.getVideoPlayUrl("d8f8f67594a2468ab9f1db7d988d2616");
    }
}
