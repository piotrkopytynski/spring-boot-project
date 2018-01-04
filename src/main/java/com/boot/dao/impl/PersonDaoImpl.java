package com.boot.dao.impl;

import com.boot.dao.PersonDaoCustom;
import com.boot.entity.AbstractEntity;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.entity.Person_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl implements PersonDaoCustom {

    @Override
    public Set<Person> findFiltered(final Gender genderParam, final Integer childrenNumber, final Boolean insured) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        final Root<Person> root = cq.from(Person.class);
        final List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(genderParam)
                .map(gender -> cb.equal(root.get(Person_.gender), gender))
                .ifPresent(predicates::add);

        Optional.ofNullable(childrenNumber)
                .map(gender -> cb.equal(root.get(Person_.childrenNumber), childrenNumber))
                .ifPresent(predicates::add);

        Optional.ofNullable(insured)
                .map(gender -> cb.equal(root.get(Person_.insured), insured))
                .ifPresent(predicates::add);

        cq.select(root);
        cq.where(predicates.toArray(new Predicate[] {}));

        final TypedQuery<Person> typedQuery = getEntityManager().createQuery(cq);
        return new HashSet<>(typedQuery.getResultList());
    }

    @Override
    protected Class<? extends AbstractEntity> getEntity() {
        return Person.class;
    }
}