package edu.aspire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.aspire.beans.GreetingService;
import edu.aspire.beans.GreetingServiceImpl;

@Configuration
public class AppConfig {
	@Bean(name = "gs1")
	@Scope(value = "singleton")
	public GreetingService getGs1() {
		GreetingServiceImpl gs = new GreetingServiceImpl();
		gs.setGreeting("Good Morning");
		return gs;
	}

	@Bean(name = "gs2")
	public GreetingService getGs2() {
		GreetingServiceImpl gs = new GreetingServiceImpl("Good Evening");
		return gs;
	}
}