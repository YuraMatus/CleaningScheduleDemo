package com.сleaning_sсhedule.service;

import com.сleaning_sсhedule.entity.Record;
import com.сleaning_sсhedule.entity.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;


public interface RecordService {

    Collection<Record> getAllRecords();
    Optional<Record> getRecordById(long id);
    Record createRecord(User user, LocalDate date);
    void saveRecord(Record record);
    void deleteRecord(Record record);


}
