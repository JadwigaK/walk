package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.walk")
@NamedQueries({
        @NamedQuery(name = "Walk.findAll", query = "SELECT w FROM Walk w"),
        @NamedQuery(name = "Walk.findByID", query = "SELECT w FROM Walk w where w.id= :id"),
        @NamedQuery(name = "Walk.findAllStartsWith", query = "SELECT w FROM Walk w where w.name like :startsWith")
})
public class Walk {

        @Id
        @Column(name = "id")
        @GeneratedValue
        private long id;

        @Column(name = "name")
        private String name;

        @JoinColumn(name = "userid")
        private long userid;

    public Walk(String name, long userid) {
        this.name = name;
        this.userid = userid;
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

        @Override
        public String toString() {
            return "Walk [id=" + id + ", name=" + name +"]";
        }
}

