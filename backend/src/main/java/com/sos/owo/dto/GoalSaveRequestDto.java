package com.sos.owo.dto;

import com.sos.owo.domain.Exercise;
import com.sos.owo.domain.Goal;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoalSaveRequestDto {
    @ApiModelProperty(example="YOGA")
    private Exercise exercise;
    @ApiModelProperty(example = "2")
    private int time;


    @Builder
    public GoalSaveRequestDto(Exercise exercise, int time) {
        this.exercise = exercise;
        this.time = time;

    }

    public Goal toEntity(){
        return Goal.builder().exercise(exercise).time(time).build();
    }
}
