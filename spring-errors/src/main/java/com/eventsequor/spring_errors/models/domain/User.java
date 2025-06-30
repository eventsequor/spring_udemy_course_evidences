package com.eventsequor.spring_errors.models.domain;

public class User {

    private long id;
    private String name;
    private String lastname;

    private Role role;

    public User() {
    }

    public User(long id, String name, String lastname, Role role) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.role = role;
    }

    public User(long id, String name, String lastname) {
        this(id, name, lastname, null);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
