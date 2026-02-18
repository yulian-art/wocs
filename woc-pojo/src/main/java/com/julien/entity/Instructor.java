package com.julien.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    private Long id;
    private String name;
    private String workCode;
    private Long teamId;
    private Long academyId;
    private String phone;
}
