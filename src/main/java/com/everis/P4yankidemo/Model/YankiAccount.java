package com.everis.P4yankidemo.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YankiAccount  { 
  private static final LocalDateTime dateCreated = LocalDateTime.now(ZoneId.of("America/Lima"));

  private String numberPhone;
  private String emailAddress;

  private String typeDocument;
  private String numberDocument;

  private String accessCode;
  private Boolean State; 
}
