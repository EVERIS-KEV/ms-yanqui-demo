package com.everis.P4yankidemo.Producer;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import com.everis.P4yankidemo.DTO.MessageFrom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerEmisor {

    private static final String TOPIC_TRANSACTION = "TRANSACTION";
    private static final String DEBIT_AFILIATION = "AFILIATION";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Mono<MessageFrom> sendMovement(String number) {
        log.info("### Producer sending message ###" + number);
        kafkaTemplate.send(TOPIC_TRANSACTION, number);
        return Mono.just(new MessageFrom("OK." + number));
    }
}
