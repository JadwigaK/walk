package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.walk")
@NamedQuery(name="Walk.findAll", query="SELECT w FROM Walk w")
public class Walk {

        @Id
        @Column(name = "id")
        @GeneratedValue
        private long id;

        @Column(name = "name")
        private String name;

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

        @Override
        public String toString() {
            return "Walk [id=" + id + ", name=" + name +"]";
        }
}

