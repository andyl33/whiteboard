package io.andylee.whiteboard.data.trac;

import io.andylee.whiteboard.data.MilestoneRepository;
import io.andylee.whiteboard.domain.Milestone;
import io.andylee.whiteboard.webservice.xmlrpc.calls.trac.GetAllMilestones;
import io.andylee.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import io.andylee.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the {@link io.andylee.whiteboard.data.MilestoneRepository} which retrieves Milestone information
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
