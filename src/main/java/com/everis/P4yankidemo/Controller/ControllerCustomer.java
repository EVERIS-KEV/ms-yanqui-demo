package com.everis.P4yankidemo.Controller;

import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.DTO.RegisterFrom;
import com.everis.P4yankidemo.Model.Customer;

import com.everis.P4yankidemo.Service.ServiceCustomer;

import java.util.Map;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class ControllerCustomer {

  @Autowired
  private ServiceCustomer Service;

  private Mono<MessageFrom> BindingResultErrors(BindingResult bindinResult) {
    String msg = "";

    for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
      msg = bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage();
    
    return Mono.just(new MessageFrom(msg));
  }

  @GetMapping("/")
  public Map<String, Customer> findAll() { 
    return Service.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Object> findById(@PathVariable String id) { 
    return Service.findById(id);
  }

  @PostMapping("/save")
  public Mono<MessageFrom> saveCustomer(
    @RequestBody @Valid RegisterFrom model,
    BindingResult bindinResult
  ) {
    if (bindinResult.hasErrors()) return BindingResultErrors(bindinResult); 

    return Service.save(model.getFirstName(), model.getLastName());
  }

  @DeleteMapping("/delete/{id}")
  public Mono<MessageFrom> deleteCustomer(@PathVariable String id) { 
    return Service.delete(id);
  }
}
