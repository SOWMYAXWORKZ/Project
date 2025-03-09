package com.xworkz.medisales.configuration;


import com.xworkz.medisales.constants.ServiceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableWebMvc
@Slf4j
@ComponentScan(basePackages = "com.xworkz.medisales")
public class SpringConfiguration implements WebMvcConfigurer {

    public SpringConfiguration() {
        log.info(" invoking the SpringConfiguration ");
        System.out.println(" Default Constructor is started");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getBean() {
        log.info("invoking the LocalContainerEntityManagerBean method..");
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setPackagesToScan("com.xworkz.medisales.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(getProperties());
        return bean;
    }

    @Bean
    public DataSource getDataSource() {
        log.info("invoking the datasource method..");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/medisales");
        dataSource.setUsername("root");
        dataSource.setPassword("Xworkzodc@123Sowmya");
        return dataSource;
    }

    @Bean
    public Properties getProperties() {
        log.info("invoking the Properties method..");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }

    @Bean
    public ViewResolver viewResolver() {
        log.info("invoking in the Viewresolver method....");
        return new InternalResourceViewResolver("/", ".jsp");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        log.info("creating MultipartResolver...........");
        System.out.println("multiresolver method is created");
        return new StandardServletMultipartResolver();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getMail(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(ServiceConstant.FROM_MAIL.getPath());
        javaMailSender.setPassword(ServiceConstant.PASSWORD.getPath());
        javaMailSender.setPort(587);
        javaMailSender.setHost("smtp.gmail.com");
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return javaMailSender;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry handlerRegistry){
        handlerRegistry.addResourceHandler("/URLToReachStaticFolder/**").
                addResourceLocations("/static/");
    }

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("template/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        return springResourceTemplateResolver;
    }

    @Bean
    public TemplateEngine templateEngine(){
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(springResourceTemplateResolver());
        return templateEngine;

    }

}
