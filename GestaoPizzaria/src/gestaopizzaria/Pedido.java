/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaopizzaria;

import java.util.List;

public class Pedido {

    private int id;
    private Cliente cliente;
    private List<Pizza> pizzas;
    private DiadeTrabalho diadeTrabalho;

    public Pedido(int id, Cliente cliente, List<Pizza> pizzas, DiadeTrabalho diadeTrabalho) {
        this.id = id;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.diadeTrabalho = diadeTrabalho;
        diadeTrabalho.adicionarPedido(this);
    }

    public double calcularTotal() {
        double precoTotal = 0.0;
        for (Pizza pizza : pizzas) {
            precoTotal += pizza.getPreco();
        }
        return precoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizza) {
        this.pizzas = pizza;
    }

    public void imprimir() {
        System.out.println("Pedido ID: " + id);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getContato());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Pizzas no pedido:");

        for (Pizza pizza : pizzas) {
            System.out.println(" - " + pizza.getNome() + " (Tamanho: " + pizza.getTamanho() + ", Preço: R$ " + pizza.getPreco() + ")");
        }

        System.out.printf("Total do pedido: R$ %.2f%n", calcularTotal());
    }

    public DiadeTrabalho getDiadeTrabalho() {
        return diadeTrabalho;
    }

    public void setDiadeTrabalho(DiadeTrabalho diadeTrabalho) {
        this.diadeTrabalho = diadeTrabalho;
    }

}
