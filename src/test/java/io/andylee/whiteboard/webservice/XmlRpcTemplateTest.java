package io.andylee.whiteboard.webservice;

import io.andylee.whiteboard.data.TicketRepository;
import io.andylee.whiteboard.data.trac.TracTicketRepository;
import io.andylee.whiteboard.domain.Milestone;
import io.andylee.whiteboard.webservice.xmlrpc.security.SslInitializer;
import io.andylee.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.junit.Assert;
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
        TicketRepository repository = new TracTicketRepository(template);
        List result = repository.find(new Milestone("WinkBall 1.8.0"));
        Assert.assertNotNull(result);
    }
}
