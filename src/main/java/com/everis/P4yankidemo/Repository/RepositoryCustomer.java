package com.everis.P4yankidemo.Repository;

import com.everis.P4yankidemo.Moodel.Customer;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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
    return null;
  }

  @Override
  public void save(Customer customer) {
    hashOperations.put(KEY, UUID.randomUUID().toString(), customer);
  }
}
