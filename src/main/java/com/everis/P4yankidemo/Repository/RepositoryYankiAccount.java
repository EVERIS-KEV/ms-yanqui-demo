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

@Repository
public class RepositoryYankiAccount {

  private static final String KEY_YANKI = "YANKI";

  @Autowired
  private RedisTemplate<String, YankiAccount> redisTemplate;

  private HashOperations hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  public Map<String, YankiAccount> findAll() {
    return hashOperations.entries(KEY_YANKI);
  }

  public YankiAccount findByDni(String numberDocument) {
    return findAll()
      .entrySet()
      .stream()
      .filter(c -> c.getValue().getNumberDocument().equals(numberDocument))
      .findFirst()
      .get()
      .getValue();
  }

  public Mono<Object> update(YankiAccount yankiAccount){
    hashOperations.put(KEY_YANKI, yankiAccount.getNumberPhone(), yankiAccount);
    return Mono.just(new MessageFrom(Constants.MessageRequest.CORRECT_DATA));
  }

  public Mono<Object> delete(String dni){
    hashOperations.delete(KEY_YANKI, findByDni(dni));
    return Mono.just(new MessageFrom(Constants.MessageRequest.CORRECT_DATA));
  }

  public Mono<MessageFrom> save(
    String numberPhone,
    String emailAddress,
    String typeDocument,
    String numberDocument
  ) {
    YankiAccount yankiAccount = new YankiAccount(
      numberPhone,
      emailAddress,
      typeDocument,
      numberDocument,
      null,
      false
    );

    hashOperations.put(KEY_YANKI, yankiAccount.getNumberPhone(), yankiAccount);
    return Mono.just(new MessageFrom(Constants.MessageRequest.CORRECT_DATA));
  }
}
