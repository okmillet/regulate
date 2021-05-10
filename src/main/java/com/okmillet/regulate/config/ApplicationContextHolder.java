package com.okmillet.regulate.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware{
	
	private static Log log = LogFactory.getLog(ApplicationContextHolder.class);

	private static ApplicationContext applicationContext;

	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if(this.applicationContext != null) {
			throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
		}
		this.applicationContext = context;
		log.info("holded applicationContext,displayName:"+applicationContext.getDisplayName());
	}
	
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getApplicationContext().getBean(beanName, clazz);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
	
	public static void cleanHolder() {
		applicationContext = null;
	}
}