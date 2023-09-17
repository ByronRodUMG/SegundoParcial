package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaMar extends Pizza {
    private static List<Topping> toppings = new ArrayList<>();
    public static double total = 0.0;
    public PizzaMar(String name, Topping... toppings) {
        super(name, toppings);
    }

    public static void precargarTopping(DefaultListModel modeloLista) {
        modeloLista.clear();

        toppings.add(new Topping("Camarones", 5.00));
        toppings.add(new Topping("Calamares", 4.50));
        toppings.add(new Topping("Mejillones", 3.80));
        toppings.add(new Topping("Salsa de Tomate", 1.80));

        // Añadir los toppings al modeloLista
        for (Topping topping : toppings) {
            modeloLista.addElement(topping);
            total += topping.getPrecio();
        }
    }
}
