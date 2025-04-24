package pizzeria.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pizzeria.spring_la_mia_pizzeria_crud.dto.RecordDtoView;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.model.RecordShop;
import pizzeria.spring_la_mia_pizzeria_crud.model.Shop;
import pizzeria.spring_la_mia_pizzeria_crud.repository.CarrelloRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.IngredientiRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.QuantitaPizzeRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.ShopRecordRepository;

@Controller
public class ShopController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientiRepository ingredientiRepository;

    @Autowired
    private QuantitaPizzeRepository quantitaPizzeRepository;

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
        boolean cercaPizza = false;
        for (RecordDtoView record : carrello) {
            if (pizza.getId().equals(record.getIdRecordShop())) {
                cercaPizza = true;

            }
        }
        if (cercaPizza) {
            redirectAttributes.addFlashAttribute("errorMessage", "pizza non aggiunta");
            return "redirect:/pizze";
        }
        Shop shop = shopRepository.findById(idShop).get();
        RecordShop recordShop = new RecordShop();
        recordShop.setPizza(pizza);
        recordShop.setShop(shop);
        recordShop.setQuantitaPizzaCarrello(quantitaPizzaCarrello);
        recordShopRepository.save(recordShop);
        return "redirect:/pizze";
    }

    @GetMapping("/showShop/{id}")
    public String showShop(@PathVariable Long id, Model model, Long idRecordShop) {
        List<RecordDtoView> carrello = recordShopRepository.findCarrelloView(id);
        model.addAttribute("listaCarrello", carrello);
        return "carrello/shop";
    }

    @PostMapping("/deleteShop/{id}")
    public String delete(@PathVariable Long id) {
        recordShopRepository.deleteById(id);
        return "redirect:/showShop/1";
    }

}
