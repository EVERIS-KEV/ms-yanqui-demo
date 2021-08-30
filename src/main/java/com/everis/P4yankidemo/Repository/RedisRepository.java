package com.everis.P4yankidemo.Repository;

import com.everis.P4yankidemo.Moodel.Customer;
import java.util.Map;

public interface RedisRepository {
  Map<String, Customer> findAll();

  Customer findById(String id);

  void save(Customer student);
}
