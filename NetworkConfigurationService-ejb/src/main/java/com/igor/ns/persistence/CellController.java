package com.igor.ns.persistence;

import com.igor.ns.ejb.CellDAO;
import com.igor.ns.entity.Cell;
import com.igor.ns.exception.NetworkConfigurationServiceException;
import com.igor.ns.interceptor.PersistenceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class CellController implements CellDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellController.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Cell getCellById(final long cellId) {
        return entityManager.find(Cell.class, cellId);
    }

    @Override
    public List<Cell> getCells() {
        final Query query = entityManager.createQuery("SELECT q FROM Query q");
        return query.getResultList();
    }

    @Override
    @PersistenceValidator
    public void persistCell(final Cell cell) throws NetworkConfigurationServiceException {
        entityManager.persist(cell);
    }

    @Override
    public void delete(final long cellId) {
        entityManager.remove(getCellById(cellId));
    }
}
