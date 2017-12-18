package com.boot.dao.impl;

import com.boot.dao.AddressDao;
import com.boot.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<Address> implements AddressDao {

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }
}