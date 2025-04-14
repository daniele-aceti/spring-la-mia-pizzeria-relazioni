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
import pizzeria.spring_la_mia_pizzeria_crud.model.OfferteSpeciali;
import pizzeria.spring_la_mia_pizzeria_crud.repository.OfferteRepository;

@Controller
@RequestMapping("/offerte")
public class OfferteController {

    @Autowired
    private OfferteRepository repository;

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("offerte") OfferteSpeciali formOfferte,
            BindingResult bindingResult,
            Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", false);
            model.addAttribute("offerte", formOfferte);
            return "/offerte/edit";
        }


        repository.save(formOfferte);

        return "redirect:/pizze/" + formOfferte.getPizza().getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable ("id") Long id , Model model) {
        OfferteSpeciali offerte = repository.findById(id).get();
        model.addAttribute("offerte", offerte);
        model.addAttribute("editMode", true);
        return "/offerte/edit";
    }

    @PostMapping("/{id}/edit")
    public String doEdit(@Valid @ModelAttribute("offerte") OfferteSpeciali offerte,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            model.addAttribute("offerte", offerte);
            return "/offerte/edit";
        }



        repository.save(offerte);

        return "redirect:/pizze/" + offerte.getPizza().getId();
    }
}
