package com.laba.solvd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
    @JsonProperty("id")
    @XmlElement
    private Integer id;
    @JsonProperty("seatNo")
    @XmlElement
    private String seatNo;
    @JsonProperty("price")
    @XmlElement
    private float price;

    @JsonProperty("passengers")
    @XmlElementWrapper(name = "passengers")
    @XmlElement(name = "passenger")
    private List<Passenger> passengers;

    public Reservation() {

    }

    public Reservation(Integer id, String seatNo, float price, List<Passenger> passengers) {
        this.id = id;
        this.seatNo = seatNo;
        this.price = price;
        this.passengers = passengers;
    }

    public Integer getId() {
        return id;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public float getPrice() {
        return price;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Float.compare(that.price, price) == 0 && Objects.equals(id, that.id) && Objects.equals(seatNo, that.seatNo) && Objects.equals(passengers, that.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatNo, price, passengers);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", seatNo='" + seatNo + '\'' +
                ", price=" + price +
                ", passengers=" + passengers +
                '}';
    }
}
