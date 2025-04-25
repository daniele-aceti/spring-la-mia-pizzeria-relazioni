package pizzeria.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzeria.spring_la_mia_pizzeria_crud.model.OfferteSpeciali;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.OfferteRepository;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Service
public class PizzaService {

    private final OfferteRepository offerteRepository;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, OfferteRepository offerteRepository) {
        this.pizzaRepository = pizzaRepository;
        this.offerteRepository = offerteRepository;
    }

    public List<Pizza> findPizzaList(String title) {
        List<Pizza> result;
         if (title != null && !title.isBlank()) {
            result = pizzaRepository.findByNomeContainingIgnoreCase(title);
        } else {
            result = pizzaRepository.findAll();
        }
        return result;
    }


    public Optional<Pizza> findPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }


    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }


    public Pizza update(Pizza pizza) {
        Optional<Pizza> optPizza = pizzaRepository.findById(pizza.getId());

        if(optPizza.isEmpty()) {
            throw new IllegalArgumentException("Impossibile aggiornare il libro senza l'id");
        }

        return pizzaRepository.save(pizza);
    }

    public void deleteById(Long id) {
        Pizza pizza = pizzaRepository.findById(id).get();
        for (OfferteSpeciali b : pizza.getOfferteSpeciali()) {
           offerteRepository.deleteById(b.getId());
        }
        pizzaRepository.deleteById(id);
    }

}
