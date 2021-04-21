package cn.tinybee.ke.test;

import cn.tinybee.ke.AdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class Test11 {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            sqlSessionTemplate.insert("test.test");

        }
    }
}
