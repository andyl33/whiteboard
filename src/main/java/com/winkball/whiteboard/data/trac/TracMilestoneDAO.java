package com.winkball.whiteboard.data.trac;

import com.winkball.whiteboard.data.MilestoneDAO;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.GetAllMilestones;
import com.winkball.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of the {@link MilestoneDAO} which retrieves Milestone information
 * from a remote Trac installation using XML-RPC
 */
@Component
public class TracMilestoneDAO implements MilestoneDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TracMilestoneDAO.class);

    private XmlRpcTemplate xmlRpcTemplate;

    @Autowired
    public TracMilestoneDAO(XmlRpcTemplate xmlRpcTemplate) {
        this.xmlRpcTemplate = xmlRpcTemplate;
    }

    public List<Milestone> findAll() {
        List<Milestone> milestones = null;
        try {
            xmlRpcTemplate.callForList(new GetAllMilestones());
        } catch (RemoteProcedureCallException rpce) {
           LOGGER.info("No Milestones found.");
        }
        return milestones;
    }
}
