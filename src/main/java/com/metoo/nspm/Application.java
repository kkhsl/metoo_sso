package com.metoo.nspm;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroBeanAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCasClient
@ServletComponentScan(basePackages ={ "com.metoo.nspm"})
@EnableScheduling // 开启定时任务（启动类增加该注解，使项目启动后执行定时任务）
@MapperScan("com.metoo.nspm.core.mapper")
//@SpringBootApplication
@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class,
        ShiroAutoConfiguration.class, ShiroBeanAutoConfiguration.class})

public class Application {

    public static void main(String[] args) {
        Long time=System.currentTimeMillis();
        SpringApplication.run(Application.class);
        System.out.println("===应用启动耗时："+(System.currentTimeMillis()-time)+"===");
    }

}
