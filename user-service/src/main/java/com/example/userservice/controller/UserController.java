package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Environment env;


    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = userService.createUser(mapper.map(user, UserDto.class));
        return ResponseEntity.ok(mapper.map(userDto, ResponseUser.class));
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v ->{
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUsers(@PathVariable(name = "userId")String userId){
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser returnValue = new ModelMapper().map(userDto,ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }


    @GetMapping("/health-check")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok(String.format("It's Working in User Service" +
                        ", port(local.server.port)=" +env.getProperty("local.server.port") +
                ", port(server.port)=" +env.getProperty("server.port") +
                ", token secret=" +env.getProperty("token.secret")+
                ", token expiration time=" +env.getProperty("token.expiration_time")));
    }
}
