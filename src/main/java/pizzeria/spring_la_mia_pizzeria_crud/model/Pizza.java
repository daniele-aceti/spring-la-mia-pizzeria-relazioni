package pizzeria.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")//pizze è nome della mia tabella
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Inserire un valore corretto")
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank(message="Inserire un valore corretto")
    @Size(min=10 , message="Inserire almeno 10 caratteri")
    private String descrizione;

    @NotBlank(message="Inserire un valore corretto")
    @Column(nullable = false)
    private String foto;

    @NotNull(message="Inserire un valore corretto")
    @Min(value=5)
    @Column(nullable = false)
    private Double prezzo;


    @OneToMany(mappedBy="pizza")//questo nome pizza è lo stesso di ManyToOne di OfferteSpeciali Pizza pizza
    private List<OfferteSpeciali> offerteSpeciali;

    @ManyToMany()
    @JoinTable(
        name = "pizza_ingredienti",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredienti_id")
    )
    private List<Ingredienti> ingredienti;//questo nome è quello del mapping


    public List<Ingredienti> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingredienti> ingredienti) {
        this.ingredienti = ingredienti;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<OfferteSpeciali> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OfferteSpeciali> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }
}
