package com.cms.webdev.dto.response;

import com.cms.webdev.dto.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

@Accessors(chain = true)
@Data
@ToString
public class UserDto extends BaseDto<Integer> {
    @NotBlank(message = "{email.empty.message}")
    @Email(message = "{email.not-valid.message}")
    private String email;

    @Size(min = 6, message = "{password.size.message}")
    @NotBlank(message = "{password.empty.message}")
    private String password;

    @NotBlank(message = "{mobileNumber.empty.message}")
    @Size(min = 10,max=12, message = "{mobileNumber.size.message}")
    private String mobileNumber;

    @NotBlank(message = "{name.empty.message}")
    private String name;

    @NotBlank(message = "{username.empty.message}")
    private String username;

}
