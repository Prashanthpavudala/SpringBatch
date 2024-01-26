package com.learning.springbatchprocess.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String EXPORT_USER_DATA_STEP = "exportUserDataStep";
    public static final int CHUNK_SIZE = 10;
    public static final int THREAD_SIZE = 5;
}
