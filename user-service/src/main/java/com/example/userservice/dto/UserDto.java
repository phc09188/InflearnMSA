package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String email;

    private String name;

    private String pwd;

    private String userId;

    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
