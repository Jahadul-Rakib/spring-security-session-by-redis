package com.rakib.common.domain.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInVM implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userEmail;
    private String password;
}
