package com.boot.service;

import java.util.List;

public interface AbstractService<ENTITY> {

    List<ENTITY> findAll();

    ENTITY getById(final long id);

    ENTITY save(final ENTITY entity);

    void update(final ENTITY entity);
}
