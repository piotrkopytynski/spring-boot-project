package com.boot.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AbstractDao<ENTITY> {

    Set<ENTITY> findAll();

    ENTITY getById(final long id);

    ENTITY save(final ENTITY entity);

    void update(final ENTITY entity);
}
