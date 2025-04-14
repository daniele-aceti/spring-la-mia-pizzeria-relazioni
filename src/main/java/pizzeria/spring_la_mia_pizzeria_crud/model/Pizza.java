package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")//pizze è nome della mia tabella
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Inserire un valore corretto")
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank(message="Inserire un valore corretto")
    @Size(min=10 , message="Inserire almeno 10 caratteri")
    private String descrizione;

    @NotBlank(message="Inserire un valore corretto")
    @Column(nullable = false)
    private String foto;

    @NotBlank(message="Inserire un valore corretto")
    @Column(nullable = false)
    private String prezzo;


    @OneToMany(mappedBy="pizza")//questo nome pizza è lo stesso di ManyToOne di OfferteSpeciali Pizza pizza
    private List<OfferteSpeciali> offerteSpeciali;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public List<OfferteSpeciali> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OfferteSpeciali> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }
}
