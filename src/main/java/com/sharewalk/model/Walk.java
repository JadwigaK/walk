package com.sharewalk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "public.walk")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Walk.findAll", query = "SELECT * FROM walk w", resultClass = Walk.class),
        @NamedNativeQuery(name = "Walk.findByID", query = "SELECT * FROM walk w where w.id = :id", resultClass = Walk.class),
        @NamedNativeQuery(name = "Walk.findAllStartsWith", query = "SELECT * FROM walk w where w.name like :startsWith", resultClass = Walk.class),
        @NamedNativeQuery(name = "Walk.findAllForUser", query = "SELECT * FROM walk w where w.user_id = :userid", resultClass = Walk.class),
        @NamedNativeQuery(name = "Walk.findAllForUserStartsWith", query = "SELECT * from walk w where w.user_id = :userid and w.name like :startsWith", resultClass = Walk.class)
})
public class Walk {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "walk_id")
    private List<WayPoint> wayPointList;


    public Walk(String name, User user, List<WayPoint> wayPointList) {
        this.name = name;
        this.user = user;
        this.wayPointList = wayPointList;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WayPoint> getWayPointList() {
        return wayPointList;
    }

    public void setWayPointList(List<WayPoint> wayPointList) {
        this.wayPointList = wayPointList;
    }

    @Override
    public String toString() {
        return "Walk [id=" + id + ", name=" + name + "]";
    }

}

