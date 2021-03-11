package com.rakib.common.controller;

import com.rakib.common.config.ResponseBody;
import com.rakib.common.domain.vm.LogInVM;
import com.rakib.common.exception.classes.InvalidKeyException;
import com.rakib.common.exception.classes.NotFoundException;
import com.rakib.common.security_service.JsonWebTokenUtilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/auth/jwt")
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<?> getWelcome() {
        return ResponseBody.responseEntity(HttpStatus.OK, "Welcome Message", "Hello Rakib Welcome you.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogInCredentials(@RequestBody LogInVM logInVM) throws InvalidKeyException, NotFoundException {
        return ResponseBody.responseEntity(HttpStatus.OK, "Get Credential Successfully",
                JsonWebTokenUtilities.getLogInAndAccessCredential(logInVM));
    }

}
