package com.okmillet.regulate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.vendor.MySqlValidConnectionChecker;

@Configuration
public class TxDataSourceConfig {

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
	    DruidDataSource ds = (DruidDataSource) dataSource;
	    if(ds.getValidConnectionChecker() instanceof MySqlValidConnectionChecker) {
	        ((MySqlValidConnectionChecker)ds.getValidConnectionChecker()).setUsePingMethod(false);
	    }
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public TransactionInterceptor txAdvice(TransactionManager transactionManager) {
		NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
		Properties attributes = new Properties();
		attributes.setProperty("*MultiOpr", "PROPAGATION_REQUIRED,-Exception");
		attributes.setProperty("*", "PROPAGATION_SUPPORTS,readOnly");
		tas.setProperties(attributes);
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, tas);
		return txAdvice;
	}
	
	@Bean
	public DefaultPointcutAdvisor serviceAdvisor(TransactionInterceptor txAdvice) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.*..*.service.impl.*ServiceImpl.*(..))");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(pointcut);
		advisor.setAdvice(txAdvice);
		return advisor;
	}
	
	@Bean
	public DefaultPointcutAdvisor baseAdvisor(TransactionInterceptor txAdvice) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.okmillet.regulate.framework.BaseService.*(..))");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(pointcut);
		advisor.setAdvice(txAdvice);
		return advisor;
	}
}