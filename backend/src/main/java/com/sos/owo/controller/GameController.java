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

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class GameController {

    private final GameService gameService;

    @ApiOperation(value = "게임 방 시작",notes = "게임 시작. status start로 변경.")
    @PutMapping("/api/{roomId}/start") // 변경
    public ResponseEntity<?> startGame(@PathVariable("roomID") int roomID){//}, @RequestBody GameStartDto gameStartDto){
        Message message = new Message();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try {
            gameService.startrRoom(roomID);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch (IllegalStateException e){
            e.printStackTrace();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("잘못된 요청(ex. memberId가 null인 경우)");
            return new ResponseEntity<>(message,httpHeaders, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
            message.setMessage("내부 서버 에러");
            return new ResponseEntity<>(message,httpHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
