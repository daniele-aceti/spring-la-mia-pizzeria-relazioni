package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;



@Controller
@RequestMapping("/")

public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/home")
    public String primaPagina(Model model) {
        return "pizze/primaPagina";
    }

    @GetMapping("/loading")
    public String loading(Model model) {
        return "pizze/loading";
    }

    @GetMapping("/pizze")
    public String index(Model model) {
        List<Pizza> result = pizzaRepository.findAll();
        model.addAttribute("pizzaList", result);
        return "pizze/index";
    }

    @GetMapping("pizze/{id}")
    public String findPizza(@PathVariable("id")Integer id,Model model) {
        List<Pizza> result = pizzaRepository.findAll();
        for(Pizza pizzaId : result){
            if(pizzaId.getId().equals(id)){
                model.addAttribute("pizzaId", result);
            }
        }
        return "pizze/dettaglioPizza";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "pizze/contatti";
    }
    
    
}
