package com.julien.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateMemberDTO implements Serializable {
    private Long id;
    private String name;
    private String studentId;
    private Long teamId;
    private Long academyId;
    private String phone;
    private Byte isCaptain;
}
