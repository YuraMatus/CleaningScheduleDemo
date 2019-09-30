package com.сleaning_sсhedule.repository;

import com.сleaning_sсhedule.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Collection<User> findAll();
}
