package com.julien.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String studentId;
    private Long teamId;
    private Long academyId;
    private String phone;
    private Byte isCaptain;
    private LocalDateTime createTime;  // 添加创建时间字段
    private LocalDateTime updateTime;  // 添加更新时间字段
}
