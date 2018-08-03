package ru.artur.darkknight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.enums.CharType;
import ru.artur.darkknight.service.CharService;
import ru.artur.darkknight.service.SecurityService;
import ru.artur.darkknight.service.UserService;
import ru.artur.darkknight.validator.UserValidator;

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
    private CharService charService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

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
                               @RequestParam("class") String heroClass,
                               Model model) {
        logger.debug("The user with name: " + userForm.getUsername() + " try to register in the web app");

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<String> errors = new ArrayList<>();
            for (ObjectError error:
                 allErrors) {
                errors.add(error.getCode());
            }
            logger.error("Some errors are occurred " + errors);
            model.addAttribute("errors", errors);
            return "registration";
        }
        Char aChar = Char.initializeChar(userForm.getUsername());
        aChar.setType(CharType.valueOf(heroClass.toUpperCase()));
        userForm.setaChar(aChar);

        charService.save(aChar);
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        logger.debug("Registration of the user with name: " + userForm.getUsername() + " successful complete and user is redirect to his main page");

        return "redirect:/game";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
            logger.debug("The user can not be authenticate an authorized, because username or password is invalid");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            logger.debug("The user have been logged out successfully");
        }

        return "login";
    }
}
