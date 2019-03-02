package lv.flyfishingteam.app.messages;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageSourceConfigurator {

	private static final int RELOAD_TIME_10_SECONDS = 10;

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(getMessageSourcePaths());
		messageSource.setCacheSeconds(RELOAD_TIME_10_SECONDS);
		return messageSource;
	}

	private String[] getMessageSourcePaths() {
		return new String[] {
				"classpath:i18n/user/validations",
				"classpath:i18n/user/messages"
		};
	}
}
