package io.github.rafaeelbe.bulking.controller;

import io.github.rafaeelbe.bulking.dto.UserRequestDto;
import io.github.rafaeelbe.bulking.dto.UserResponseDto;
import io.github.rafaeelbe.bulking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<UserResponseDto> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus (HttpStatus.OK)
    public UserResponseDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
