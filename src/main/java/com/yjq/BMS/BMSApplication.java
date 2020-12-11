package com.yjq.BMS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目入口启动文件
 *
 */
@SpringBootApplication
@MapperScan("com.yjq.BMS.dao.admin")
public class BMSApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(BMSApplication.class, args);
    }
}
