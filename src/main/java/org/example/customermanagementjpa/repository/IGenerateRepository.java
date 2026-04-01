package org.example.customermanagementjpa.repository;

import org.example.customermanagementjpa.model.Customer;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
    List<T> search(String name);
}
