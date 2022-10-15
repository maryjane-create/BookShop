package com.example.bookstore.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface Resource <T>{
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    ResponseEntity<Collection<T>>findAll();

    @GetMapping("{id}")
    ResponseEntity<T>findById(@PathVariable Long id);


}
