package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.user")
@NamedNativeQueries({
        @NamedNativeQuery(name = "User.findAll", query = "SELECT * FROM public.user u", resultClass = User.class),
        @NamedNativeQuery(name = "User.findUserByID", query = "SELECT * FROM public.user u where u.id = :id", resultClass = User.class),
        @NamedNativeQuery(name="User.findUserByEmail", query="SELECT * FROM public.user u where u.email = :email", resultClass = User.class)
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + email + "]";
    }

}
