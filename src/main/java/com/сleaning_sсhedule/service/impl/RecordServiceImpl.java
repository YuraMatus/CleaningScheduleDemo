package com.сleaning_sсhedule.service.impl;

import com.сleaning_sсhedule.entity.Record;
import com.сleaning_sсhedule.entity.User;
import com.сleaning_sсhedule.repository.RecordRepository;
import com.сleaning_sсhedule.service.RecordService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Override
   public Collection<Record> getAllRecords(){
        return recordRepository.findAll();
    }
    @Override
    public Optional<Record> getRecordById(long id){
        return recordRepository.findById(id);
    }
    @Override
    public Record createRecord(User user, LocalDate date){
    return recordRepository.save(
            new Record().setDate(date).setUser(user)
       );
    }

    @Override
    public void saveRecord(Record record){
        recordRepository.save(record);

    }
    @Override
    public void deleteRecord(Record record){
        recordRepository.delete(record);
    }

}
