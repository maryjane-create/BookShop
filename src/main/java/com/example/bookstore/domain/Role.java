package com.example.bookstore.domain;

import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class Role {

private  Long id;
private  String name;


@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Set<User> users;

    public Role() {
    }

    public Role(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


