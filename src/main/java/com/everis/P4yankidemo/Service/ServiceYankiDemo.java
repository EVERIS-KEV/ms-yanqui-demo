package com.everis.P4yankidemo.Service;

import com.everis.P4yankidemo.Constants.Constants;
import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.DTO.RegisterFrom;
import com.everis.P4yankidemo.Model.Customer;
import com.everis.P4yankidemo.Model.YankiAccount;
import com.everis.P4yankidemo.Repository.RepositoryCustomer;
import com.everis.P4yankidemo.Repository.RepositoryYankiAccount;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ServiceYankiDemo {

  @Autowired
  private RepositoryCustomer RepositoryCustomer;

  @Autowired
  private RepositoryYankiAccount RepositoryYankiAccount; //75395108

  private Boolean existsByDni(String numberDocument) {
    return !findAll()
      .entrySet()
      .stream()
      .filter(c -> c.getValue().getNumberDocument().equals(numberDocument))
      .collect(Collectors.toList())
      .isEmpty();
  }

  public Mono<Object> save(RegisterFrom model) {
    RepositoryYankiAccount.save(
      model.getNumberPhone(),
      model.getEmailAddress(),
      model.getTypeDocument(),
      model.getNumberDocument()
    );

    return RepositoryCustomer.save(
      model.getFirstName(),
      model.getLastName(),
      model.getTypeDocument(),
      model.getNumberDocument()
    );
  }

  public Map<String, Customer> findAll() {
    return RepositoryCustomer.findAll();
  }

  public Mono<Object> findByDocument(String numberDocument) {
    if (!existsByDni(numberDocument)) return Mono.just(
      new MessageFrom(Constants.MessageRequest.INCORRECT_DATA)
    );

    return Mono.just(
      findAll()
        .entrySet()
        .stream()
        .filter(c -> c.getValue().getNumberDocument().equals(numberDocument))
        .findFirst()
        .get()
        .getValue()
    );
  }

  public Mono<Object> findAccountByDocument(String numberDocument) {
    if (!existsByDni(numberDocument)) return Mono.just(
      new MessageFrom(Constants.MessageRequest.INCORRECT_DATA)
    );

    return Mono.just(
      RepositoryYankiAccount
        .findAll()
        .entrySet()
        .stream()
        .filter(c -> c.getValue().getNumberDocument().equals(numberDocument))
        .findFirst()
        .get()
        .getValue()
    );
  }

  public Mono<Object> changeStateYankiAccount(String numberPhone, String accessCode) {
    YankiAccount yankiAccount = RepositoryYankiAccount
      .findAll()
      .entrySet()
      .stream()
      .filter(c -> c.getValue().getNumberPhone().equals(numberPhone))
      .findFirst()
      .get()
      .getValue(); 

      yankiAccount.setState(true);
      yankiAccount.setAccessCode(accessCode);   
      RepositoryYankiAccount.delete(yankiAccount.getNumberDocument());

    return RepositoryYankiAccount.update(yankiAccount);
  }

  public Mono<Object> findById(String id) {
    return RepositoryCustomer.findById(id);
  }

  public Mono<MessageFrom> delete(String id) {
    return RepositoryCustomer.delete(id);
  }

  public Map<String, YankiAccount> findAllYankiAccount(){
    return RepositoryYankiAccount.findAll();
  }
}
