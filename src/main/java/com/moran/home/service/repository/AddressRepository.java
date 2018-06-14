package com.moran.home.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.moran.home.service.entity.address.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

}
