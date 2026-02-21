package com.julien.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BuildCompetitionDTO {
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
