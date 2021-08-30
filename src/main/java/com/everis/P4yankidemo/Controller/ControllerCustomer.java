package com.everis.P4yankidemo.Controller;

import com.everis.P4yankidemo.Moodel.Customer;
import com.everis.P4yankidemo.Repository.RepositoryCustomer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerCustomer {
  @Autowired
  private RepositoryCustomer repository;

  @GetMapping("/")
  public Map<String, Customer> findAll() {
    return repository.findAll();
  }

  @PostMapping("/save")
  public void save(@RequestBody Customer customer) {
    repository.save(customer);
  }
}
