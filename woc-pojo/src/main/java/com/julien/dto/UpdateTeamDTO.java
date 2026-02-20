package com.julien.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateTeamDTO implements Serializable {
    private Long id;
    private Long comId;
    private String name;
    private Long captainId;
    private String captainName;
    private Byte status;
    private List<Long> memberIds;
    private List<Long> instructorIds;
    private LocalDateTime createTime;  // 添加创建时间字段
    private LocalDateTime updateTime;  // 添加更新时间字段
}
