package com.learning.springbatchprocess.component.writer;

import com.learning.springbatchprocess.domain.UserInfo;
import com.learning.springbatchprocess.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDataWriter implements ItemWriter<UserInfo> {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void write(Chunk<? extends UserInfo> chunk) throws Exception {
        log.info("Inside UserDataWriter with {} items", chunk.size());
        try {
            if(!chunk.isEmpty()) {
                userInfoRepository.saveAll(chunk);
            }

        }catch (Exception e) {
            log.error("Exception occurred inside UserDataWriter is ", e);
        }
    }
}
