package com.voca1800.service;

import com.voca1800.entity.master.WordMaster;
import com.voca1800.repository.WordMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final WordMasterRepository wordMasterRepository;

    public List<WordMaster> getWords() {
        return wordMasterRepository.findAll();
    }
}
