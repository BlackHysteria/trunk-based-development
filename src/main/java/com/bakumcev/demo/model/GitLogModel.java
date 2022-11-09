package com.bakumcev.demo.model;

import com.bakumcev.demo.enums.GitKeywords;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GitLogModel {

    /*private String commit;
    private String author;
    private LocalDateTime createdDate;
    private String description;*/

    private HashMap<GitKeywords, String> info;


}