package com.everis.P4yankidemo.DTO;

import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessFrom {

  @NotBlank(message = "El campo número no debe estar vacio.")
  @Size(min = 9, message = "El número celular debe tener mas de 9 carácteres.")
  private String numberPhone;

  @NotBlank(message = "El campo código no debe estar vacio.")
  @Size(min = 6, message = "El código debe tener mas de 7 carácteres.")
  private String accessCode;
}
