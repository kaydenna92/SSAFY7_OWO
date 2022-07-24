package com.sos.owo.controller;

import com.sos.owo.dto.GoalSaveRequestDto;
import com.sos.owo.dto.MemberSaveRequestDto;
import com.sos.owo.service.EmailTokenService;
import com.sos.owo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailTokenService emailTokenService;

    @PostMapping("/api/auth/join")
    public ResponseEntity<?> signUp(@RequestBody MemberSaveRequestDto requestDto){
        try {
            int id = memberService.join(requestDto.toEntity());
            String emailTokenId = emailTokenService.createEmailToken(id, requestDto.getEmail());
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/api/user/compete/{memberId}")
    public ResponseEntity<?> findBestScore(@PathVariable("memberId") int memberId, Model model){
        try {
            List<Integer> bestScore = memberService.findBestScore(memberId);
            model.addAttribute("bestScores", bestScore);
            return new ResponseEntity<List<Integer>>( memberService.findBestScore(memberId), HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/api/user/point/{point}/{memberId}")
    public ResponseEntity<?> savePoint(@PathVariable("point") int point, @PathVariable("memberId") int memberId){
        try {
            memberService.savePoint(point, memberId);
            return new ResponseEntity<String>("경쟁모드 경험치 저장성공", HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/user/point/{exp}/{memberId}")
    public ResponseEntity<?> saveExp(@PathVariable("exp") int exp, @PathVariable("memberId") int memberId){
        try {
            memberService.saveExp(exp, memberId);
            return new ResponseEntity<String>("자유/영상모드 경험치 저장성공", HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //운동 목표 추가
    @PostMapping("/api/user/goal/{memberId}")
    public ResponseEntity<?> saveGoal(@PathVariable("memberId") int memberId, @RequestBody GoalSaveRequestDto goalSaveRequestDto){
        try {
            memberService.saveGoal(memberId,goalSaveRequestDto.toEntity());

            return new ResponseEntity<String>("SUCCESS SAVE GOAL", HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //운동 목표 수정 -- 수정 필요
    @PutMapping("api/user/goal/{memberId}/{goalId}")
    public ResponseEntity<?> updateGoal(@PathVariable("memberId") int memberId, @RequestBody GoalSaveRequestDto goalSaveRequestDto){
        try {
            memberService.updateGoal(memberId,goalSaveRequestDto.toEntity());

            return new ResponseEntity<String>("SUCCESS UPDATE GOAL", HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //운동 목표 삭제
    @Delete("api/user/goal/{memberId}/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable("memberId") int memberId, @PathVariable("goalId") int goalId){
        try {
            memberService.deleteGoal(memberId,goalId);

            return new ResponseEntity<String>("SUCCESS UPDATE GOAL", HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>("OVERLAP", HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
