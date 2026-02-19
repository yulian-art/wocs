package com.julien.dto;

import lombok.Data;

@Data
public class AddMemberDTO {
    private Long id;
    private String name;
    private String studentId;
    private Long academyId;
    private String phone;
    private Byte isCaptain;
}
