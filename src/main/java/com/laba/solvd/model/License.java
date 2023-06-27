package com.laba.solvd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.laba.solvd.parsers.DateHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class License {
    @JsonProperty("id")
    @XmlElement
    private Integer id;
    @JsonProperty("certificationNo")
    @XmlElement
    private int certificationNo;
    @JsonDeserialize(using = DateHandler.class)
    @JsonProperty("issued")
    @XmlElement
    private LocalDate issued;
    @JsonDeserialize(using = DateHandler.class)
    @JsonProperty("expired")
    @XmlElement
    private LocalDate expired;

    public License() {

    }

    public License(Integer id, int certificationNo, LocalDate issued, LocalDate expired) {
        this.id = id;
        this.certificationNo = certificationNo;
        this.issued = issued;
        this.expired = expired;
    }

    public Integer getId() {
        return id;
    }

    public int getCertificationNo() {
        return certificationNo;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public LocalDate getExpired() {
        return expired;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCertificationNo(int certificationNo) {
        this.certificationNo = certificationNo;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public void setExpired(LocalDate expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equals(id, license.id) && Objects.equals(certificationNo, license.certificationNo) && Objects.equals(issued, license.issued) && Objects.equals(expired, license.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certificationNo, issued, expired);
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", certificationNo=" + certificationNo +
                ", issued=" + issued +
                ", expired=" + expired +
                '}';
    }
}
