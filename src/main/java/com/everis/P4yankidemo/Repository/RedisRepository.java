package com.everis.P4yankidemo.Repository;

import com.everis.P4yankidemo.DTO.MessageFrom;
import com.everis.P4yankidemo.Model.Customer;
import java.util.Map;
import reactor.core.publisher.Mono;

public interface RedisRepository {
  Map<String, Customer> findAll();

  Mono<Boolean> existsId(String id);

  Mono<Object> findById(String id);

  Mono<MessageFrom> save(
    String firstName,
    String lastName,
    String numberPhone,
    String emailAddress,
    String typeDocument,
    String numberDocument
  );

  Mono<MessageFrom> delete(String id);
}
