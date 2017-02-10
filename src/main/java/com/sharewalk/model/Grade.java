package com.sharewalk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "public.grade")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Grade.findAllByWalk", query = "SELECT * FROM grade g where g.walk_id= :walkid", resultClass = Grade.class),
        @NamedNativeQuery(name = "Grade.findAllByWalkAndUser", query = "SELECT * FROM grade g where g.walk_id= :walkid and g.user_id= :userid", resultClass = Grade.class),
})
public class Grade {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "grade")
    private Float grade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Walk walk;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public Grade(Float grade, Walk walk, User user) {
        this.grade = grade;
        this.walk = walk;
        this.user = user;
    }

    public Grade() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
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
        return "Grade [id=" + id + ", grade=" + grade + "]";
    }
}
