package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaItaliana extends Pizza {
    private static List<Topping> toppings = new ArrayList<>();
    public static double total = 0.0;
    public PizzaItaliana(String name, Topping... toppings) {
        super(name, toppings);
    }

    public static void precargarTopping(DefaultListModel modeloLista) {
        modeloLista.clear();

        toppings.add(new Topping("Mozzarella", 2.50));
        toppings.add(new Topping("Aceitunas Negras", 1.80));
        toppings.add(new Topping("Champiñones", 2.20));
        toppings.add(new Topping("Pimiento Rojo", 1.90));

        // Añadir los toppings al modeloLista
        for (Topping topping : toppings) {
            modeloLista.addElement(topping);
            total += topping.getPrecio();
        }
    }
}
