package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.OfferteSpeciali;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.IngredientiRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Controller
@RequestMapping

public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientiRepository ingredientiRepository;

    @GetMapping
    public String primaPagina(Model model) {
        return "pizze/primaPagina";
    }

    @GetMapping("/loading")
    public String loading(Model model) {
        return "pizze/loading";
    }

    @GetMapping("/pizze")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String findPizza) {
        List<Pizza> result;
        if (findPizza != null && !findPizza.isBlank()) {
            result = pizzaRepository.findByNomeContainingIgnoreCase(findPizza);
        } else {
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
        model.addAttribute("errorCause", "La pizza da te cercata con id " + id + " non esiste");
        return "pizze/error/error";
    }

    @GetMapping("/contatti")
    public String contatti() {
        return "pizze/contatti";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("listaIngredienti", ingredientiRepository.findAll());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaIngredienti", ingredientiRepository.findAll());
            return "pizze/create";
        }

        pizzaRepository.save(formPizza);

        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata!");
        return "redirect:/pizze";
    }

    @GetMapping("/modifica/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("modificaPizza", pizzaRepository.findById(id).get());
        model.addAttribute("listaIngredienti", ingredientiRepository.findAll());

        return "/pizze/modificaPizza";
    }

    @PostMapping("/modifica/{id}")
    public String update(
            @Valid @ModelAttribute("modificaPizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/pizze/modificaPizza";
        }
        pizzaRepository.save(formPizza);

        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Pizza pizza = pizzaRepository.findById(id).get();
        pizzaRepository.deleteById(id);

        return "redirect:/pizze";
    }


    @GetMapping("/{id}/offerte")
    public String editOfferte(@PathVariable Integer id, Model model) {
        OfferteSpeciali offerteSpeciali = new OfferteSpeciali();
        offerteSpeciali.setPizza(pizzaRepository.findById(id).get());
        model.addAttribute("offerte", offerteSpeciali);
        model.addAttribute("editMode", false);
        return "offerte/edit";
    }


    @GetMapping("/modificaAdmin")
    public String editAdmin(Model model) {
        List <Pizza> pizza = pizzaRepository.findAll();
        Pizza newPizza = new Pizza();
        model.addAttribute("newPizza", newPizza);
        model.addAttribute("pizza", pizza);

        return "pizze/adminModifica";
    }

    @PostMapping("/modificaAdmin")
    public String updateAdmmin(
            @Valid @ModelAttribute("modificaPizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/pizze/adminModifica";
        }
        pizzaRepository.save(formPizza);

        return "redirect:/pizze";
    }
     

}
