package com.ams.controller;

import com.ams.dto.UserDto;
import com.ams.dto.UserRespDto;
import com.ams.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * In controller if you are creating REST APIs
 * then add
 * @RestController annotation which is a combo of
 * @Controller & @ResponseBody
 * But if you are using this controller to load java UI(jsp or Thymeleaf)
 * then use only @Controller
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public UserRespDto addUser(@Valid @RequestBody UserDto dto) {
        return userService.addUser(dto);
    }

    @GetMapping("/all")
    public List<UserRespDto> getAll() {
        return userService.getAll();
    }

    @PutMapping("/update/{id}")
    public UserRespDto updateUser(@PathVariable int id,
                                  @Valid @RequestBody UserDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

