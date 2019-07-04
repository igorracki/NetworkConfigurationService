package com.igor.ns.persistence;

import com.igor.ns.ejb.CellDAO;
import com.igor.ns.ejb.NodeDAO;
import com.igor.ns.entity.Cell;
import com.igor.ns.entity.Node;
import com.igor.ns.exception.NetworkConfigurationServiceException;
import com.igor.ns.interceptor.PersistenceValidator;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class NodeController implements NodeDAO {

    @Inject
    private Persistence<Node> nodePersistence;

    @Inject
    private CellDAO cellDAO;

    @Override
    public Node getNodeById(final long nodeId) {
        return nodePersistence.find(nodeId);
    }

    @Override
    public List<Node> getNodes() {
        return nodePersistence.findAll();
    }

    @Override
    @PersistenceValidator
    public void persistNode(final Node node) throws NetworkConfigurationServiceException {
        nodePersistence.persist(node);
    }

    @Override
    public void delete(final long nodeId) {
        nodePersistence.remove(nodeId);
    }

    @Override
    public void addCellsToNode(final long nodeId, final long[] cellIds) {
        final Node node = getNodeById(nodeId);
        for (final long id : cellIds) {
            final Cell cell = cellDAO.getCellById(id);
            cell.setParent(node);
            node.addCell(cell);
        }
        nodePersistence.merge(node);
    }
}
