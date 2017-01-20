package com.sharewalk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "public.walk")
@NamedQueries({
        @NamedQuery(name = "Walk.findAll", query = "SELECT w FROM Walk w"),
        @NamedQuery(name = "Walk.findByID", query = "SELECT w FROM Walk w where w.id= :id"),
        @NamedQuery(name = "Walk.findAllStartsWith", query = "SELECT w FROM Walk w where w.name like :startsWith"),
        @NamedQuery(name = "Walk.findAllForUser", query = "SELECT w FROM Walk w where w.userid= :userid"),
        @NamedQuery(name = "Walk.findAllForUserStartsWith", query = "SELECT w FROM Walk w where w.userid= :userid and w.name like :startsWith")
})
public class Walk {

        @Id
        @Column(name = "id")
        @GeneratedValue
        private long id;

        @Column(name = "name")
        private String name;

        @JoinColumn(name = "userid", referencedColumnName = "public.user.id")
        private long userid;

        @JsonProperty("waypoint")
        //no generalnie z tym transient jest problem bo jak to dam to nie uzupełnia tablekli Waypoint jak dodaje nowy Walk a jak chce wyswietlac wszystkie Walki to nie chce zeby wyswietlac to
        @Transient
        @OneToMany(cascade=CascadeType.ALL)
        @JoinColumn(name="walk_id")
        private List<WayPoint> wayPointList;


    public Walk(String name, long user_id) {
        this.name = name;
        this.userid = user_id;
    }

    public Walk() {
    }

    public long getId() {
            return id;
        }

    public void setId(long id) {
            this.id = id;
        }

    public String getName() {
            return name;
        }

    public void setName(String name) {
            this.name = name;
        }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public List<WayPoint> getWayPointList() {
        return wayPointList;
    }

    public void setWayPointList(List<WayPoint> wayPointList) {
        this.wayPointList = wayPointList;
    }

    @Override
        public String toString() {
            return "Walk [id=" + id + ", name=" + name +"]";
        }
}

