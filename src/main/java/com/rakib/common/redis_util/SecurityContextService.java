//package com.rakib.common.security_service;
//
//import com.rakib.common.redis.SessionSecurityContext;
//import com.rakib.common.repository.SecurityContextRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.web.context.HttpRequestResponseHolder;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class SecurityContextService extends HttpSessionSecurityContextRepository{
//
//    private final SecurityContextRepo securityContextRepo;
//
//    @Override
//    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
//        String token = String.valueOf(requestResponseHolder.getRequest().getHeaders(HttpHeaders.AUTHORIZATION));
//        SessionSecurityContext sessionSecurityContext = securityContextRepo.getOne(token).get();
//        return sessionSecurityContext.getSecurityContext();
//    }
//
//    @Override
//    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
//        String token = String.valueOf(request.getHeaders(HttpHeaders.AUTHORIZATION));
//        securityContextRepo.set(token,
//                SessionSecurityContext.builder()
//                        .token(token)
//                        .securityContext(context)
//                        .httpServletRequest(request)
//                        .httpServletResponse(response)
//                        .build());
//    }
//
//    @Override
//    public boolean containsContext(HttpServletRequest request) {
//        String token = String.valueOf(request.getHeaders(HttpHeaders.AUTHORIZATION));
//        Optional<SessionSecurityContext> securityContext = securityContextRepo.getOne(token);
//        if (securityContext.isPresent()) {
//            return true;
//        }
//        return false;
//    }
//}
