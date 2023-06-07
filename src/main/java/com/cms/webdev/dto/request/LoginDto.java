package com.cms.webdev.dto.request;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString
public class LoginDto {
    private String email;
    private String username;
    private String password;
}
