package com.boot.dao.impl;

import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Person getByEmail(final String email) {
        final Query query = getEntityManager().createQuery("SELECT e FROM person e where e.email = :email");
        query.setParameter("email", email);
        return (Person) query.getSingleResult();
    }
}