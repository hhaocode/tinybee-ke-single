package cn.tinybee.ke.test;

import cn.tinybee.ke.PortalApplication;
import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
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
 * @date 2020/12/25 14:18
 */
@SpringBootTest(classes = PortalApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class CmsTest {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Test
    public void getDetailById () {
        CmsCourseDto detailForWeb = iCmsCourseService.getDetailForWeb(1L, 2L);
        System.out.println(detailForWeb);
    }
}
