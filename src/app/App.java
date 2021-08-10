package app;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import model.*;
import model.empregados.*;
import model.empresa.FolhaDePagamento;
import model.empresa.Pagamento;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        // input
        Sindicato sindicato = new Sindicato(200, 100, UUID.randomUUID(), 1);
        Pagamento pagamento = new Pagamento(1, "Banco do Brasil", 550, 999187180);
        String diaDoPagamentoAssalariado = "Mensal - Último dia do mês",diaDoPagamentoComissionado = "Quinzenal - Às sextas-feiras",diaDoPagamentoHorista = "Semanal - Às sextas-feiras";; 

        Empregado empregado = null;
        List<Empregado> listaDeEmpregados = new ArrayList<Empregado>();
        FolhaDePagamento folhaDePagamento = new FolhaDePagamento();

        //
        int op = -1;

        while(op!=0){
            System.out.println("============================================");
            System.out.println("=============== Menu Inicial ===============");
            System.out.println("--------------------------------------------");
            System.out.println("Escolha uma opção: ");
            System.out.println("[0] Sair");
            System.out.println("[1] Registrar um novo empregado");
            System.out.println("[2] Remover um empregado do sistema");
            System.out.println("[3] Lançar um 'cartão de ponto'");
            System.out.println("[4] Lançar um resultado de venda");
            System.out.println("[5] Lançar uma taxa de serviço");
            System.out.println("[6] Alterar detalhes de um empregado");
            System.out.println("[7] Rodar a folha de pagamento para hoje");
            System.out.println("[8] Criar nova agenda de pagamento");
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
                    //listaDeEmpregados.add(Configs.novoEmpregado(entrada));
                    empregado = new Empregado("Maselo", UUID.randomUUID(), "Rua Deide Costa", 1, sindicato, pagamento, diaDoPagamentoAssalariado);
                    listaDeEmpregados.add(empregado);
                    empregado = new Empregado("Joana", UUID.randomUUID(), "Rua Deide Lado", 2, sindicato, pagamento, diaDoPagamentoComissionado);
                    listaDeEmpregados.add(empregado);
                    empregado = new Empregado("Rayssa", UUID.randomUUID(), "Rua Deide Frente", 3, sindicato, pagamento, diaDoPagamentoHorista);
                    listaDeEmpregados.add(empregado);
                    //empregado = Configs.novoEmpregado(entrada);
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
                default:
                    break;
            }
        }
    }
}
 