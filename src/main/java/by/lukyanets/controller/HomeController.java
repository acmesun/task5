package by.lukyanets.controller;

import by.lukyanets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private UserService service;

    @GetMapping("/home")
    public ModelAndView homePage(Model model) {
        return new ModelAndView("home", "users", service.listAllUsers());
    }

    @PostMapping("/home/update")
    public String unblockUsers(@RequestBody MultiValueMap<String, String> formData) {
        List<String> listFromData = formData.get("Value");
        Set<String> checkedEmails = formData.keySet();
        checkedEmails.remove("Value");
        String act = listFromData.get(0);
        boolean logoutUser = false;
        switch (act) {
            case "Block":
                service.blockUserAccounts(checkedEmails);
                logoutUser = true;
                break;
            case "Unblock":
                service.unblockUserAccounts(checkedEmails);
                break;
            case "Delete":
                service.deleteUserAccounts(checkedEmails);
                logoutUser = true;
                break;
        }
        return logoutUser && isCurrentUserIn(checkedEmails) ? "redirect:/logout" : "redirect:/home";
    }

    private boolean isCurrentUserIn(Set<String> emails) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentEmail = user.getUsername();
        return emails.contains(currentEmail);
    }
}
