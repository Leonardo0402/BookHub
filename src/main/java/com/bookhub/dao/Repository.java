package com.bookhub.dao;

import java.util.List;
import java.util.Optional;

/**
 * 泛型仓储接口，展示 Java 泛型的应用。DAO 实现类（JDBC）可以实现该接口。
 */
public interface Repository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);
}
