package com.igor.ns.ejb;

import com.igor.ns.entity.Node;
import com.igor.ns.exception.NetworkConfigurationServiceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface NodeDAO {

    Node getNodeById(final long nodeId);

    List<Node> getNodes();

    void persistNode(final Node node) throws NetworkConfigurationServiceException;

    void delete(final long nodeId);

    void addCellsToNode(final long nodeId, final long[] cellIds);
}
