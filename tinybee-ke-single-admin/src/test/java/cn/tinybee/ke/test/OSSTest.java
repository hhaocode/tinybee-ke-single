package cn.tinybee.ke.test;

import cn.tinybee.ke.AdminApplication;
import cn.tinybee.ke.common.service.cloud.OSSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/8 10:30
 */

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class OSSTest {

    @Autowired
    private OSSService ossService;


    @Test
    public void test () throws Exception {
//        ossHandler.uploadFile(new File("D:\\resource\\os\\arcus\\material\\main_component_subtitle_home.png"), "images");

        ossService.getMetaInfo();
    }
}
