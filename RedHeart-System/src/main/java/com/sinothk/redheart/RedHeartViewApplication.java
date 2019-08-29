package com.sinothk.redheart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // api
@MapperScan("com.sinothk.redheart.repository")
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class) // https://doc.xiaominfo.com/guide/useful.html#注意事项
public class RedHeartViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedHeartViewApplication.class, args);
    }

}
