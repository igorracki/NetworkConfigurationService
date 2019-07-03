package com.igor.ns.persistence;

import com.igor.ns.ejb.NodeDAO;
import com.igor.ns.entity.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class NodeController implements NodeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Node getNodeById(final long id) {
        return entityManager.find(Node.class, id);
    }

    @Override
    public List<Node> getNodes() {
        final Query query = entityManager.createQuery("SELECT n FROM Node n");
        return query.getResultList();
    }

    @Override
    public boolean persistNode(final Node node) {
        final Query query = entityManager.createQuery("SELECT n FROM Node n WHERE n.id = :id");
        query.setParameter("id", node.getNodeId());

        if (query.getResultList().isEmpty()) {
            LOGGER.info("Creating the node...");
            entityManager.persist(node);
            return true;
        } else {
            LOGGER.warn("A Node with ID: {} already exists!", node.getNodeId());
            return false;
        }
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getNodeById(id));
    }
}
