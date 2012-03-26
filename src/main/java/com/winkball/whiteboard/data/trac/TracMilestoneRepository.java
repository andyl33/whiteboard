package com.winkball.whiteboard.data.trac;

import com.winkball.whiteboard.data.MilestoneRepository;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.GetAllMilestones;
import com.winkball.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the {@link com.winkball.whiteboard.data.MilestoneRepository} which retrieves Milestone information
 * from a remote Trac installation using XML-RPC
 */
@Repository(value="TracMilestoneRepository")
public class TracMilestoneRepository implements MilestoneRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TracMilestoneRepository.class);

    private XmlRpcTemplate xmlRpcTemplate;

    @Autowired
    public TracMilestoneRepository(XmlRpcTemplate xmlRpcTemplate) {
        this.xmlRpcTemplate = xmlRpcTemplate;
    }

    public List<Milestone> findAll() {
        List<Milestone> milestones = null;
        try {
            milestones = xmlRpcTemplate.callForList(new GetAllMilestones());
        } catch (RemoteProcedureCallException rpce) {
           LOGGER.info("No Milestones found.");
        }
        return milestones;
    }
}
