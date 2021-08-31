package com.everis.P4yankidemo.Config;

import com.everis.P4yankidemo.Model.Customer;
import com.everis.P4yankidemo.Model.YankiAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  RedisTemplate<String, Customer> redisTemplateCustomer() {
    final RedisTemplate<String, Customer> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

  @Bean
  RedisTemplate<String, YankiAccount> redisTemplateYankiAccount() {
    final RedisTemplate<String, YankiAccount> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
}
