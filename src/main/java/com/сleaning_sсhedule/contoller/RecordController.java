package com.сleaning_sсhedule.contoller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.сleaning_sсhedule.dao.RecordInformation;
import com.сleaning_sсhedule.dao.Records;
import com.сleaning_sсhedule.entity.Record;
import com.сleaning_sсhedule.exceptions.RecordNotFoundException;
import com.сleaning_sсhedule.exceptions.UserNotFoundException;
import com.сleaning_sсhedule.service.BotService;
import com.сleaning_sсhedule.service.RecordService;
import com.сleaning_sсhedule.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/records/")
@NoArgsConstructor
@AllArgsConstructor
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserService userService;
    @Autowired
    private BotService botService;

    @GetMapping
    public Records getAllRecords() {
        return new Records(recordService.getAllRecords());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Record createRecord(
            @RequestBody RecordInformation recordInformation
    ) throws UserNotFoundException, UnirestException {
        Record record = recordService.createRecord(
                userService.getUserById(recordInformation.getUser()).orElseThrow(UserNotFoundException::new),
                LocalDate.now()
        );
        botService.sendMessage(record.getUser().getUsername()+ " виніс сміття ");
        return record;
    }

    @GetMapping(path = "/{id}")
    public Record getRecordById(@PathVariable long id) throws RecordNotFoundException {
        return recordService.getRecordById(id).orElseThrow(RecordNotFoundException::new);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable long id) throws RecordNotFoundException {
        Record record = recordService.getRecordById(id).orElseThrow(RecordNotFoundException::new);
        recordService.deleteRecord(record);
    }

}
