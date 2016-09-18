package com.jack.rms.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context = null;
	
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}

	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}
	
	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}
}
