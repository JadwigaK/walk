package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.waypoint")
public class WayPoint {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @ManyToOne
    private Walk walk;

    @Column(name = "pointname")
    private String pointname;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    public long getId() {
        return id;
    }

    public String getPointname() {
        return pointname;
    }

    public Walk getWalk() {
        return walk;
    }

    public String getDescription() {
        return description;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setId(long id) {
        this.id = id;
    }



    public void setPointname(String pointname) {
        this.pointname = pointname;
    }

    public void setWalk(Walk walk) {
        this.walk = walk;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
