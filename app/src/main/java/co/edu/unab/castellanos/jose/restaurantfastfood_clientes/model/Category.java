package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model;

public class Category {

    private String nombre, texto, url_img;

    public Category(String nombre, String texto, String url_img) {
        this.nombre = nombre;
        this.texto = texto;
        this.url_img = url_img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
