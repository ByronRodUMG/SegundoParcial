package pizza.base;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private String name;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(String name, Topping... toppings) {
        this.name = name;
        for (Topping topping : toppings) {
            this.toppings.add(topping);
        }
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(int index) {
        this.toppings.remove(index);
    }

    public List<Topping> getToppings() {

        return Collections.unmodifiableList(new ArrayList<>(toppings));
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Pizza { Nombre: " + name + ", Toppings: " + toppings + " }";
    }

    public void prepare(DefaultListModel<String> model, JLabel lblTotal) {
        model.clear();
        model.addElement("Preparando..... " + name);
        model.addElement("Agregando toppings:");
        double total = 0; // Agrega una variable para calcular el costo total
        for (Topping topping : toppings) {
            model.addElement("- " + topping.getNombre() + " $" + topping.getPrecio());
            total += topping.getPrecio(); // Agrega el precio del topping al total
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        model.addElement("La Pizza esta lista!");
        model.addElement("Costo Total: $" + lblTotal.getText()); // Agrega el costo total al modelo
    }

}
