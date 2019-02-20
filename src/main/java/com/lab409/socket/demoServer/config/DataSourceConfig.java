package com.lab409.socket.demoServer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lab409.socket.demoServer.mapper")
public class DataSourceConfig {

}
