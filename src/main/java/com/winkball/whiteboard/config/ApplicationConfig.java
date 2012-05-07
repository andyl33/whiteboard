package com.winkball.whiteboard.config;

import java.net.URL;

import com.winkball.whiteboard.domain.configuration.BoardConfiguration;
import com.winkball.whiteboard.domain.configuration.TracBoardConfiguration;
import com.winkball.whiteboard.domain.configuration.TracColumnConfiguration;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Application Context Configuration.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public BoardConfiguration boardConfiguration() {

        BoardConfiguration boardConfiguration = new TracBoardConfiguration("WinkBall Ticket Board");

        TracColumnConfiguration backlog = new TracColumnConfiguration("Backlog");
        backlog.containsTicketsWith("status=new&&owner=backlog");
        backlog.setMaxTickets(25);
        backlog.acceptsTicketsWith("status=*");
        backlog.onAccept("status=new,action=new,owner=backlog");

        boardConfiguration.add(backlog);

        TracColumnConfiguration failedTesting = new TracColumnConfiguration("Failed Testing");
        failedTesting.containsTicketsWith("status=failed_testing");
        failedTesting.setMaxTickets(25);
        failedTesting.acceptsTicketsWith("status=testing");
        failedTesting.onAccept("status=failed_testing,action=reject,owner=backlog");

        boardConfiguration.add(failedTesting);

        TracColumnConfiguration inProgress = new TracColumnConfiguration("In Progress");
        inProgress.containsTicketsWith("status=assigned");
        inProgress.setMaxTickets(25);
        inProgress.acceptsTicketsWith("status=new,status=failed_testing,status=failed_review");
        inProgress.onAccept("status=assigned,action=accept,owner=*");

        boardConfiguration.add(inProgress);

        TracColumnConfiguration readyForReview = new TracColumnConfiguration("Ready for Review");
        readyForReview.containsTicketsWith("status=assigned&&owner=review_pool");
        readyForReview.setMaxTickets(25);
        readyForReview.acceptsTicketsWith("status=assigned");
        readyForReview.onAccept("status=assigned,action=accept,owner=review_pool");

        boardConfiguration.add(readyForReview);

        TracColumnConfiguration reviewing = new TracColumnConfiguration("Reviewing");
        reviewing.containsTicketsWith("status=reviewing");
        reviewing.setMaxTickets(25);
        reviewing.acceptsTicketsWith("status=assigned");
        reviewing.onAccept("status=reviewing,action=review,owner=*");

        boardConfiguration.add(reviewing);

        TracColumnConfiguration testing = new TracColumnConfiguration("Testing");
        testing.containsTicketsWith("status=testing");
        testing.setMaxTickets(25);
        testing.acceptsTicketsWith("status=reviewing");
        testing.onAccept("status=testing,action=testing,owner=release_pool");

        boardConfiguration.add(testing);

        TracColumnConfiguration readyToRelease = new TracColumnConfiguration("Ready to Release");
        readyToRelease.containsTicketsWith("status=closed");
        readyToRelease.setMaxTickets(25);
        readyToRelease.acceptsTicketsWith("status=testing");
        readyToRelease.onAccept("status=closed,action=resolve,resolution=fixed,owner=*");

        boardConfiguration.add(readyToRelease);

        return boardConfiguration;
    }


    @Bean
    public XmlRpcClient xmlRpcClient() throws Exception {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://trac.winkball.com:555/login/xmlrpc"));
        config.setBasicPassword("");
        config.setBasicUserName("");
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/views/freemarker/");
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setExposeSpringMacroHelpers(true);
        return resolver;
    }

}
