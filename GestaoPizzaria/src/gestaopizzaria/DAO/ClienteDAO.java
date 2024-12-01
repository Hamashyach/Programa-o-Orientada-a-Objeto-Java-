/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaopizzaria.DAO;

import gestaopizzaria.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void listarClientes() {
        clientes.forEach(cliente -> cliente.imprimir());
    }

    public Cliente buscarClientePorId(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean Atualizar(int id, String nome, String contato, String endereco) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setContato(contato);
            cliente.setEndereco(endereco);
            return true;
        }
        return false;
    }

    public boolean excluirCliente(int id) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            System.out.println("O seguinte cliente foi excluido: ");
            cliente.imprimir();
            clientes.remove(cliente);
            return true;
        }
        return false;
    }
}
