package cn.tinybee.ke.common.config;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/28
 */
@Configuration
public class DozerConfig {

    @Bean
    public Mapper mapper() {
//        List<String> mappingFileUrls = Lists.newArrayList("dozer-custom-convert.xml");
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
//        mapper.setMappingFiles(mappingFileUrls);
        return mapper;
//        return new DozerBeanMapper();
    }

}

