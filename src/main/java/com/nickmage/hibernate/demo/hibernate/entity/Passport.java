package com.nickmage.hibernate.demo.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport course = (Passport) o;
        return getId().equals(course.getId()) && getNumber().equals(course.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber());
    }
}
