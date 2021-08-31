package com.everis.P4yankidemo.Repository;

import com.everis.P4yankidemo.Constants.Constants;
import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.Moodel.*;
import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RepositoryCustomer implements RedisRepository {

  private static final String KEY = "YANKI";

  @Autowired
  private RedisTemplate<String, Customer> redisTemplate;

  private HashOperations hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public Map<String, Customer> findAll() {
    return hashOperations.entries(KEY);
  }

  @Override
  public Customer findById(String id) {
    return (Customer) hashOperations.get(KEY, id);
  }

  @Override
  public Mono<MessageFrom> delete(String id) {
    hashOperations.delete(KEY, id);
    return Mono.just(new MessageFrom(Constants.MessageRequest.CLIENT_DELETED_SUCCESS));
  }

  @Override
  public Mono<MessageFrom> save(
    String firstName,
    String lastName,
    String numberPhone,
    String emailAddress,
    String typeDocument,
    String numberDocument
  ) {
    Customer customer = new Customer(
      firstName,
      lastName,
      numberPhone,
      emailAddress,
      typeDocument,
      numberDocument,
      null
    );
    hashOperations.put(KEY, UUID.randomUUID().toString(), customer);
    return Mono.just(new MessageFrom(Constants.MessageRequest.CORRECT_DATA));
  }
}
