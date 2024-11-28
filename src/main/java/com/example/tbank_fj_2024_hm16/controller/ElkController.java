package com.example.tbank_fj_2024_hm16.controller;

import com.example.tbank_fj_2024_hm16.errors.CustomOutOfMemoryError;
import com.example.tbank_fj_2024_hm16.errors.CustomStackOverflowError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tbank_fj_2024_hm16.service.ElkService;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ElkController {
    private final ElkService elkService;
    private final MeterRegistry meterRegistry;

    @GetMapping("/act")
    public String act() {
        var requestId = UUID.randomUUID().toString();
        try (var ignored = MDC.putCloseable("requestId", requestId)) {
            log.info("Getting response from ElkService...");
            return elkService.doSomething();
        }
    }

    @GetMapping("/customMetric")
    public String incrementCustomMetric() {
        meterRegistry.counter("custom_requests_total", "endpoint", "/customMetric").increment();
        log.info("Custom metric incremented.");
        return "Custom metric incremented!";
    }

    @GetMapping("/stack-overflow")
    public void triggerStackOverflow() {
        CustomStackOverflowError error = new CustomStackOverflowError();
        error.generateStackOverflow();
    }

    @GetMapping("/out-of-memory")
    public void triggerOutOfMemory() {
        CustomOutOfMemoryError error = new CustomOutOfMemoryError();
        error.generateOutOfMemory();
    }
}