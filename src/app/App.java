package app;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import model.empregados.*;
import model.empresa.Pagamento;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        List<Empregado> listaDeEmpregados = new ArrayList<Empregado>();
        // APENAS PARA TESTAR
        Empregado empregado;
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
        listaDeEmpregados.add(empregado);
        //
        int op = -1;
        while(op!=0){
            //Configs.printTodosEmpregados(entrada, listaDeEmpregados, 1);
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
            op = Entradas.lerEntre(entrada, "", 0, 9);
            //Auxiliar.limparConsole();
            Auxiliar.appMenu(entrada, op, listaDeEmpregados);      
        }
    }
}
 