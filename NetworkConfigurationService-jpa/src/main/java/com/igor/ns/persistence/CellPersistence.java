package com.igor.ns.persistence;

import com.igor.ns.cache.Cache;
import com.igor.ns.entity.Cell;
import com.igor.ns.event.CachePurgeEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class CellPersistence extends Cache<Cell> implements Persistence<Cell> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(final Cell persistenceObject) {
        entityManager.persist(persistenceObject);
        updateCache();
    }

    @Override
    public Cell merge(final Cell persistenceObject) {
        final Cell cell = entityManager.merge(persistenceObject);
        updateCache();
        return cell;
    }

    @Override
    protected void updateCache() {
        clear();
        final Query query = entityManager.createQuery("SELECT c FROM Cell c");
        final List<Cell> cells = query.getResultList();
        cells.forEach(cell -> add(cell.getCellId(), cell));
    }

    @Override
    public void remove(final long cellId) {
        entityManager.remove(find(cellId));
        remove(cellId);
    }

    @Override
    public Cell find(final long cellId) {
        if (contains(cellId)) {
            return get(cellId);
        } else {
            final Cell cell = entityManager.find(Cell.class, cellId);
            add(cell.getCellId(), cell);
            return cell;
        }
    }

    @Override
    public List<Cell> findAll() {
        return getAll();
    }
}
