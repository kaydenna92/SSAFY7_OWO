package com.sos.owo.service;


import com.sos.owo.dto.RecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordService {

    @Transactional
    public void registRecord(RecordDto recordDto) {}
}
