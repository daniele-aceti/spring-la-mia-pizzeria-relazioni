package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.User;
import pizzeria.spring_la_mia_pizzeria_crud.repository.UserRepository;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/login")
    public String getMethodName() {
        return "pizze/login";
    }
    

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
        if(userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
            return "pizze/indexUser";
        }else{
            model.addAttribute("user", "Username o Password errati");
            return "pizze/login";
        }

    }

    @GetMapping("/user")
    public String nuovoUtente(Model model) {
        model.addAttribute("user", new User());
        return "pizze/registrazione";
    }

    @PostMapping("/user")
    public String storeUtente(@Valid @ModelAttribute("user") User formUser,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }

        userRepository.save(formUser);

        //redirectAttributes.addFlashAttribute("successMessage", "Pizza creata!");
        return "redirect:/login";
    }

}
