package com.julien.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildCompetitionVO {
    private Long id;
    private String name;
    private String description;
    private Integer minTeamMembers;
    private Integer maxTeamMembers;
    private String workCode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reviewBeginTime;
    private LocalDateTime reviewEndTime;
    private LocalDateTime createTime;
}
