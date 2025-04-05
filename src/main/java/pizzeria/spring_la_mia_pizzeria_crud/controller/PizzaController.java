package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Controller
@RequestMapping

public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String primaPagina(Model model) {
        return "pizze/primaPagina";
    }

    @GetMapping("/loading")
    public String loading(Model model) {
        return "pizze/loading";
    }

    @GetMapping("/pizze")
    public String index(Model model,@RequestParam(name="keyword", required= false) String findPizza) {
        List<Pizza> result;
        if(findPizza != null && !findPizza.isBlank()){
            result = pizzaRepository.findByNomeContainingIgnoreCase(findPizza);
        }else{
            result = pizzaRepository.findAll();
        }
        model.addAttribute("pizzaList", result);
        return "pizze/index";
    }

    @GetMapping("pizze/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optionPizza = pizzaRepository.findById(id);
        if (optionPizza.isPresent()) {
            model.addAttribute("pizza", pizzaRepository.findById(id).get());
            return "pizze/show";
        }
        model.addAttribute("errorCause", "La pizza da te cercata con " + id + "non esiste");
        return "pizze/error";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "pizze/contatti";
    }

}
