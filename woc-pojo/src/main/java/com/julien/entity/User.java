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
public class User {
    private Long id;
    private String userCode;
    private String password;
    private String name;
    private Long academyId;
    private Long comId;
    private Byte role;
    private LocalDateTime createdAt;
}
