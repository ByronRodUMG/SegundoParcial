package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaVerde extends Pizza {
    private static List<Topping> toppings = new ArrayList<>();
    public static double total = 0.0;
    public PizzaVerde(String name, Topping... toppings) {
        super(name, toppings);
    }

    public static void precargarTopping(DefaultListModel modeloLista) {
        modeloLista.clear();

        toppings.add(new Topping("Espinacas Frescas", 2.50));
        toppings.add(new Topping("Brócoli", 2.20));
        toppings.add(new Topping("Pimientos Verdes", 1.80));
        toppings.add(new Topping("Pepinillos", 2.00));

        // Añadir los toppings al modeloLista
        for (Topping topping : toppings) {
            modeloLista.addElement(topping);
            total += topping.getPrecio();
        }
    }
}
