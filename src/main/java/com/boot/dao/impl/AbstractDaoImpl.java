package com.boot.dao.impl;

import com.boot.dao.AbstractDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Transactional
public abstract class AbstractDaoImpl<ENTITY extends AbstractDao> implements AbstractDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ENTITY> findByUniqueField(final String uniqueField, final String value) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ENTITY> cq = cb.createQuery(getEntity());
        final Root<ENTITY> root = cq.from(getEntity());

        cq.select(root);
        cq.where(cb.equal(root.get(uniqueField), value));

        final TypedQuery<ENTITY> typedQuery = entityManager.createQuery(cq);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    protected abstract Class<ENTITY> getEntity();

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}