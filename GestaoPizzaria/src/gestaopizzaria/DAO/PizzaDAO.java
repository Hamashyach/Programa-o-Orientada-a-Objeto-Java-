/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaopizzaria.DAO;

import gestaopizzaria.Pizza;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO {

    private final List<Pizza> pizzas = new ArrayList<>();

    public void cadastrarPizza(Pizza pizza) {
        pizzas.add(pizza);
        pizza.imprimir();
    }

    public void listarPizza() {
        pizzas.forEach(pizza -> pizza.imprimir());
    }

    public Pizza buscarPizzaPorId(int id) {
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }

    public boolean AtualizarPizza(int id, double preco, String tamanho) {
        Pizza pizza = buscarPizzaPorId(id);
        if (pizza != null) {
            pizza.setPreco(preco);
            pizza.setTamanho(tamanho);
            return true;
        }
        System.out.println("ERRO: Pizza 404!");
        return false;
    }

    public boolean excluirPizza(int id) {
        Pizza pizza = buscarPizzaPorId(id);
        if (pizza != null) {
            pizzas.remove(pizza);
            return true;
        }
        System.out.println("ERRO: Pizza 404!");
        return false;
    }
}
