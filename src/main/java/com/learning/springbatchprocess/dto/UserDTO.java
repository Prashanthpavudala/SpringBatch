package com.learning.springbatchprocess.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private String name;
    private Long mobileNo;
    private String email;
    private String password;
    private String role;
}
