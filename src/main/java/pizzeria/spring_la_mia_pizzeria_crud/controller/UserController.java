package pizzeria.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pizzeria.spring_la_mia_pizzeria_crud.repository.UserRepository;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;
 
}
