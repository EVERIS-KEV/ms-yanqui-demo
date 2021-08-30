package com.everis.P4yankidemo.Moodel;

import java.io.Serializable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

  private static final long serialVersionUID = 7156526077883281623L;
  private String firstName;
  private String lastName;
  private String numberPhone;
  private String emailAddress;

  private String typeDocument;
  private String numberDocument;

  private String accessCode;
}
