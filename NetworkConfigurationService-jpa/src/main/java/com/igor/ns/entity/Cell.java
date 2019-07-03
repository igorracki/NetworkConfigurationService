package com.igor.ns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cells", schema = "Network")
public class Cell {

    @Id
    @Column(unique = true)
    private long nodeId;
    private long range;
    private String name;

    public Cell() {
        this(Long.MIN_VALUE, 0L, "Undefined");
    }

    public Cell(final long nodeId, final long range, final String name) {
        this.nodeId = nodeId;
        this.range = range;
        this.name = name;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(final long nodeId) {
        this.nodeId = nodeId;
    }

    public long getRange() {
        return range;
    }

    public void setRange(final long range) {
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
