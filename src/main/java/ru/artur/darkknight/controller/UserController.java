package ru.artur.darkknight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.artur.darkknight.model.Knight;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.service.KnightService;
import ru.artur.darkknight.service.SecurityService;
import ru.artur.darkknight.service.UserService;
import ru.artur.darkknight.validator.UserValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * An additional controller, which is necessary to ensure the security of the application.
 * The controller allows to perform all manipulations on authorization and user authentication.
 * The controller performs registration and logon requests.
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private KnightService knightService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/")
    public String main() {
        return "redirect:/game";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Principal principal,
                               Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<String> errors = new ArrayList<>();
            for (ObjectError error:
                 allErrors) {
                errors.add(error.getCode());
            }
            model.addAttribute("errors", errors);
            return "registration";
        }
        Knight knight = Knight.initializeKnight(userForm.getUsername());
        userForm.setKnight(knight);

        knightService.save(knight);
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/game";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
