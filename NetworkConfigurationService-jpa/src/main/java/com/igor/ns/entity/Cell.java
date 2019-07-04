package com.igor.ns.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cells", schema = "Network")
public class Cell {

    @Id
    @Column(unique = true)
    private long cellId;
    private long range;
    private String name;

    @JsonBackReference
    @ManyToOne
//    @JoinColumn(name = "parentId")
    private Node parent;

    public Cell() {
        this(Long.MIN_VALUE, 0L, "Undefined");
    }

    public Cell(final long cellId, final long range, final String name) {
        this.cellId = cellId;
        this.range = range;
        this.name = name;
    }

    public long getCellId() {
        return cellId;
    }

    public void setCellId(final long cellId) {
        this.cellId = cellId;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node node) {
        this.parent = node;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        return cellId == ((Cell) o).getCellId();
    }
}
