package ru.artur.darkknight.controller;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artur.darkknight.exception.ConditionNotEnoughException;
import ru.artur.darkknight.exception.GoldNotEnoughException;
import ru.artur.darkknight.exception.TrainingNotPossible;
import ru.artur.darkknight.model.Char;
import ru.artur.darkknight.model.User;
import ru.artur.darkknight.model.enums.TrainingType;
import ru.artur.darkknight.service.CharService;
import ru.artur.darkknight.service.ConditionService;
import ru.artur.darkknight.service.UserService;
import ru.artur.darkknight.utils.HelpService;
import ru.artur.darkknight.utils.TimeUtil;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A controller that processes all requests related to training.
 * The controller has all the necessary services for character training, in particular:
 * a service {@link CharService} for the character that updates the status of the character in the database,
 * the condition service {@link ConditionService} updates the condition of the character in the database after a successful training,
 * userService {@link UserService} is necessary in order to get an authorized user, so that the application always was secured.
 *
 * @author Grudtsin Artur
 * @version 1.0
 */
@Controller
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private CharService charService;
    @Autowired
    private ConditionService conditionService;
    @Autowired
    private UserService userService;

    /**
     * This method mappes for requests of the form '/training' and redirects the user to the main page of the training.
     * The application uses Spring Security, so the user will not be able to bypass the system and get to the training page of another user.
     * It is for security reasons that each method requires an authorized user.
     *
     * @param principal  is required to get the authorized user.
     * @param exceptions it can be some exceptions can occurred in this method {@link GoldNotEnoughException}, {@link ConditionNotEnoughException}.
     *                   If this object is not null, exceptions will added to the model and will display on the view.
     * @param model      The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping
    public String trainingPage(Principal principal,
                               @RequestParam(value = "exceptions", required = false) Set<String> exceptions,
                               Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Char myChar = user.getaChar();
        List<Char> allHeroes = charService.getAllKnights();
        Date lastTrainingDate = myChar.getLastTrainingDate();
        Date now = new Date();
        if (lastTrainingDate != null) {
            String timeBetween = TimeUtil.getTimeBetween(now, DateUtils.addDays(lastTrainingDate, 1));
            int hour = Integer.parseInt(timeBetween.split(":")[0]);
            int minute = Integer.parseInt(timeBetween.split(":")[1]);
            int second = Integer.parseInt(timeBetween.split(":")[2]);
            if (hour > 0 || minute > 0 && second > 0)
                model.addAttribute("time", timeBetween);
            else model.addAttribute("trainingComplete", true);
        }
        if (exceptions != null)
            model.addAttribute("exceptions", exceptions);
        model.addAttribute("myChar", myChar);
        model.addAttribute("allHeroes", allHeroes);
        return "training";
    }

    /**
     * This method performs the training of the character.
     * Calls all the necessary methods and updates the state of the character.
     *
     * @param id            Id of the character need trained.
     * @param strengthValue The value of the strength {@link ru.artur.darkknight.model.Skills} that must be added to the character.
     * @param defenceValue  The value of the defence {@link ru.artur.darkknight.model.Skills} that must be added to the character.
     * @param staminaValue  The value of the stamina {@link ru.artur.darkknight.model.Skills} that must be added to the character.
     * @param model         The object needed to add parameters to the response.
     * @return the name of the view page.
     */
    @RequestMapping("/train_skills/**")
    public String trainSkills(@RequestParam("id") long id,
                              @RequestParam("strength") String strengthValue,
                              @RequestParam("defence") String defenceValue,
                              @RequestParam("stamina") String staminaValue,
                              Model model) {
        Set<String> exceptionSet = new HashSet<>();
        Char aChar = charService.getKnightById(id);
        List<Char> allHeroes = charService.getAllKnights();
        //Try to train every skill
        try {
            if (strengthValue != null && !strengthValue.equals(""))
                HelpService.trainHero(aChar, strengthValue, TrainingType.STRENGTH);
            if (defenceValue != null && !defenceValue.equals(""))
                HelpService.trainHero(aChar, defenceValue, TrainingType.DEFENCE);
            if (staminaValue != null && !staminaValue.equals(""))
                HelpService.trainHero(aChar, staminaValue, TrainingType.STAMINA);
            aChar.setLastTrainingDate(new Date());
        } catch (ConditionNotEnoughException conditionNotEnoughException) {
            exceptionSet.add(conditionNotEnoughException.getMessage());
        } catch (GoldNotEnoughException e) {
            exceptionSet.add(e.getMessage());
        } catch (TrainingNotPossible trainingNotPossible) {
            exceptionSet.add(trainingNotPossible.getMessage());
        } finally {
            conditionService.update(aChar.getCondition());
            charService.update(aChar);
        }
        if (exceptionSet.size() > 0) {
            model.addAttribute("exceptions", exceptionSet);
            return "redirect:/training";
        }
        model.addAttribute("myChar", aChar);
        model.addAttribute("allHeroes", allHeroes);
        return "redirect:/";
    }

    @RequestMapping("/train_skill/**")
    public String trainSkill(Principal principal,
                             @RequestParam("param") String trainingType,
                             Model model) {
        User user = userService.findByUsername(principal.getName());
        Char myChar = charService.getKnightById(user.getaChar().getId());
        Set<String> exceptionSet = new HashSet<>();
        try {
            HelpService.trainHero(myChar, "1", TrainingType.valueOf(trainingType.toUpperCase()));
        } catch (ConditionNotEnoughException conditionNotEnoughException) {
            exceptionSet.add(conditionNotEnoughException.getMessage());
        } catch (GoldNotEnoughException e) {
            exceptionSet.add(e.getMessage());
        } catch (TrainingNotPossible trainingNotPossible) {
            exceptionSet.add(trainingNotPossible.getMessage());
        } finally {
            conditionService.update(myChar.getCondition());
            charService.update(myChar);
        }
        if (exceptionSet.size() > 0) {
            model.addAttribute("exceptions", exceptionSet);
            return "redirect:/training";
        }
        model.addAttribute("myChar", myChar);
        return "redirect:/training";
    }
}
