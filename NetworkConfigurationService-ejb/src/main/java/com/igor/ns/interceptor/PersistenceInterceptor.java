package com.igor.ns.interceptor;

import com.igor.ns.entity.Cell;
import com.igor.ns.entity.Node;
import com.igor.ns.exception.NetworkConfigurationServiceException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static java.lang.String.format;

@PersistenceValidator
@Interceptor
public class PersistenceInterceptor {

    @PersistenceContext
    private EntityManager entityManager;

    @AroundInvoke
    public Object validateUniqueId(final InvocationContext ctx) throws Exception {
        final Object object = ctx.getParameters()[0];
        boolean isNodeUnique = true;
        long id = 0;

        if (object instanceof Node) {
            final Node node = (Node) object;
            if (node.getCells() != null && !node.getCells().isEmpty()) {
                throw new NetworkConfigurationServiceException("Please create the cells first (if required) and assign their IDs to the node, after the node is created!");
            }
            id = node.getNodeId();
            isNodeUnique = runQuery("SELECT n FROM Node n WHERE n.nodeId = :id", id);
        } else if (object instanceof Cell) {
            id = ((Cell) object).getCellId();
            isNodeUnique = runQuery("SELECT c FROM Cell c WHERE c.cellId = :id", id);
        }

        if (!isNodeUnique) {
            throw new NetworkConfigurationServiceException(format("The ID %s for %s is already used!", id, object.getClass().getSimpleName()));
        }

        return ctx.proceed();
    }

    private boolean runQuery(final String queryString, final long id) {
        final Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.getResultList().isEmpty();
    }
}
