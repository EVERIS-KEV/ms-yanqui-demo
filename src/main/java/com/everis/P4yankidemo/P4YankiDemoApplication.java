package com.everis.P4yankidemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class P4YankiDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(P4YankiDemoApplication.class, args);
    log.info("-SERVICIO MONEDERO VIRTUAL YANKI HABILITADO-");
  }
}
