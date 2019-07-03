package com.igor.ns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
