package com.winkball.whiteboard.webservice;

import com.winkball.whiteboard.data.TicketDAO;
import com.winkball.whiteboard.data.trac.TracTicketDAO;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.webservice.xmlrpc.security.SslInitializer;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import junit.framework.Assert;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;

/**
 * Tests for the {@link XmlRpcTemplate}
 */
public class XmlRpcTemplateTest {

    private XmlRpcTemplate template;

    @Before
    public void init() throws Exception {
        SslInitializer initializer = new SslInitializer();
        initializer.init();

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://trac.winkball.com:555/login/xmlrpc"));
        config.setBasicPassword(""); //TODO change to whiteboard trac account!
        config.setBasicUserName(""); //TODO change to whiteboard trac account!
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        template = new XmlRpcTemplate(client);
    }

    @Test
    public void testStuff() throws Exception {
        TicketDAO repository = new TracTicketDAO(template);
        List result = repository.find(new Milestone("WinkBall 1.8.0"));
        Assert.assertNotNull(result);
    }
}
