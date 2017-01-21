package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.waypoint")
public class WayPoint {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

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

    public void setPointname(String pointname) {
        this.pointname = pointname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
