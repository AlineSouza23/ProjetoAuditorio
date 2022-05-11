package com.auditorio.auditorio.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	
	// CONFIGURA A CONEXÃO DA APLICAÇÃO AO BANCO DE DADOS MySQL
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSouce = new DriverManagerDataSource();
		dataSouce.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSouce.setUrl("jdbc:mysql://localhost:3307/auditorio");
		dataSouce.setUsername("root");
		dataSouce.setPassword("root");
		return dataSouce;
	}
	
	// CONFIGURA O HIBERNATE (ORM - MAPEAMENTO OBJETO RELACIONAL)
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		adapter.setShowSql(true);
		adapter.setPrepareConnection(true);
		adapter.setGenerateDdl(true);
		return adapter;
	}
	
}
