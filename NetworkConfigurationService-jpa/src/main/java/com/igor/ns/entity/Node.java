package com.igor.ns.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Nodes", schema = "Network")
public class Node {

    @Id
    @Column(unique = true)
    private long nodeId;
    private String name;
    private String location;
    private double latitude;
    private double longitude;

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Cell> cells;

    public Node() {
        this(Long.MIN_VALUE, "Undefined", "Undefined", 0.00, 0.00);
    }

    public Node(final long nodeId, final String name, final String location, final double latitude, final double longitude) {
        this.nodeId = nodeId;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(final long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public void setCells(final Set<Cell> cells) {
        cells.forEach(cell -> cell.setParent(this));
        this.cells = cells;
    }

    public void addCell(final Cell cell) {
        if (cells == null) {
            cells = new HashSet<>();
        }
        cells.add(cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        return nodeId == ((Node) o).getNodeId();
    }
}
