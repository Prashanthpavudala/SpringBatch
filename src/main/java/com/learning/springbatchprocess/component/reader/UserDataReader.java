package com.learning.springbatchprocess.component.reader;

import com.learning.springbatchprocess.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
@Slf4j
public class UserDataReader implements ItemReader<UserDTO> {

    private Queue<UserDTO> records = new LinkedList<>();

    @PostConstruct
    public void init() {
        log.info("Inside UserDataReader init...");
        try {
            log.warn("No records found");
        }catch (Exception e) {
            log.error("Exception occurred inside UserDataReader init is ", e);
        }
    }

    @Override
    public UserDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return records.poll();
    }
}
