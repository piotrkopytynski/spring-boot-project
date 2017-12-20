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

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Person findByEmail(final String email) {
        final Query query = getEntityManager().createQuery("SELECT e FROM person e where e.email = :email");
        query.setParameter("email", email);
        return (Person) query.getSingleResult();
    }

    @Override
    public Set<Person> findByGender(final Gender gender) {
        final Query query = getEntityManager().createQuery("SELECT e FROM person e where e.gender = :gender");
        query.setParameter("gender", gender);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Person> findFiltered(final Gender gender, final Integer childrenNumber, final Boolean insured) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Person> cq = cb.createQuery(getEntityClass());
        final Root<Person> root = cq.from(getEntityClass());

        final List<Predicate> predicates = new ArrayList<>();
        if (Optional.ofNullable(gender).isPresent()) {
            predicates.add(cb.equal(root.get(Person_.gender), gender));
        }
        if (Optional.ofNullable(childrenNumber).isPresent()) {
            predicates.add(cb.equal(root.get(Person_.childrenNumber), childrenNumber));
        }
        if (Optional.ofNullable(insured).isPresent()) {
            predicates.add(cb.equal(root.get(Person_.insured), insured));
        }

        cq.select(root);
        cq.where(predicates.toArray(new Predicate[] {}));

        final TypedQuery<Person> typedQuery = getEntityManager().createQuery(cq);
        return new HashSet<>(typedQuery.getResultList());
    }
}