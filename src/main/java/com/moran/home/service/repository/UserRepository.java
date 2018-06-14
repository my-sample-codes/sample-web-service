package com.moran.home.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.moran.home.service.entity.user.User;

public interface UserRepository extends CrudRepository<User, String> {

}
