package pizzeria.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;

public interface QuantitaPizzeRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByQuantitaPizza(Integer quantitaPizza);
}
