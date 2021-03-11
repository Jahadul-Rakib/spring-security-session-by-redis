package com.rakib.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBody {
    public static ResponseEntity<Map<String, Object>> responseEntity(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", status.value());
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.ok(response);
    }
}
