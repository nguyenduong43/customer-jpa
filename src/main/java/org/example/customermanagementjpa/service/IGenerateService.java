package org.example.customermanagementjpa.service;

import org.example.customermanagementjpa.model.Customer;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    void save(T t);

    T findById(Long id);

    void remove(Long id);
    List<Customer> search(String name);

}