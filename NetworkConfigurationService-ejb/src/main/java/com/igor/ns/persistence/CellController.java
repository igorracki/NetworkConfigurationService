package com.igor.ns.persistence;

import com.igor.ns.ejb.CellDAO;
import com.igor.ns.entity.Cell;
import com.igor.ns.exception.NetworkConfigurationServiceException;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
public class CellController implements CellDAO {

    @Inject
    private Persistence<Cell> cellPersistence;


    @Override
    public Cell getCellById(final long cellId) {
        return cellPersistence.find(cellId);
    }

    @Override
    public List<Cell> getCells() {
        return cellPersistence.findAll();
    }

    @Override
    public void persistCell(final Cell cell) throws NetworkConfigurationServiceException {
        cellPersistence.persist(cell);
    }

    @Override
    public void delete(final long cellId) {
        cellPersistence.remove(cellId);
    }
}
