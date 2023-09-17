package pizza.especialidades;

import pizza.base.Pizza;
import pizza.base.Topping;

public class YoLaArmo extends Pizza {
    public YoLaArmo(String name, Topping... toppings) {
        super(name, toppings);
    }
}
