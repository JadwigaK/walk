package com.sharewalk.model;

import javax.persistence.*;

@Entity
@Table(name = "public.comment")
@NamedQueries({
        @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
        @NamedQuery(name = "Comment.findByIDComments", query = "SELECT c FROM Comment c where c.walkid= :walkid")
})

public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String comment;

    //@ManyToMany
    @JoinColumn(name = "walkid")
    private long walkid;

    //@ManyToMany
    @JoinColumn(name = "userid")
    private long userid;

    public Comment(String comment, long walkid, long userid) {
        this.comment = comment;
        this.walkid = walkid;
        this.userid = userid;
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getWalkid() {
        return walkid;
    }

    public void setWalkid(long walkid) {
        this.walkid = walkid;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment +"]";
    }
}
