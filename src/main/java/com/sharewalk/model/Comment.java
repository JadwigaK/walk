package com.sharewalk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "public.comment")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Comment.findAll", query = "SELECT * FROM comment c", resultClass = Comment.class),
        @NamedNativeQuery(name = "Comment.findByIDComments", query = "SELECT * FROM comment c where c.walk_id= :walkid", resultClass = Comment.class)
})

public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    //@JsonProperty("comment")
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    private Walk walk;

    @ManyToOne
    //@JoinColumn(name = "user_id")
    private User user;

    public Comment(String comment) {
        this.comment = comment;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Walk getWalk() {
        return walk;
    }

    public User getUser() {
        return user;
    }

    public void setWalk(Walk walk) {
        this.walk = walk;
    }

    public void setWalkId(long id) {
        walk.setId(id);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(long id) {
        user.setId(id);
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment +"]";
    }
}
