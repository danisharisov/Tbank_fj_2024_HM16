package com.example.tbank_fj_2024_hm16.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ElkService {

    public String doSomething() {
        log.info("Starting generating...");
        String id =  UUID.randomUUID().toString();
        log.info("Finished generating.");

        return id;
    }
}