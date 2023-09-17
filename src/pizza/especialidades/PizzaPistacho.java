package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

import javax.swing.*;
import java.util.List;

public class PizzaHawaiian extends Pizza {
    private static List<Topping> toppings;
    public PizzaHawaiian(String name, Topping... toppings) {
        super(name, toppings);
    }

    public static void precargarTopping(DefaultListModel modeloLista) {
        toppings.add(new Topping("Albahaca", 2.10));
        toppings.add(new Topping("Tomates Cherry", 3.30));
        toppings.add(new Topping("Ajo", 1.25));
    }
}
