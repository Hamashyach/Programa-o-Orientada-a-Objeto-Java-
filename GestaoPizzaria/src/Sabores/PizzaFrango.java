package Sabores;

import gestaopizzaria.Pizza;

public class PizzaFrango extends Pizza {

    public PizzaFrango(int id, double preco, String tamanho) {
        super(id, "Frango com catupiry", preco, tamanho, "Pizza de frango, mussarela e catupiry");
    }

    @Override
    public void imprimir() {
        super.imprimir();
    }

}
