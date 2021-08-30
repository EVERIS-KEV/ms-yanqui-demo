package com.everis.P4yankidemo.Controller;

import com.everis.P4yankidemo.DTO.CustomerForm;
import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.Moodel.Customer;
import com.everis.P4yankidemo.Repository.RepositoryCustomer;
import java.util.Map;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class ControllerCustomer {

  @Autowired
  private RepositoryCustomer repository;

  private Mono<Object> BindingResultErrors(BindingResult bindinResult) {
    String msg = "";

    for (int i = 0; i < bindinResult.getAllErrors().size(); i++) {
      msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
    }
    return Mono.just(new MessageFrom(msg));
  }

  @GetMapping("/")
  public Map<String, Customer> findAll() {
    return repository.findAll();
  }

  @PostMapping("/save")
  public Mono<Object> save(@RequestBody @Valid CustomerForm customer, BindingResult bindinResult) {
    if (bindinResult.hasErrors()) return BindingResultErrors(bindinResult);
    return repository.save(
      customer.getFirstName(),
      customer.getLastName(),
      customer.getNumberPhone(),
      customer.getEmailAddress(),
      customer.getTypeDocument(),
      customer.getNumberDocument()
    );
  }
}
