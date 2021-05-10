package com.okmillet.regulate.config;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * ViewResolver for SimpleFreeMarkerView
 * 
 * Override buildView, if viewName start with / , then ignore prefix.
 */
public class SimpleFreeMarkerViewResolver extends FreeMarkerViewResolver {
	
	/**
	 * Set default viewClass
	 */
	public SimpleFreeMarkerViewResolver() {
		setViewClass(SimpleFreeMarkerView.class);
		setContentType("text/html; charset=UTF-8");
		setExposeRequestAttributes(false);
		setExposeSessionAttributes(false);
		setExposeSpringMacroHelpers(false);
		setSuffix("");
	}

	/**
	 * if viewName start with / , then ignore prefix.
	 */
//	@Override
//	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
//		AbstractUrlBasedView view = super.buildView(viewName);
//		// start with / ignore prefix
//		if (!viewName.endsWith(".html")) {
//			view.setUrl(viewName + ".html");
//		}
//		return view;
//	}
}
