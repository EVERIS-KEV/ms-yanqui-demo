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
public class RepositoryCustomer {

  private static final String KEY_CUSTOMER = "CUSTOMER";

  @Autowired
  private RedisTemplate<String, Customer> redisTemplate;

  private HashOperations hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }
  
  private Mono<Boolean> existsId(String id) {
    Customer customer = (Customer) hashOperations.get(KEY_CUSTOMER, id);
    if (customer == null) return Mono.just(false);

    return Mono.just(true);
  }
  
  public Map<String, Customer> findAll() {
    return hashOperations.entries(KEY_CUSTOMER);
  }
  
  public Mono<Object> findById(String id) {
    if (!existsId(id).block()) return Mono.just(
      new MessageFrom(Constants.MessageRequest.INCORRECT_DATA)
    );

    Customer customer = (Customer) hashOperations.get(KEY_CUSTOMER, id);
    return Mono.just(customer);
  }
  
  public Mono<MessageFrom> delete(String id) {
    if (!existsId(id).block()) return Mono.just(
      new MessageFrom(Constants.MessageRequest.INCORRECT_DATA)
    );

    hashOperations.delete(KEY_CUSTOMER, id); 
    return Mono.just(new MessageFrom(Constants.MessageRequest.CLIENT_DELETED_SUCCESS));
  }
  
  public Mono<MessageFrom> save(
    String firstName,
    String lastName
  ) {
    Customer customer = new Customer(
      firstName,
      lastName 
    ); 

    hashOperations.put(KEY_CUSTOMER, UUID.randomUUID().toString(), customer);
    return Mono.just(new MessageFrom(Constants.MessageRequest.CORRECT_DATA));
  }
}
