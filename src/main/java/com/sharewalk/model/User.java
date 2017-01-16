package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.user")
@NamedQuery(name="User.findAll", query="SELECT c FROM User c")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String comment) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String comment) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Walk [id=" + id + ", name=" + email +"]";
    }
}
