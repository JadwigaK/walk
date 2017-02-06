package com.sharewalk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "public.comment")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Comment.findByWalkID", query = "SELECT * FROM comment c where c.walk_id= :walkid", resultClass = Comment.class)
})

public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "comment")
    private String comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Walk walk;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private User user;

    public Comment(String comment, Walk walk, User user) {
        this.comment = comment;
        this.walk = walk;
        this.user = user;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setWalk(Walk walk) {
        this.walk = walk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment1 = (Comment) o;

        if (id != comment1.id) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (walk != null ? !walk.equals(comment1.walk) : comment1.walk != null) return false;
        return user != null ? user.equals(comment1.user) : comment1.user == null;
    }
}
