package com.everis.P4yankidemo.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement implements Serializable {
  private static final long serialVersionUID = 7156526077883281623L;
  private LocalDateTime dateCreated = LocalDateTime.now(ZoneId.of("America/Lima"));
  private String IdYankiAccount;
  private String NumberEmisor;
  private String NumberReceptor;
  private Double amount;
}
