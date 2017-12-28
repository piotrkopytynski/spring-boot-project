package com.boot.dao.impl;

import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.entity.Person_;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_EMAIL = "email";

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Person findByEmail(final String email) {
        final Query query = getEntityManager().createQuery("SELECT e FROM person e where e.email = :email");
        query.setParameter(FIELD_EMAIL, email);
        return (Person) query.getSingleResult();
    }

    @Override
    public Set<Person> findByGender(final Gender gender) {
        final Query query = getEntityManager().createQuery("SELECT e FROM person e where e.gender = :gender");
        query.setParameter(FIELD_GENDER, gender);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Person> findFiltered(final Gender genderParam, final Integer childrenNumber, final Boolean insured) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Person> cq = cb.createQuery(getEntityClass());
        final Root<Person> root = cq.from(getEntityClass());
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
}