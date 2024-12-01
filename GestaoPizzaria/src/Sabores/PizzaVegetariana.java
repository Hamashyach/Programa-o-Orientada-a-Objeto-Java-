package Sabores;

import gestaopizzaria.Pizza;

public class PizzaVegetariana extends Pizza {

    public PizzaVegetariana(int id, double preco, String tamanho) {
        super(id, "Pizza Vegetariana", preco, tamanho, "Pizza com mussarela, brocolis, milho, tomate e palmito");
    }

    @Override
    public void imprimir() {
        super.imprimir();
    }
}
