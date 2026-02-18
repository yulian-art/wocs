package com.julien.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Long id;
    private String judgeId;
    private String workCode;
    private Long teamId;
    private Long comId;
    private Integer score;
    private String comment;
}
