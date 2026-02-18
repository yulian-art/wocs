package com.julien.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionListVO implements Serializable {

    @ApiModelProperty("比赛列表")
    private Long id;
    private String name;
    private String description;
    private Long minTeamMembrs;
    private Long maxTeamMembrs;
    private String workCode;
    private String startTime;
    private String endTime;
    private String reviewBeginTime;
    private String reviewEndTime;
    private String createTime;

}
