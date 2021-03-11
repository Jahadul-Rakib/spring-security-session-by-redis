//package com.rakib.common.repository;
//
//import com.rakib.common.config.AppConstant;
//import com.rakib.common.redis.SessionSecurityContext;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import java.util.Optional;
//
//@Repository
//public class SecurityContextRepo {
//
//    private final HashOperations hashOperations;
//
//
//    public SecurityContextRepo(RedisTemplate<String, Object> redisTemplate) {
//        hashOperations = redisTemplate.opsForHash();
//    }
//
//    public void set(String token, SessionSecurityContext sessionSecurityContext) {
//        hashOperations.put(AppConstant.SECURITY_SESSION, token, sessionSecurityContext);
//    }
//
//    public Optional<SessionSecurityContext> getOne(String token) {
//        return Optional.ofNullable((SessionSecurityContext) hashOperations.get(AppConstant.SECURITY_SESSION, token));
//    }
//
//    public Optional<Map<String, SessionSecurityContext>> getAll() {
//        return Optional.of(hashOperations.entries(AppConstant.SECURITY_SESSION));
//    }
//
//    public boolean delete(String token) {
//        Optional<SessionSecurityContext> tokenDTOOptional = getOne(token);
//        if (tokenDTOOptional.isPresent()) {
//            hashOperations.delete(AppConstant.SECURITY_SESSION, token);
//            return true;
//        }
//        return false;
//    }
//
//    public void update(String token, SessionSecurityContext sessionSecurityContext) {
//        hashOperations.put(AppConstant.SECURITY_SESSION, token, sessionSecurityContext);
//    }
//}
