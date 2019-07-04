package com.igor.ns.persistence;

import com.igor.ns.ejb.CellDAO;
import com.igor.ns.ejb.NodeDAO;
import com.igor.ns.entity.Cell;
import com.igor.ns.entity.Node;
import com.igor.ns.exception.NetworkConfigurationServiceException;
import com.igor.ns.interceptor.PersistenceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Singleton
public class NodeController implements NodeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CellDAO cellDAO;

    @Override
    public Node getNodeById(final long nodeId) {
        return entityManager.find(Node.class, nodeId);
    }

    @Override
    public List<Node> getNodes() {
        final Query query = entityManager.createQuery("SELECT n FROM Node n");
        final List<Node> nodes = query.getResultList();
//        nodes.forEach(node -> LOGGER.info("Node getCells size {}", node.getCells().size()));
        return nodes;
    }

    @Override
    @PersistenceValidator
    public void persistNode(final Node node) throws NetworkConfigurationServiceException {
        entityManager.persist(node);
    }

    @Override
    public void delete(final long nodeId) {
        entityManager.remove(getNodeById(nodeId));
    }

    @Override
    public void addCellsToNode(final long nodeId, final long[] cellIds) {
        final Node node = getNodeById(nodeId);
        for (final long id : cellIds) {
            final Cell cell = cellDAO.getCellById(id);
            cell.setParent(node);
            node.addCell(cell);
        }
        entityManager.merge(node);
//        LOGGER.info("Node ID is: {}", nodeId);
//        final Node node = getNodeById(nodeId);
//        LOGGER.info("Node is: {}", node);
//        final Set<Cell> cells = node.getCells();
//        node.setCells(null);
//        entityManager.detach(node);
//
//        for (final long id : cellIds) {
//            final Cell cell = cellDAO.getCellById(id);
//            entityManager.detach(cell);
////            cell.setParent(node);
////            node.addCell(new Cell(cell.getCellId() + 1234, cell.getRange(), cell.getName()));
////            node.addCell(new Cell(111, 2020, "qweqweqwe"));
//            node.addCell(cell);
//            entityManager.merge(cell);
//            LOGGER.info("Added cell to node!");
//        }
//        cells.forEach(node::addCell);
//        entityManager.merge(node);
    }
}
