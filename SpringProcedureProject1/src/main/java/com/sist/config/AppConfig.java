package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.sist.*"})
/*
 *   DispatcherServlet ==> HandlerMapping (클래스 찾기)
 *                     ==> ViewResolver (JSP찾기)
 */
public class AppConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	/*
	 *   <bean id="viewResolver"
		       class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		       p:prefix="/main/"
		       p:suffix=".jsp"
		    />
	 */
    @Bean
    public ViewResolver viewResolver()
    {
    	InternalResourceViewResolver v=new InternalResourceViewResolver();
    	v.setPrefix("/main/");
    	v.setSuffix(".jsp");
    	return v;
    }
}





