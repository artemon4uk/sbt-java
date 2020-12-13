package ru.sbt.course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sbt.course.registration.DataBase;

@Controller
public class LoginController {
    private final AuditService auditService = new AuditService();

    private static final String AUDIT = "audit";
    private static final String REGISTRATION = "registration";
    private static final String LOGIN = "login";
    private static final String INDEX = "index";

    @GetMapping("/")
    public String login() {
        return LOGIN;
    }

    @GetMapping("/registration")
    public String registration() {
        return REGISTRATION;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        if (user.getName() == null) {
            return LOGIN;
        }
        boolean doesUserExist = DataBase.doesUserExist(user);
        boolean doesPasswordRight = DataBase.doesPasswordRight(user);
        addAttributesForLogin(model, doesUserExist, doesPasswordRight, user.getName());
        if (!doesUserExist) {
            auditService.addLog(user, "User doesn't exist");
            return LOGIN;
        }
        if (!DataBase.doesPasswordRight(user)) {
            auditService.addLog(user, "Wrong password");
            return LOGIN;
        }
        auditService.addLog(user, "Success");
        return INDEX;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        if (user.getName() == null || "".equals(user.getName())) {
            return LOGIN;
        }
        boolean usingExistLogin = DataBase.doesUserExist(user);
        boolean usingAdminLogin = "admin".equals(user.getName());

        if (usingExistLogin) {
            addAttributesForRegistration(model, true, "This user has already existed.");
            return REGISTRATION;
        }
        if (usingAdminLogin) {
            addAttributesForRegistration(model, true, "You can't use \"admin\" login.");
            return REGISTRATION;
        }
        DataBase.addUser(user);
        return LOGIN;
    }

    @GetMapping("/audit")
    public String audit(Model model) {
        model.addAttribute("audit", auditService.audit().toString());
        return AUDIT;
    }

    private void addAttributesForLogin(Model model, boolean doesUserExist, boolean doesPasswordRight, String username) {
        model.addAttribute("doesUserExist", doesUserExist);
        model.addAttribute("doesPasswordRight", doesPasswordRight);
        model.addAttribute("userName", username);
    }

    private void addAttributesForRegistration(Model model, boolean isError, String message) {
        model.addAttribute("isError", isError);
        model.addAttribute("message", message);
    }
}
