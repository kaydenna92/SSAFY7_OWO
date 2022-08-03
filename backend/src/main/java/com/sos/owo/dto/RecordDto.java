package com.sos.owo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RecordDto {

    int recordId;
    int memberId;
    String recordMemo;
    LocalDateTime recordCreateTime;
    int meetingRoomId;
    int recordImgId;




}
