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

    public void setId(long id) {
        this.id = id;
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WayPoint)) return false;

        WayPoint wayPoint = (WayPoint) o;

        if (id != wayPoint.id) return false;
        if (pointname != null ? !pointname.equals(wayPoint.pointname) : wayPoint.pointname != null) return false;
        if (description != null ? !description.equals(wayPoint.description) : wayPoint.description != null)
            return false;
        if (longitude != null ? !longitude.equals(wayPoint.longitude) : wayPoint.longitude != null) return false;
        return latitude != null ? latitude.equals(wayPoint.latitude) : wayPoint.latitude == null;
    }
}
