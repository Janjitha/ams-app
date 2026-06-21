package com.ams.service;

import com.ams.dto.ChangePasswordDto;
import com.ams.dto.TokenDto;
import com.ams.dto.UserRespDto;
import com.ams.model.User;
import com.ams.utility.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtility jwtUtility;

    // local encoder instance — same pattern as trainer's OfficerService
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /*
     * .requestMatchers(HttpMethod.POST, "/api/auth/login").authenticated()
     *
     * This ensures that if user request comes to login() method,
     * Spring already has checked username/password via Basic Auth and they are correct,
     * so we can ask for the username from Spring's Principal
     * */
    public TokenDto login(String username) {
        String token = jwtUtility.generateToken(username);
        return new TokenDto(username, token);
    }

    public String forgotPassword(ChangePasswordDto dto) {
        User user = userService.getByUsername(dto.username());
        user.setPassword(encoder.encode(dto.newPassword()));
        userService.saveUser(user);
        return "Password reset successfully";
    }

    public UserRespDto profile(String username) {
        User user = (User) userService.loadUserByUsername(username);
        return new UserRespDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
