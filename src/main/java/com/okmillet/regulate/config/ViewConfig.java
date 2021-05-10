package com.okmillet.regulate.config;

import java.util.Properties;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class ViewConfig {

	@Bean
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("dict_name", ApplicationContextHolder.getBean("dict_name"));
//		resultMap.put("dictionary", ApplicationContextHolder.getBean("dictionary"));
//		config.setFreemarkerVariables(resultMap);
		config.setTemplateLoaderPath("classpath:/templates/");
		Properties settings = new Properties();
		settings.put("tag_syntax","square_bracket");
		settings.put("template_update_delay","5");
		settings.put("defaultEncoding","UTF-8");
		settings.put("url_escaping_charset","UTF-8");
		settings.put("locale","zh_CN");
		settings.put("boolean_format","true,false");
		settings.put("datetime_format","yyyy-MM-dd HH:mm:ss");
		settings.put("date_format","yyyy-MM-dd");
		settings.put("time_format","HH:mm:ss");
		settings.put("number_format","0.##");
		settings.put("whitespace_stripping","true");
		settings.put("classic_compatible","true");
		config.setFreemarkerSettings(settings);
		return config;
	}

	@Bean(name = "freeMarkerViewResolver")
	public FreeMarkerViewResolver getFreeMarkerViewResolver() {
		return new SimpleFreeMarkerViewResolver();
	}
	
	@Bean
	public ErrorPageRegistrar getErrorPageRegistrar() {
		return new ErrorPageRegistrar() {

			@Override
			public void registerErrorPages(ErrorPageRegistry registry) {
				ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				registry.addErrorPages(page404);
			}
		};
	}
}
