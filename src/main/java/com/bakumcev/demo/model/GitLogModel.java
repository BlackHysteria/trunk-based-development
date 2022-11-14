package com.bakumcev.demo.model;

import com.bakumcev.demo.enums.git.GitKeywords;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GitLogModel {

    private HashMap<GitKeywords, String> info;

}