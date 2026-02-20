package com.julien.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamVO implements Serializable {
    private Long id;
    private Long comId;
    private String name;
    private Long captainId;
    private String captainName;
    private Byte status;              // Byte也行，但Integer更省事
    private List<Long> memberIds;        // ✅ 改这里
    private List<Long> instructorIds;    // ✅ 改这里
    private LocalDateTime createTime;

}
