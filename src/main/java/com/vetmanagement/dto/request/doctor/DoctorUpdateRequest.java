package com.vetmanagement.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "Doctor id must be positive.")
    private Long id;

    @NotNull(message = "Please do not leave the name field empty !!!")
    private String name;

    private String phone;

    @NotNull(message = "Please do not leave the email field empty !!!")
    @Email(message = "Please enter a valid email address !!!")
    private String mail;

    private String address;

    private String city;
}
