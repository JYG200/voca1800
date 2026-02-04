package com.voca1800.controller;

import com.voca1800.entity.master.WordMaster;
import com.voca1800.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test/words")
    public List<WordMaster> getWords() {
        return testService.getWords();
    }



}
