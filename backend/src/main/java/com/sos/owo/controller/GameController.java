package com.sos.owo.controller;

import com.sos.owo.dto.GameStartDto;
import com.sos.owo.dto.Message;
import com.sos.owo.dto.StatusEnum;
import com.sos.owo.service.GameService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class GameController {

    private final GameService gameService;

    private Map<Integer,Integer> roomSession = new ConcurrentHashMap<>();


    @ApiOperation(value = "방 시작",notes = "방 시작. status start로 변경. start_date 저장")
    @PutMapping("/api/room/{roomId}/start") // 변경
    public ResponseEntity<?> startRoom(@PathVariable("roomID") int roomID, LocalDateTime startDate){//}, @RequestBody GameStartDto gameStartDto){
        Message message = new Message();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try {
            gameService.startRoom(roomID);
            return new ResponseEntity<>(message,httpHeaders, HttpStatus.OK);
        }catch (IllegalStateException e){
            e.printStackTrace();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("잘못된 요청(방이 존재하지 않음)");
            return new ResponseEntity<>(message,httpHeaders, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
            message.setMessage("내부 서버 에러");
            return new ResponseEntity<>(message,httpHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "방 종료",notes = "방(운동) 끝. status end로 변경. end_date 저장")
    @PutMapping("/api/room/{roomId}/end") // 변경
    public ResponseEntity<?> endRoom(@PathVariable("roomID") int roomID){//}, @RequestBody GameStartDto gameStartDto){
        Message message = new Message();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try {
            // status end, end date 저장
            gameService.endRoom(roomID);

            //만약에 최종 한명 남아있다면 세션에서 방을 지우기.
            if(this.roomSession.get(roomID)==1){
                this.roomSession.remove(roomID);
            }

            return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
        }catch (IllegalStateException e){
            e.printStackTrace();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("잘못된 요청(방이 존재하지 않음)");
            return new ResponseEntity<>(message,httpHeaders, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
            message.setMessage("내부 서버 에러");
            return new ResponseEntity<>(message,httpHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

