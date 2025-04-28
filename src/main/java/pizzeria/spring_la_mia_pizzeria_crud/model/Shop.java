package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Inserire un valore corretto")
    @DateTimeFormat(pattern="yyy-MM-dd")
    private LocalDate dataDiCreazione;

    @OneToMany(mappedBy="shop")
    private List<RecordShop> recordShop;

    
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RecordShop> getRecordShop() {
        return recordShop;
    }
 
     public void setRecordShop(List<RecordShop> recordShop) {
        this.recordShop = recordShop;
    }

     public LocalDate getDataDiCreazione() {
         return dataDiCreazione;
     }

     public void setDataDiCreazione(LocalDate dataDiCreazione) {
         this.dataDiCreazione = dataDiCreazione;
     } 


}
