package gestaopizzaria;

import Sabores.*;
import gestaopizzaria.DAO.ClienteDAO;
import gestaopizzaria.DAO.DiadeTrabalhoDAO;
import gestaopizzaria.DAO.PedidoDAO;
import gestaopizzaria.DAO.PizzaDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestaoPizzaria {

    private static int clienteIdCounter = 1;
    private static int pizzaIdCounter = 1;
    private static int pedidoIdCounter = 1;

    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final PizzaDAO pizzaDAO = new PizzaDAO();
    private static final PedidoDAO pedidoDAO = new PedidoDAO();
    private static final DiadeTrabalhoDAO diadeTrabalhoDAO = new DiadeTrabalhoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Criar Dia de Trabalho");
            System.out.println("2. Ver Relatórios de Dias de Trabalho");
            System.out.println("3. Acessar Menu da Pizzaria");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Digite a data do dia de trabalho (YYYY-MM-DD): ");
                    String dataStr = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataStr);
                    DiadeTrabalho diaDeTrabalho = new DiadeTrabalho(data);
                    diadeTrabalhoDAO.cadastrarDiaDeTrabalho(diaDeTrabalho);
                    System.out.println("Dia de trabalho criado com sucesso!");
                    break;

                case 2:

                    System.out.println("Relatórios de Dias de Trabalho:");
                    List<DiadeTrabalho> dias = diadeTrabalhoDAO.listarDiasDeTrabalho();
                    for (DiadeTrabalho dia : dias) {
                        dia.imprimirRelatorio();
                    }
                    break;

                case 3:

                    List<DiadeTrabalho> diasTrabalho = diadeTrabalhoDAO.listarDiasDeTrabalho();
                    if (diasTrabalho.isEmpty()) {
                        System.out.println("Não foram cadastrados dias de trabalho ainda!");
                        break;
                    }
                    menuPizzaria(scanner);
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void menuPizzaria(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu da Pizzaria ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Pizza");
            System.out.println("3. Criar Pedido");
            System.out.println("\n4. Listar Clientes");
            System.out.println("5. Listar Pizzas");
            System.out.println("6. Listar Pedidos");
            System.out.println("\n7. Excluir cliente");
            System.out.println("8. Excluir pizza");
            System.out.println("9. Excluir pedido");
            System.out.println("\n10. Atualizar pizza");
            System.out.println("11. Atualizar pedido");
            System.out.println("12. Atualizar Cliente");
            System.out.println("13. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o telefone do cliente: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Digite o endereço do cliente: ");
                    String endereco = scanner.nextLine();
                    Cliente cliente = new Cliente(clienteIdCounter++, nome, telefone, endereco);
                    clienteDAO.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:

                    System.out.print("Digite o tipo da pizza (Calabresa, Marguerita, etc.): ");
                    String tipoPizza = scanner.nextLine();
                    System.out.print("Digite o preço da pizza: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Digite o tamanho da pizza: ");
                    String tamanho = scanner.next();
                    Pizza pizza;
                    if (tipoPizza.equalsIgnoreCase("Calabresa")) {
                        pizza = new PizzaCalabresa(pizzaIdCounter++, preco, tamanho);
                    } else if (tipoPizza.equalsIgnoreCase("Frango")) {
                        pizza = new PizzaFrango(pizzaIdCounter++, preco, tamanho);
                    } else if (tipoPizza.equalsIgnoreCase("Marguerita")) {
                        pizza = new PizzaMarguerita(pizzaIdCounter++, preco, tamanho);
                    } else if (tipoPizza.equalsIgnoreCase("Vegetariana")) {
                        pizza = new PizzaVegetariana(pizzaIdCounter++, preco, tamanho);
                    } else {
                        System.out.println("Tipo de pizza não reconhecido.");
                        continue;
                    }
                    pizzaDAO.cadastrarPizza(pizza);
                    System.out.println("Pizza cadastrada com sucesso!");
                    break;

                case 3:

                    System.out.println("Digite o ID do cliente para o pedido:");
                    int clienteId = scanner.nextInt();
                    Cliente clientePedido = clienteDAO.buscarClientePorId(clienteId);

                    if (clientePedido != null) {
                        List<Pizza> pizzasPedido = new ArrayList<>();
                        System.out.print("Digite o número de pizzas que deseja adicionar ao pedido: ");
                        int numPizzas = scanner.nextInt();
                        for (int i = 0; i < numPizzas; i++) {
                            System.out.print("Digite o ID da pizza " + (i + 1) + ": ");
                            int pizzaId = scanner.nextInt();
                            Pizza pizzaPedido = pizzaDAO.buscarPizzaPorId(pizzaId);
                            if (pizzaPedido != null) {
                                pizzasPedido.add(pizzaPedido);
                            } else {
                                System.out.println("Pizza com ID " + pizzaId + " não encontrada.");
                            }
                        }

                        System.out.println("Digite o ID do dia de trabalho para o pedido:");
                        int diaId = scanner.nextInt();
                        DiadeTrabalho diaDeTrabalhoSelecionado = diadeTrabalhoDAO.buscarDiaDeTrabalhoPorId(diaId);
                        if (diaDeTrabalhoSelecionado != null) {
                            Pedido pedido = new Pedido(pedidoIdCounter++, clientePedido, pizzasPedido, diaDeTrabalhoSelecionado);
                            pedidoDAO.cadastrarPedido(pedido);
                            System.out.println("Pedido cadastrado com sucesso!");
                            pedido.imprimir();
                        } else {
                            System.out.println("Dia de trabalho não encontrado.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 4:

                    System.out.println("Clientes cadastrados:");
                    clienteDAO.listarClientes();
                    break;

                case 5:

                    System.out.println("Pizzas cadastradas:");
                    pizzaDAO.listarPizza();
                    break;

                case 6:

                    System.out.println("Pedidos cadastrados:");
                    for (Pedido pedidoList : pedidoDAO.listarPedido()) {
                        pedidoList.imprimir();
                    }
                    break;

                case 7:
                    System.out.println("Excluir cliente:");
                    System.out.println("Insira o ID do cliente a ser excluído:");
                    int idClienteExcluido = Integer.parseInt(scanner.nextLine());
                    clienteDAO.excluirCliente(idClienteExcluido);
                    break;

                case 8:
                    System.out.println("Excluir pizza:");
                    System.out.println("Insira o ID da pizza a ser excluída:");
                    int idPizzaExcluida = Integer.parseInt(scanner.nextLine());
                    pizzaDAO.excluirPizza(idPizzaExcluida);
                    break;

                case 9:
                    System.out.println("Excluir pedido:");
                    System.out.println("Insira o ID do pedido a ser excluído:");
                    int idPedidoExcluido = Integer.parseInt(scanner.nextLine());
                    pedidoDAO.excluirPedido(idPedidoExcluido);
                    break;

                case 10:
                    System.out.println("Atualizar pizza:");
                    System.out.println("Insira o ID do pizza a ser atualizada:");
                    int idPizzaAtualizada = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o preço da pizza: ");
                    double precoAtualizado = scanner.nextDouble();
                    System.out.print("Digite o tamanho da pizza: ");
                    String tamanhoAtualizado = scanner.next();

                    pizzaDAO.AtualizarPizza(idPizzaAtualizada, precoAtualizado, tamanhoAtualizado);
                    break;

                case 11:
                    System.out.println("Atualizar pedido:");
                    System.out.print("Digite o ID do pedido a ser atualizado: ");
                    int pedidoIdAtualizado = scanner.nextInt();
                    scanner.nextLine();

                    Pedido pedidoAtual = pedidoDAO.buscarPedidoPorId(pedidoIdAtualizado);

                    if (pedidoAtual != null) {
                        System.out.println("Pedido encontrado. Atualize os detalhes:");

                        System.out.print("Digite o novo ID do cliente para o pedido (ou 0 para manter o atual): ");
                        int novoClienteId = scanner.nextInt();
                        scanner.nextLine();
                        if (novoClienteId != 0) {
                            Cliente novoCliente = clienteDAO.buscarClientePorId(novoClienteId);
                            if (novoCliente != null) {
                                pedidoAtual.setCliente(novoCliente);
                            } else {
                                System.out.println("Cliente com ID " + novoClienteId + " não encontrado.");
                            }
                        }

                        List<Pizza> novasPizzas = new ArrayList<>();
                        System.out.print("Digite o número de pizzas que deseja adicionar ao pedido: ");
                        int numPizzas = scanner.nextInt();
                        for (int i = 0; i < numPizzas; i++) {
                            System.out.print("Digite o ID da pizza " + (i + 1) + ": ");
                            int pizzaId = scanner.nextInt();
                            Pizza pizzaPedido = pizzaDAO.buscarPizzaPorId(pizzaId);
                            if (pizzaPedido != null) {
                                novasPizzas.add(pizzaPedido);
                            } else {
                                System.out.println("Pizza com ID " + pizzaId + " não encontrada.");
                            }
                        }

                        System.out.print("Digite o ID do novo dia de trabalho para o pedido: ");
                        int novoDiaId = scanner.nextInt();
                        DiadeTrabalho novoDiaDeTrabalho = diadeTrabalhoDAO.buscarDiaDeTrabalhoPorId(novoDiaId);
                        if (novoDiaDeTrabalho != null) {
                            pedidoAtual.setDiadeTrabalho(novoDiaDeTrabalho);
                        } else {
                            System.out.println("Dia de trabalho com ID " + novoDiaId + " não encontrado.");
                        }

                        pedidoAtual.setPizzas(novasPizzas);

                        pedidoDAO.atualizarPedido(pedidoAtual);
                        System.out.println("Pedido atualizado com sucesso!");
                        pedidoAtual.imprimir();
                    } else {
                        System.out.println("Pedido com ID " + pedidoIdAtualizado + " não encontrado.");
                    }
                    break;

                case 12:
                    System.out.println("Atualizar Cliente: ");
                    System.out.println("Digite o id do cliente a ser atualizado: ");
                    int IdAtualizado = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do cliente: ");
                    String nomeAtualizado = scanner.nextLine();
                    System.out.println("Digite o contato do cliente: ");
                    String contatoAtualizado = scanner.nextLine();
                    System.out.println("Digite o endereço do cliente: ");
                    String enderecoAtualizado = scanner.nextLine();

                    clienteDAO.Atualizar(IdAtualizado, nomeAtualizado, contatoAtualizado, enderecoAtualizado);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 13:
                    System.out.println("Saindo do menu da pizzaria...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 13);
    }
}
