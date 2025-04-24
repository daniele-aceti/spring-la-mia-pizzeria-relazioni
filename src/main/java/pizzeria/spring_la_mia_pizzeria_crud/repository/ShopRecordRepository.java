package pizzeria.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pizzeria.spring_la_mia_pizzeria_crud.dto.RecordDtoView;
import pizzeria.spring_la_mia_pizzeria_crud.model.RecordShop;

public interface ShopRecordRepository extends JpaRepository<RecordShop, Long> {

    @Query(value = """
        SELECT pi.foto, pi.descrizione, pi.quantita_pizza, rs.quantita_pizza_carrello as quantitaPizzaCarrello,rs.id as idRecordShop, pi.id as pizzaId
        FROM pizze pi
        JOIN record_shop rs ON rs.pizza_id = pi.id
        WHERE rs.shop_id = ?1
        """, nativeQuery = true)

    //usato Text Blocks per concatenare la stringa (essendo molto lunga)
    List<RecordDtoView> findCarrelloView(Long id);

}
