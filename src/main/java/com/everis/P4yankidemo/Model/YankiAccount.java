package com.everis.P4yankidemo.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.*;

@Data
@NoArgsConstructor 
public class YankiAccount implements Serializable{  
  private static final long serialVersionUID = 7156526077883281623L;
  private LocalDateTime dateCreated = LocalDateTime.now(ZoneId.of("America/Lima"));
  private String IdCustomer;

  private String numberPhone;
  private String emailAddress;

  private String typeDocument;
  private String numberDocument;

  private String accessCode;
  private Boolean State; 

  public YankiAccount(String IdCustomer, String numberPhone, String emailAddress, String typeDocument, String numberDocument){
    this.accessCode = null;
    this.State = false;
  }
}
