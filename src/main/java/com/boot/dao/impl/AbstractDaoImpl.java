package com.boot.dao.impl;

import com.boot.dao.AbstractDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;

@Transactional
public abstract class AbstractDaoImpl<ENTITY> implements AbstractDao<ENTITY>  {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<ENTITY> findAll(){
        final Query query = entityManager.createQuery("FROM " + getEntityClass().getSimpleName().toLowerCase());
        return (Set<ENTITY>) query.getResultList();
    }

    @Override
    public ENTITY getById(final long id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public ENTITY save(final ENTITY entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void update(final ENTITY entity) {
        entityManager.merge(entity);
    }

    @Override
    public void flush(ENTITY entity) {
        entityManager.flush();
    }

    protected abstract Class<ENTITY> getEntityClass();

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}