package pizzeria.spring_la_mia_pizzeria_crud.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import pizzeria.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import pizzeria.spring_la_mia_pizzeria_crud.service.PizzaService;

@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    //BASE

    /*  @GetMapping
    public List<Pizza> index(@RequestParam(name="keyword", required=false)String keyword){
        List<Pizza> result;
        if (keyword != null && !keyword.isBlank()) {
            result = pizzaRepository.findByNomeContainingIgnoreCase(keyword);
        } else {
            result = pizzaRepository.findAll();
        }
        return  result; 
    }

    
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }


    @PutMapping("/{id}")
    public Pizza update(@PathVariable Long id, @RequestBody Pizza pizza) {
        //inserire eccezione deve essere presente id nel put
        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
       pizzaRepository.deleteById(id);
    } */
    //ADVANCED
    @GetMapping
    public ResponseEntity<List<Pizza>> index(@RequestParam(name = "keyword", required = false) String keyword) {
        List<Pizza> result;
        if (keyword != null && !keyword.isBlank()) {
            result = pizzaRepository.findByNomeContainingIgnoreCase(keyword);
        } else {
            result = pizzaRepository.findAll();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> create(@Valid @RequestBody Pizza pizza) {
        Pizza newPizza = pizzaRepository.save(pizza);
        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody Pizza entity) {
        if(!id.equals(entity.getId())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Pizza updatedPizza = pizzaRepository.save(entity);
        return new ResponseEntity<>(updatedPizza, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable Long id) {

        Optional<Pizza> optPizza = pizzaRepository.findById(id);

        if (optPizza.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);

    }

}
