package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model;

public class Customer {

    private String id, name, email, password, score;

    public Customer(String id, String nombre, String correo, String password, String score) {
        this.id = id;
        this.name = nombre;
        this.email = correo;
        this.password = password;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
