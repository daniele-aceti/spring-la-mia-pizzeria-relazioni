package pizzeria.spring_la_mia_pizzeria_crud.dto;

public class RecordDtoView {
    private String foto;
    private String descrizione;
    private int quantitaPizza;
    private int quantitaPizzaCarrello;

    public RecordDtoView(String foto, String descrizione, int quantitaPizza, int quantitaPizzaCarrello){
        this.foto = foto;
        this.descrizione= descrizione;
        this.quantitaPizza = quantitaPizza;
        this.quantitaPizzaCarrello = quantitaPizzaCarrello;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantitaPizza() {
        return quantitaPizza;
    }

    public void setQuantitaPizza(int quantitaPizza) {
        this.quantitaPizza = quantitaPizza;
    }

    public int getQuantitaPizzaCarrello() {
        return quantitaPizzaCarrello;
    }

    public void setQuantitaPizzaCarrello(int quantitaPizzaCarrello) {
        this.quantitaPizzaCarrello = quantitaPizzaCarrello;
    }

   
}
