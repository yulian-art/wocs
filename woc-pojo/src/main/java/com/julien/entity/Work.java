package com.julien.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private Long id;
    private String name;
    private Long teamId;
    private String captainStudentId;
    private Byte status;
}
