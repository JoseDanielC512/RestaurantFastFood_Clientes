package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.network;

public interface CallBackApp<T> {

    void correct(T respuesta);

    void error(Exception error);
}
