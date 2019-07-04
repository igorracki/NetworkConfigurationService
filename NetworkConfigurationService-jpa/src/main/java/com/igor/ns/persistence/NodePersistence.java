package com.igor.ns.persistence;

import com.igor.ns.cache.Cache;
import com.igor.ns.entity.Node;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class NodePersistence extends Cache<Node> implements Persistence<Node> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(final Node persistenceObject) {
        entityManager.persist(persistenceObject);
        updateCache();
    }

    @Override
    public Node merge(final Node persistenceObject) {
        final Node node = entityManager.merge(persistenceObject);
        updateCache();
        return node;
    }

    @Override
    protected void updateCache() {
        clear();
        final Query query = entityManager.createQuery("SELECT n FROM Node n");
        final List<Node> nodes = query.getResultList();
        nodes.forEach(node -> add(node.getNodeId(), node));
    }

    @Override
    public void remove(final long id) {
        entityManager.remove(find(id));
        remove(id);
    }

    @Override
    public Node find(final long nodeId) {
        if (contains(nodeId)) {
            return get(nodeId);
        } else {
            final Node node = entityManager.find(Node.class, nodeId);
            add(node.getNodeId(), node);
            return node;
        }
    }

    @Override
    public List<Node> findAll() {
        return getAll();
    }
}
