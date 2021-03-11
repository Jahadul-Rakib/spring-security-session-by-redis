package com.rakib.common.domain.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private String userEmail;
    private List<String> userRole;
}
