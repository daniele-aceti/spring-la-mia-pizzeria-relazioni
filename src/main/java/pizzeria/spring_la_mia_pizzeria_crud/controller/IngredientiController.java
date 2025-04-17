package pizzeria.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.Ingredienti;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.IngredientiRepository;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    @GetMapping
    public String ingredienti(Model model) {
        model.addAttribute("listaIngredienti", ingredientiRepository.findAll());
        model.addAttribute("creaIngredienti", new Ingredienti());
        return "/ingredienti/index";
    }

    @PostMapping("/create")
    public String postMethodName(@Valid @ModelAttribute("creaIngredienti") Ingredienti ingredienti,
            BindingResult bindingResult,
            Model model) {

        ingredientiRepository.save(ingredienti);

        return "redirect:/ingredienti";

    }

     @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
           Ingredienti ingredienti = ingredientiRepository.findById(id).get();

        for (Pizza pizza : ingredienti.getPizza()) {
            pizza.getIngredienti().remove(ingredienti);
        }

        ingredientiRepository.deleteById(id);
        return "redirect:/ingredienti";
    }

}
