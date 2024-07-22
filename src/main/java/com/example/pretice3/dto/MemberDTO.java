package com.example.pretice3.dto;

import com.example.pretice3.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberDTO extends BaseEntity {

    private Integer id;
    private String username;
    private LocalDateTime moddate;

}
