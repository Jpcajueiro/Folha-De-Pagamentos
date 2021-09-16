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
            System.out.println("[10] Limpar tela do console");
            System.out.println("...");
            op = Entradas.lerEntre(entrada, "", 0, 10);
            Auxiliar.appMenu(entrada, op, listaDeEmpregados);      
        }
    }
}
 