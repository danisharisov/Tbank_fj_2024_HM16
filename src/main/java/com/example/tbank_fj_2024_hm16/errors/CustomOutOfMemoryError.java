package com.example.tbank_fj_2024_hm16.errors;

import java.util.ArrayList;
import java.util.List;

public class CustomOutOfMemoryError {
    public void generateOutOfMemory() {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}