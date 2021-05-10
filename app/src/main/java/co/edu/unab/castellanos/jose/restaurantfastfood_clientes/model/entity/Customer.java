package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

public class Customer {

    private String id, name, email, urlFoto, dir, phone;
    private int score;

    public Customer() {

    }

    public Customer(String name, String email, String dir, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dir = dir;
        this.phone = phone;
        this.urlFoto = "";
        this.score = 0;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("url_foto")
    public String getUrlFoto() {
        return urlFoto;
    }

    @PropertyName("url_foto")
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
