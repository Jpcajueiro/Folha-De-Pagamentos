package app;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import model.empregados.*;
import model.empresa.FolhaDePagamento;
import model.empresa.Pagamento;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        Empregado empregado = null;
        List<Empregado> listaDeEmpregados = new ArrayList<Empregado>();
        FolhaDePagamento folhaDePagamento = new FolhaDePagamento();
        /* input
        Sindicato sindicato = new Sindicato(200, 100, UUID.randomUUID(), 1);
        Pagamento pagamento = new Pagamento(1, "Banco do Brasil", 550, 23503780);
        Pagamento pagamento2 = new Pagamento(2, "Itaú", 123, 67869042);
        Pagamento pagamento3 = new Pagamento(3, "Sincred", 479, 19828754);
        String diaDoPagamentoAssalariado = "Mensal - Último dia do mês",diaDoPagamentoComissionado = "Quinzenal - Às sextas-feiras",diaDoPagamentoHorista = "Semanal - Às sextas-feiras";
        empregado = new Assalariado("Marcelo Alves", UUID.randomUUID(), "Rua José Bonifácio", 1, sindicato, pagamento, diaDoPagamentoAssalariado, 1050);
        listaDeEmpregados.add(empregado);
        empregado = new Horista("Larissa Manoela", UUID.randomUUID(), "Walt Disney", 3, sindicato, pagamento2, diaDoPagamentoHorista, 35);
        listaDeEmpregados.add(empregado);
        empregado = new Comissionado("Rayssa Leal", UUID.randomUUID(), "Rua Coaracy da Mata", 2, sindicato, pagamento3, diaDoPagamentoComissionado, 1050, 50, null);
        listaDeEmpregados.add(empregado);
        empregado = new Horista("Joaquim Fênix", UUID.randomUUID(), "Gotham City", 3, sindicato, pagamento, diaDoPagamentoHorista, 40);
        listaDeEmpregados.add(empregado);*/
        
        int op = -1;

        while(op!=0){
            System.out.println("============================================");
            System.out.println("=============== Menu Inicial ===============");
            System.out.println("--------------------------------------------");
            System.out.println("Escolha uma opção: ");
            System.out.println("[0] Sair");
            System.out.println("[1] Registrar um novo empregado");
            System.out.println("[2] Remover um empregado do sistema");
            System.out.println("[3] Lançar um cartão de ponto");
            System.out.println("[4] Lançar um resultado de venda");
            System.out.println("[5] Lançar uma taxa de serviço");
            System.out.println("[6] Alterar detalhes de um empregado");
            System.out.println("[7] Rodar a folha de pagamento para hoje");
            System.out.println("[8] Criar nova agenda de pagamento");
            System.out.println("[9] Lista de todos empregados do sistema");
            System.out.println("...");
            op = Entradas.lerInt(entrada);
            switch (op) {
                case 0:
                    System.out.println("Obrigado por usar nosso sistesma");
                    System.out.println("..");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".");
                    break;
                case 1:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 1 selecionada");
                    
                    empregado = Configs.novoEmpregado(entrada);
                    listaDeEmpregados.add(empregado);
                    break;
                case 2:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 2 selecionada");;
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.");
                    else {
                        Configs.removerEmpregado(entrada, listaDeEmpregados);
                    }
                    break;
                case 3:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 3 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.\n Não há como adicionar um 'cartão de ponto'");
                    else Configs.addCartaoDePonto(entrada, listaDeEmpregados);
                    break;
                case 4:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 4 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.\n Não há como adicionar um 'relatorio de vendas'");
                    else Configs.addRelatorioDeVendas(entrada, listaDeEmpregados);
                    break;
                case 5:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 5 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.\n Não há como adicionar uma 'taxa de serviço'");
                    else Configs.addTaxaDeServico(entrada, listaDeEmpregados);
                    break;
                case 6:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 6 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.\n Não há como alterar os detalhes de ninguém");
                    else Configs.mudarInfoEmpregado(entrada, listaDeEmpregados);
                    break;
                case 7:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 7 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.");
                    else Configs.rodarFolhaDePagamento(entrada, listaDeEmpregados, folhaDePagamento);
                    break;
                case 8:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 8 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.");
                    else Configs.criarAgenda(entrada, folhaDePagamento);
                    break;
                case 9:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("============================================");
                    System.out.println("Opção 9 selecionada");
                    if(listaDeEmpregados.isEmpty()) System.out.println("Não há nenhum empregado registrado no sitema.\n Não há como alterar os detalhes de ninguém");
                    else Configs.printTodosEmpregados(entrada, listaDeEmpregados, 1);
                    break;
                default:
                    break;
            }
        }
    }
}
 