package org.damon.st.producer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.damon.st.producer.dto.UserDto;
import org.damon.st.producer.exception.UsersException;
import org.damon.st.producer.mapstruct.UserMapper;
import org.damon.st.producer.service.UsersService;
import org.damon.st.producer.utils.ErrorUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = ErrorUtils.getErrorMessages(bindingResult);
            throw new UsersException("Person has not been created: " + errorMessages);
        }
        usersService.createUser(userMapper.toEntity(userDTO));
        return ResponseEntity.ok("User creation request accepted.");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserDto userDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = ErrorUtils.getErrorMessages(bindingResult);
            throw new UsersException("Person has not been updated: " + errorMessages);
        }
        usersService.updateUser(userMapper.toEntity(userDTO));
        return ResponseEntity.ok("User update request accepted.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody @Valid UserDto userDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = ErrorUtils.getErrorMessages(bindingResult);
            throw new UsersException("Person has not been deleted: " + errorMessages);
        }
        usersService.deleteUser(userMapper.toEntity(userDTO));
        return ResponseEntity.ok("User deletion request accepted.");
    }
}