package formularios.frmPizza;

import pizza.base.*;
import pizza.especialidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class frmPizza {
    private JPanel borderPanel;
    private JPanel mainPanel;
    private JComboBox cbEspecialidad;
    private JComboBox cbTopping;
    private JTextField tfNombre;
    private JButton btnTopping;
    private JLabel lblTotal;
    private JList listaIngredientes;
    private JButton btnPreparar;
    private JList listaPreparar;
    private JRadioButton rbtnSmall;
    private JRadioButton rbtnMedium;
    private JRadioButton rbtnBig;
    private ButtonGroup g1 = new ButtonGroup();
    private double total;
    private double totalRadio;
    private DefaultListModel modeloLista = new DefaultListModel();
    private DefaultListModel modeloPreparar = new DefaultListModel();
    private List<Topping> ingredientes = new ArrayList<>();
    private List<String> especialidades = new ArrayList<>();

    public Container getBorderPanel() {
        return borderPanel;
    }

    private void cargarEspecialidad() {
        especialidades.add("Yo la armo");
        especialidades.add("Pizza Italiana");
        especialidades.add("Pizza Hawaiiana");
        especialidades.add("Pizza de Pistacho");
        especialidades.add("Pizza Verde");
        especialidades.add("Pizza de Mariscos");

        DefaultComboBoxModel model = new DefaultComboBoxModel(especialidades.toArray());
        cbEspecialidad.setModel(model);

        cbEspecialidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene la especialidad seleccionada
                String seleccion = (String) cbEspecialidad.getSelectedItem();
                // Carga los toppings de la especialidad seleccionada
                cargarToppingsEspecial(seleccion);
                rbtnSmall.setSelected(true);
            }
        });
    }


    private void cargarToppingsEspecial(String specialty) {
        modeloLista.clear(); // Limpiar el modelo de la lista
        total = 0.00; // Reiniciar el total

        // Revisa la especialidad seleccionada y carga los toppings correspondientes
        if ("Yo la armo".equals(specialty)) {
            // No se necesita hacer nada
        } else if ("Pizza Italiana".equals(specialty)) {
            PizzaItaliana.precargarTopping(modeloLista);
            total = PizzaItaliana.total;
        } else if ("Pizza Hawaiiana".equals(specialty)) {
            PizzaHawaiian.precargarTopping(modeloLista);
            total = PizzaHawaiian.total;
        } else if ("Pizza de Pistacho".equals(specialty)) {
            PizzaPistacho.precargarTopping(modeloLista);
            total = PizzaPistacho.total;
        } else if ("Pizza Verde".equals(specialty)) {
            PizzaVerde.precargarTopping(modeloLista);
            total = PizzaVerde.total;
        } else if ("Pizza de Mariscos".equals(specialty)) {
            PizzaMar.precargarTopping(modeloLista);
            total = PizzaMar.total;
        }
        listaIngredientes.setModel(modeloLista); // Actualiza el JList
        actualizarTotal(total);
    }


    private void cargarToppings() {
        ingredientes.add(new Topping("Aceitunas Verdes", 1.75));
        ingredientes.add(new Topping("Pimientos Rojos Asados", 2.30));
        ingredientes.add(new Topping("Jalapeños", 2.00));
        ingredientes.add(new Topping("Queso Feta", 3.75));
        ingredientes.add(new Topping("Chorizo", 4.25));
        ingredientes.add(new Topping("Champiñones", 2.75));
        ingredientes.add(new Topping("Espinacas", 2.20));
        ingredientes.add(new Topping("Cebolla Roja", 1.50));
        ingredientes.add(new Topping("Maíz", 1.90));
        ingredientes.add(new Topping("Aceite de Oliva", 1.25));
        ingredientes.add(new Topping("Salsa BBQ", 2.50));


        DefaultComboBoxModel model = new DefaultComboBoxModel(ingredientes.toArray());
        cbTopping.setModel(model);
    }

    public frmPizza() {
        g1.add(rbtnSmall);
        g1.add(rbtnMedium);
        g1.add(rbtnBig);
        cargarEspecialidad();
        cargarToppings();

        rbtnSmall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calcula el precio basado en el radio button seleccionado (e.g., pequeña)
                double price = calcularPrecio("small");
                actualizarTotal(price);
            }
        });

        rbtnMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calcular el precio basado en el radio button seleccionado (e.g., mediana)
                double price = calcularPrecio("medium");
                actualizarTotal(price);
            }
        });

        rbtnBig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calcular el precio basado en el radio button seleccionado (e.g., grande)
                double price = calcularPrecio("big");
                actualizarTotal(price);
            }
        });


        btnTopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) cbTopping.getSelectedItem();
                modeloLista.addElement(ingrediente); // Añade el objeto Topping al modelo
                listaIngredientes.setModel(modeloLista);
                total += ingrediente.getPrecio();
                lblTotal.setText(String.format("%.2f", total)); // Muestra el total con dos decimales
                rbtnSmall.setSelected(true);
            }
        });

        listaIngredientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Revisa si se hizo doble click
                    int indiceSeleccionado = listaIngredientes.getSelectedIndex();
                    if (indiceSeleccionado != -1) {
                        Topping removedTopping = (Topping) modeloLista.remove(indiceSeleccionado);
                        total -= removedTopping.getPrecio();
                        lblTotal.setText(String.format("%.2f", total));
                        rbtnSmall.setSelected(true);
                    }
                }
            }
        });

        btnPreparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pizzaName = tfNombre.getText().trim(); // Obtener el nombre de la pizza y quitar espacios en blanco
                if (pizzaName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para la pizza.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    Pizza pizza = new Pizza(pizzaName);
                    Topping topi;
                    for (int i = 0; i < listaIngredientes.getModel().getSize(); i++) {
                        topi = (Topping) listaIngredientes.getModel().getElementAt(i);
                        pizza.addTopping(topi);
                    }
                    if (pizza.getToppings().size() == 0) {
                        JOptionPane.showMessageDialog(null, "Debe agregar al menos un ingrediente.");
                    } else {
                        listaPreparar.setModel(modeloPreparar);
                        pizza.prepare(modeloPreparar, lblTotal);
                    }
                }
            }
        });

    }

    private double calcularPrecio(String size) {
        double currentTotal = total; // Obtener el precio actual

        if (size.equals("small")) {
            return currentTotal; // Mantener el precio si es pequeña
        } else if (size.equals("medium")) {
            return currentTotal + (currentTotal * 0.20); // Agregar 20% para mediana
        } else if (size.equals("big")) {
            return currentTotal + (currentTotal * 0.35); // Agregar 35% para grande
        } else {
            return 0.0; // Valor predeterminado
        }
    }

    private void actualizarTotal(double price) {
        // Actualizar el precio total basándonos en el radio button seleccionado
        totalRadio = price;
        lblTotal.setText(String.format("%.2f", totalRadio)); // Muestra el total con dos decimales
    }
}
