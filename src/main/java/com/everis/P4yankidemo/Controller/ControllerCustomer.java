package com.everis.P4yankidemo.Controller;

import com.everis.P4yankidemo.DTO.AccessFrom;
import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.DTO.RegisterFrom;
import com.everis.P4yankidemo.Model.Customer;
import com.everis.P4yankidemo.Model.YankiAccount;
import com.everis.P4yankidemo.Producer.ProducerEmisor;
import com.everis.P4yankidemo.Service.ServiceYankiDemo;
import java.util.Map;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yanki")
public class ControllerCustomer {

  @Autowired
  private ServiceYankiDemo Service;

  @Autowired
  private ProducerEmisor producerEmisor;

  private Mono<Object> BindingResultErrors(BindingResult bindinResult) {
    String msg = "";

    for (int i = 0; i < bindinResult.getAllErrors().size(); i++)
      msg = bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage();

    return Mono.just(new MessageFrom(msg));
  }

  @GetMapping("/findAccountByDocument/{document}")
  public Mono<Object> findAccountByDocument(@PathVariable String document) {
    return Service.findAccountByDocument(document);
  }

  @GetMapping("/findAccountAll")
  public Map<String, YankiAccount> findAccountByDocument() {
    return Service.findAllYankiAccount();
  }

  @PostMapping("/registerCustomer")
  public Mono<Object> saveCustomer(@RequestBody @Valid RegisterFrom model, BindingResult bindinResult) {
    if (bindinResult.hasErrors())
      return BindingResultErrors(bindinResult);

    return Service.save(model);
  }

  @PostMapping("/access")
  public Mono<Object> access(@RequestBody @Valid AccessFrom model, BindingResult bindinResult) {
    if (bindinResult.hasErrors())
      return BindingResultErrors(bindinResult);
    return Service.changeStateYankiAccount(model.getNumberPhone(), model.getAccessCode());
  }

  @PostMapping("/transaction")
  public Mono<Object> transaction() {
    producerEmisor.sendMovement("GAAAAAAAAAAA");
    return Mono.just(new MessageFrom("GO..."));
  }

  // --

  @GetMapping("/listCustomer")
  public Map<String, Customer> findAll() {
    return Service.findAll();
  }

  @GetMapping("/findCustomer/{id}")
  public Mono<Object> findById(@PathVariable String id) {
    return Service.findById(id);
  }

  @GetMapping("/findCustomerByDocument/{document}")
  public Mono<Object> findCustomerByDocument(@PathVariable String document) {
    return Service.findByDocument(document);
  }

  @DeleteMapping("/deleteCustomer/{id}")
  public Mono<MessageFrom> deleteCustomer(@PathVariable String id) {
    return Service.delete(id);
  }
}
