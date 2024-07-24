package com.vetmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
