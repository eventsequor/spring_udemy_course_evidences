package com.eventsequor.relations_jpa.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "client_id_fk")
    @JoinTable(
            name = "tbl_client_to_address",
            joinColumns = @JoinColumn(name = "client_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "address_id_fk"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"address_id_fk"})
    )
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetails clientDetails;

    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    public void addInvoices(Invoice... invoices) {
        for (Invoice i : invoices)
            addInvoice(i);
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
    }

    public void removeInvoice(Invoice invoice) {
        this.getInvoices().remove(invoice);
        invoice.setClient(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setClient(this);
    }

    public void removeClientDetails(ClientDetails clientDetails) {
        clientDetails.setClient(null);
        this.clientDetails = null;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", addresses=" + addresses +
                ", invoices=" + invoices +
                ", clientDetails=" + clientDetails +
                '}';
    }
}
