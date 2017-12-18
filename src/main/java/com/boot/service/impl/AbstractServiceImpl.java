package com.boot.service.impl;

import com.boot.dao.AbstractDao;
import com.boot.service.AbstractService;

import java.util.List;

public abstract class AbstractServiceImpl<ENTITY> implements AbstractService<ENTITY> {

    @Override
    public List<ENTITY> findAll() {
        return getDao().findAll();
    }

    @Override
    public ENTITY getById(final long id) {
        return getDao().getById(id);
    }

    @Override
    public ENTITY save(final ENTITY entity) {
        return getDao().save(entity);
    }

    @Override
    public void update(final ENTITY entity) {
        getDao().update(entity);
    }

    protected abstract AbstractDao<ENTITY> getDao();
}