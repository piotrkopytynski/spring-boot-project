package com.boot.dao;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbstractDao<ENTITY> {
    Optional<ENTITY> findByUniqueField(final String uniqueField, final String value);
}
