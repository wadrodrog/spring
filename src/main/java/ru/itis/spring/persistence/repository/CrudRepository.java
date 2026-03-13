package ru.itis.spring.persistence.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, V> {
    void save(T t);
    Optional<T> getById(V id);
    List<T> getAll();
    void update(T t);
    boolean deleteById(V id);
    void deleteAll();
}
