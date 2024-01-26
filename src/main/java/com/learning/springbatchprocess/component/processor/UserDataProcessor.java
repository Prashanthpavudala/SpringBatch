package com.learning.springbatchprocess.component.processor;

import com.learning.springbatchprocess.domain.UserInfo;
import com.learning.springbatchprocess.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDataProcessor implements ItemProcessor<UserDTO, UserInfo> {

    @Override
    public UserInfo process(UserDTO item) throws Exception {
        log.info("Inside UserDataProcessor with record {}", item);
        try {
            /*  */
        }catch (Exception e) {
            log.error("Exception occurred inside UserDataProcessor is ", e);
        }
        return null;
    }
}
