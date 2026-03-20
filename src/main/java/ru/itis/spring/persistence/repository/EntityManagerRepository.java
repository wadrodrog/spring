package ru.itis.spring.persistence.repository;

import java.util.List;

public interface EntityManagerRepository<T, V> {
    void save(T t);
    T getById(V id);
    List<T> getAll();
    void delete(T t);
}
