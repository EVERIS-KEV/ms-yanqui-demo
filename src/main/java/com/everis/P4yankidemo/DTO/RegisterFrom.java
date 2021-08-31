package com.everis.P4yankidemo.DTO;

import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFrom {

  @NotBlank(message = "El campo nombre no debe estar vacio.")
  private String firstName;

  @NotBlank(message = "El campo apellido no debe estar vacio.")
  private String lastName;

  @NotBlank(message = "El campo telefono no debe estar vacio.")
  @Size(min = 9, message = "El número celular debe tener mas de 9 carácteres.")
  private String numberPhone;

  @Email(message = "Debe ingresar un email.")
  @NotBlank(message = "El campo e-mail no debe estar vacio.")
  private String emailAddress;

  @NotBlank(message = "El campo tipo de documento no debe estar vacio.")
  private String typeDocument;

  @NotBlank(message = "El campo número de documento no debe estar vacio.")
  @Size(min = 7, message = "El número de documento debe tener mas de 7 carácteres.")
  private String numberDocument;
}
