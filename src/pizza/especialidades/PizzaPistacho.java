package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaPistacho extends Pizza {
    private static List<Topping> toppings = new ArrayList<>();
    public static double total = 0.0;
    public PizzaPistacho(String name, Topping... toppings) {
        super(name, toppings);
    }

    public static void precargarTopping(DefaultListModel modeloLista) {
        modeloLista.clear();

        toppings.add(new Topping("Pistachos", 4.50));
        toppings.add(new Topping("Ricotta", 3.20));
        toppings.add(new Topping("Aceite de Oliva", 1.80));
        toppings.add(new Topping("Queso Parmesano", 2.50));

        // Añadir los toppings al modeloLista
        for (Topping topping : toppings) {
            modeloLista.addElement(topping);
            total += topping.getPrecio();
        }
    }
}
