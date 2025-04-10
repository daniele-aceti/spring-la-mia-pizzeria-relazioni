package pizzeria.spring_la_mia_pizzeria_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzeria.spring_la_mia_pizzeria_crud.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
