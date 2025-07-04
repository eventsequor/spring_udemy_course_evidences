package com.eventsequor.relations_jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clients_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean premium;
    private Integer points;

    @OneToOne
    @JoinColumn(name = "client_id_detail_fk")
    private Client client;

    public ClientDetails() {
    }

    public ClientDetails(boolean premium, Integer points) {
        this.premium = premium;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "id=" + id +
                ", premium=" + premium +
                ", points=" + points +
                '}';
    }
}
