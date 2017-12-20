//package com.boot;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class JPAPersistenceUnitConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5433/spring_boot_project");
//        dataSource.setUsername("traininguser");
//        dataSource.setPassword("trainingpassword");
//
//        return dataSource;
//    }
//
////    @Bean
////    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
////        final LocalEntityManagerFactoryBean em = new LocalEntityManagerFactoryBean();
////        em.setPersistenceUnitName("customPU");
////        return em;
////    }
//}