package com.okmillet.regulate.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class TxDataSourceConfig {

	@Value("${datasource.url}")
	private String url;
	@Value("${datasource.username}")
	private String username;
	@Value("${datasource.password}")
	private String password;
	@Value("${datasource.driverClassName}")
	private String driverClassName;
	@Value("${datasource.filters}")
	private String filters;
	@Value("${datasource.maxActive}")
	private Integer maxActive;
	@Value("${datasource.initialSize}")
	private Integer initialSize;
	@Value("${datasource.maxWait}")
	private Integer maxWait;
	@Value("${datasource.minIdle}")
	private Integer minIdle;
	@Value("${datasource.timeBetweenEvictionRunsMillis}")
	private Integer timeBetweenEvictionRunsMillis;
	@Value("${datasource.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;
	@Value("${datasource.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${datasource.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${datasource.testOnReturn}")
	private boolean testOnReturn;
	@Value("${datasource.maxOpenPreparedStatements}")
	private Integer maxOpenPreparedStatements;
	@Value("${datasource.removeAbandoned}")
	private boolean removeAbandoned;
	@Value("${datasource.removeAbandonedTimeout}")
	private Integer removeAbandonedTimeout;
	@Value("${datasource.logAbandoned}")
	private boolean logAbandoned;
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
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
	
	@Bean
	public DataSource getDataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDriverClassName(driverClassName);
		ds.setFilters(filters);
		ds.setMaxActive(maxActive);
		ds.setInitialSize(initialSize);
		ds.setMaxWait(maxWait);
		ds.setMinIdle(minIdle);
		ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		ds.setTestWhileIdle(testWhileIdle);
		ds.setTestOnBorrow(testOnBorrow);
		ds.setTestOnReturn(testOnReturn);
		ds.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
		ds.setRemoveAbandoned(removeAbandoned);
		ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		ds.setLogAbandoned(logAbandoned);
		return ds;
	}
}