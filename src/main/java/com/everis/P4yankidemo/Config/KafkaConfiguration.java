package com.everis.P4yankidemo.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

  @Bean
  public NewTopic adviceTopic() {
    return new NewTopic("TRANSACTION", 3, (short) 1);
  }
}
