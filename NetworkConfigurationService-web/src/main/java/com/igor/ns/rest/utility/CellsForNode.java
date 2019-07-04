package com.igor.ns.rest.utility;

public class CellsForNode {

    private long nodeId;
    private long[] cellIds;

    public CellsForNode() {

    }

    public CellsForNode(final long nodeId, final long[] cellIds) {
        this.nodeId = nodeId;
        this.cellIds = cellIds;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(final long nodeId) {
        this.nodeId = nodeId;
    }

    public long[] getCellIds() {
        return cellIds;
    }

    public void setCellIds(final long[] cellIds) {
        this.cellIds = cellIds;
    }
}
