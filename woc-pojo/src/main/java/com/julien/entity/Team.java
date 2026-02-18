package com.julien.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private Long id;
    private Long comId;
    private String name;
    private Long captainId;
    private String captainName;
    private Byte status;
    private String memberIds; // JSON数组用String存储
    private String instructorIds; // JSON数组用String存储
    private LocalDateTime createTime;
}
