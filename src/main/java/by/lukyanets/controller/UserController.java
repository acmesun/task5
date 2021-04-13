package by.lukyanets.controller;

import by.lukyanets.dto.UserDto;
import by.lukyanets.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationProvider authProvider;

    public UserController(UserService userService, AuthenticationProvider authProvider) {
        this.userService = userService;
        this.authProvider = authProvider;
    }

    @PostMapping("/register/api")
    public ModelAndView registerAction(@ModelAttribute("user") @Valid UserDto userDto) {
        userService.registerNewUserAccount(userDto);
        return new ModelAndView("redirect:/user/login/view", "user", userDto);
    }

    @GetMapping("/register/view")
    public ModelAndView registerView() {
        return new ModelAndView("registration", "user", new UserDto());
    }

    @PostMapping("/login/api")
    public ModelAndView loginApi(@ModelAttribute("user") @Valid UserDto userDto) {
        Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ModelAndView("redirect:/home", "user", userDto);
    }

    @GetMapping("/login/view")
    public ModelAndView login() {
        return new ModelAndView("login", "user", new UserDto());
    }
}
