package com.igor.ns.ejb;

import com.igor.ns.entity.Cell;
import com.igor.ns.exception.NetworkConfigurationServiceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CellDAO {

    Cell getCellById(final long cellId);

    List<Cell> getCells();

    void persistCell(final Cell cell) throws NetworkConfigurationServiceException;

    void delete(final long cellId);
}
