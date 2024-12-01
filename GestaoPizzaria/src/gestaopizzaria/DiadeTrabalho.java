package gestaopizzaria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiadeTrabalho {

    private final LocalDate data;
    private final List<Pedido> pedidos;

    public DiadeTrabalho(LocalDate data) {
        this.data = data;
        this.pedidos = new ArrayList<>();
    }

    public LocalDate getData() {
        return data;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double calcularTotalVendas() {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            total += pedido.calcularTotal();
        }
        return total;
    }

    public void imprimirRelatorio() {
        System.out.println("Relatório do dia: " + data);
        System.out.println("Total de vendas: R$ " + calcularTotalVendas());
        System.out.println("Pedidos:");
        for (Pedido pedido : pedidos) {
            pedido.imprimir();
        }
    }
}
