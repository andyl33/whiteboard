package com.winkball.whiteboard.config;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.net.URL;

/**
 * Application Context Configuration.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public XmlRpcClient xmlRpcClient() throws Exception {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://trac.winkball.com:555/login/xmlrpc"));
        config.setBasicPassword("GitHub!10");
        config.setBasicUserName("andy.lee");
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setResourceLoaderPath("/WEB-INF/views/velocity/");
        return configurer;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".vm");
        resolver.setExposeSpringMacroHelpers(true);
        return resolver;
    }

}
