package com.сleaning_sсhedule.repository;

import com.сleaning_sсhedule.entity.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {
    Collection<Record> findAll();
}
