package com.example.pretice3.dto;

import com.example.pretice3.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardDTO extends BaseEntity {


    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime moddate;

}
