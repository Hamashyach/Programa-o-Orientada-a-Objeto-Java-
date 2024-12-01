/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaopizzaria.DAO;

import gestaopizzaria.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> listarPedido() {
        return pedidos;
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizarPedido(Pedido pedido) {

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                return;
            }
        }
        System.out.println("Pedido não encontrado para atualização.");
    }

    public boolean excluirPedido(int id) {
        Pedido pedido = buscarPedidoPorId(id);
        if (pedido != null) {
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }
}
