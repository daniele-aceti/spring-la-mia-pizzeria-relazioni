package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pizzeria.spring_la_mia_pizzeria_crud.dto.RecordDtoView;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.model.RecordShop;
import pizzeria.spring_la_mia_pizzeria_crud.model.Shop;
import pizzeria.spring_la_mia_pizzeria_crud.repository.CarrelloRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.ShopRecordRepository;

@Controller
public class ShopController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private CarrelloRepository shopRepository;

    @Autowired
    private ShopRecordRepository recordShopRepository;

    @PostMapping("/addShop/{idPizza}")
    public String addShop(
            @PathVariable Long idPizza,
            Long idShop,
            Integer quantitaPizzaCarrello,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<RecordDtoView> carrello = recordShopRepository.findCarrelloView(idShop);
        Pizza pizza = pizzaRepository.findById(idPizza).get();
        for (RecordDtoView record : carrello) {
            if (pizza.getId().equals(record.getIdPizza())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Pizza non aggiunta, modifica la quantità nel carrello");
                return "redirect:/pizze";
            }
        }
        redirectAttributes.addFlashAttribute("successMessage", "La pizza è stata aggiunta nel carrello");
        Shop shop = shopRepository.findById(idShop).get();
        RecordShop recordShop = new RecordShop();
        recordShop.setPizza(pizza);
        recordShop.setShop(shop);
        recordShop.setQuantitaPizzaCarrello(quantitaPizzaCarrello);
        recordShopRepository.save(recordShop);
        return "redirect:/pizze";
    }

    @GetMapping("/showShop/{id}")
    public String showShop(@PathVariable Long id, Model model) {
        List<RecordDtoView> carrello = recordShopRepository.findCarrelloView(id);
        model.addAttribute("listaCarrello", carrello);
        Double sum = 0.0;
        for (RecordDtoView pizza : carrello) {
            if (pizza.getQuantitaPizza() >= pizza.getQuantitaPizzaCarrello()) {
                sum += (pizza.getPrezzo() * pizza.getQuantitaPizzaCarrello());
            } else {
                continue;
            }

        }
        model.addAttribute("prezzoCarrello", sum);
        return "carrello/shop";
    }

    @PostMapping("/deleteShop/{id}")
    public String delete(@PathVariable Long id) {
        recordShopRepository.deleteById(id);
        return "redirect:/showShop/1";
    }

    @PostMapping("/modificaShop/{id}")
    public String modificaShop(@PathVariable Long id,
            @RequestParam("quantitaPizzaCarrello") int nuovaQuantita,
            RedirectAttributes redirectAttributes) {

        RecordShop record = recordShopRepository.findById(id).get();

        if (record == null || record.getPizza() == null) {
            redirectAttributes.addFlashAttribute("errore", "Record o pizza non trovati!");
            return "redirect:/showShop/1";
        }

        Pizza pizza = record.getPizza(); // prendo la pizza associata al record
        Integer quantitaMagazzino = pizza.getQuantitaPizza();

        if (nuovaQuantita > quantitaMagazzino) {
            redirectAttributes.addFlashAttribute("errore", "Quantità: " + nuovaQuantita + " il valore inserito supera la disponibilità in magazzino!");
            return "redirect:/showShop/1";
        }

        redirectAttributes.addFlashAttribute("modifica", "Modifica eseguita la " + record.getPizza().getNome() + " ora ha quantità " + nuovaQuantita);
        record.setQuantitaPizzaCarrello(nuovaQuantita);
        recordShopRepository.save(record);

        return "redirect:/showShop/1";
    }
}
