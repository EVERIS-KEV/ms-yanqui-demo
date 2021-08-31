package com.everis.P4yankidemo.Repository;

import com.everis.P4yankidemo.Constants.Constants;
import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.Model.*;
import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public class RepositoryYankiAccount {

  private static final String KEY_YANKI = "CUSTOMER"; 

  @Autowired
  private RedisTemplate<String, YankiAccount> redisTemplate;

  private HashOperations hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  } 

  public Map<String, YankiAccount> findAll(){
    return hashOperations.entries(KEY_YANKI);
  }
}
