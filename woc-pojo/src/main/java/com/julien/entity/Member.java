package com.julien.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
