package com.nickmage.hibernate.demo.presentation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "visa")
//@Cacheable
public class Visa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "visas", fetch = FetchType.LAZY)
    private List<Tourist> tourists;

    public Visa() {
    }

    public Visa(String countryName) {
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public void addTourist(Tourist student) {
        tourists.add(student);
    }

    public void removeTourist(Tourist student) {
        tourists.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visa visa = (Visa) o;
        return getId().equals(visa.getId()) && getCountryName().equals(visa.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountryName());
    }

    @Override
    public String toString() {
        return "Visa{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
