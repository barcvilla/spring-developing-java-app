/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.webstore.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author PC
 */
@Configuration
@ComponentScan("com.packt.webstore")
public class RootApplicationContextConfig {
    
    @Bean
    public DataSource dataSource()
    {
        BasicDataSource db = new BasicDataSource();
        db.setDriverClassName("com.mysql.jdbc.Driver");
        db.setUrl("jdbc:mysql://localhost:3306/db_springboot_backend?useSSL=false");
        db.setUsername("root");
        db.setPassword("admin");
        
        return db;
    }
    
    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate()
    {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
