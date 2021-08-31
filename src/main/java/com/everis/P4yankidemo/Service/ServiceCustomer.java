package com.everis.P4yankidemo.Service;

import java.util.Map;

import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.Model.Customer;
import com.everis.P4yankidemo.Repository.RepositoryCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ServiceCustomer {
  @Autowired
  private RepositoryCustomer repository;
  
  public Mono<MessageFrom> save(String firstName, String lastName){
    return repository.save(firstName, lastName); 
  }

  public Map<String, Customer> findAll(){
    return repository.findAll();
  }

  public Mono<Object> findById(String id){
    return repository.findById(id);
  }

  public Mono<MessageFrom> delete(String id){
    return repository.delete(id);
  }
}
