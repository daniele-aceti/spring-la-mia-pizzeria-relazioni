package pizzeria.spring_la_mia_pizzeria_crud.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    public Pizza update(Pizza book) {
        Optional<Pizza> optBook = pizzaRepository.findById(book.getId());

        if(optBook.isEmpty()) {
            throw new IllegalArgumentException("Impossibile aggiornare il libro senza l'id");
        }

        return pizzaRepository.save(book);
    }

    

}
