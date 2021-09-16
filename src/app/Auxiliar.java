package app;
import java.util.Scanner;
import model.empregados.*;
import model.empresa.*;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class Auxiliar {
    public static String printHeader(String name) {
        return "============================================\n"
        +"=========== " + name + " ============\n" +
       "--------------------------------------------\n";
    }

    public static void limparConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void appMenu(Scanner entrada,int op, List<Empregado> listaDeEmpregados) {
        FolhaDePagamento folhaDePagamento = new FolhaDePagamento();
        System.out.println("============================================");
        System.out.println("Opção " +op+ " selecionada");
       /* if(op >=2 && op<=9 && listaDeEmpregados.isEmpty()){
            System.out.println("Não há nenhum empregado registrado no sistema.");
            return;
        }*/
        switch (op) {
            case 0:
                System.out.println("Obrigado por usar nosso sistesma");
                System.out.println(".");
                break;
            case 1:
                Configs.novoEmpregado(entrada, listaDeEmpregados);
                break;
            case 2:
                Configs.removerEmpregado(entrada, listaDeEmpregados);
                break;
            case 3:
                Configs.addCartaoDePonto(entrada, listaDeEmpregados);
                break;
            case 4:
                Configs.addRelatorioDeVendas(entrada, listaDeEmpregados);
                break;
            case 5:
                Configs.addTaxaDeServico(entrada, listaDeEmpregados);
                break;
            case 6:
                Configs.mudarInfoEmpregado(entrada, listaDeEmpregados);
                break;
            case 7:
                Configs.rodarFolhaDePagamento(entrada, listaDeEmpregados, folhaDePagamento);
                break;
            case 8:
                Configs.criarAgenda(entrada, folhaDePagamento);
                break;
            case 9:
                Configs.printTodosEmpregados(entrada, listaDeEmpregados, 1);
                break;
            default:
                break;
        }
    }

    public static String entradaNome(Scanner entrada) {
        System.out.println("--------------------------------------------");
        return Entradas.lerString(entrada, "Qual o nome do empregado ?\n");
    }

    public static String entradaEndereco(Scanner entrada) {
        System.out.println("--------------------------------------------");
        return Entradas.lerString(entrada, "Qual o endereço do empregado ?\n");
    }

    public static int entradaTipoEmpregado(Scanner entrada) {
        return Entradas.lerInt(entrada, "Qual o tipo do empregado a ser atribuído ?\n[1] Assalariado [2] Comissionado [3] Horista");
    }

    public static void empregadoViaTipo(Scanner entrada,int isNew ,String name,String endereco,int tipoDeEmpregado,Sindicato sindicato, Pagamento pagamento, List<Empregado> listaDeEmpregados,int numEmpregado) {
        Empregado empregado = null;
        if(isNew==0){
            empregado = listaDeEmpregados.get(numEmpregado);
        }
        String diaDoPagamento = "";
        int salario=0;
        switch (tipoDeEmpregado) {
            case 1:
                // Assalariado
                diaDoPagamento = "Mensal - Último dia do mês"; 
                salario = Entradas.lerInt(entrada,"Qual o salário do empregado ?");
                if(isNew == 1) {
                    empregado = new Assalariado(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salario);
                    listaDeEmpregados.add(empregado);
                    empregado.printEmpregadoInfo();
                }
                else { //Substituindo o 'antigo empregado' da lista
                    empregado = new Assalariado(empregado.getName(), empregado.getId(), empregado.getEndereco(), 1, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salario);
                    listaDeEmpregados.set(numEmpregado, empregado);
                }
                
                break;
            case 2:
                // Comissionado
                diaDoPagamento = "Quinzenal - Às sextas-feiras";
                salario = Entradas.lerInt(entrada,"Qual o salário do empregado ?");
                int comissao = Entradas.lerInt(entrada,"Qual a comissão do empregado ?");
                if (isNew == 1) {
                    empregado = new Comissionado(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salario, comissao, null);
                    listaDeEmpregados.add(empregado);
                }
                else{
                    //Substituindo o 'antigo empregado' da lista
                    empregado = new Comissionado(empregado.getName(), empregado.getId(), empregado.getEndereco(), 2, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salario, comissao, null);
                    listaDeEmpregados.set(numEmpregado, empregado);
                }
                break;
            case 3:
                //Horista
                diaDoPagamento = "Semanal - Às sextas-feiras";
                int salarioPorHora = Entradas.lerInt(entrada,"Qual o salário por hora do empregado ?");
                if(isNew==1){
                    empregado = new Horista(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salarioPorHora);
                    listaDeEmpregados.add(empregado);
                }
                else{
                    empregado = new Horista(empregado.getName(), empregado.getId(), empregado.getEndereco(), 3, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salarioPorHora);
                    //Substituindo o 'antigo empregado' da lista
                    listaDeEmpregados.set(numEmpregado, empregado);
                }
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
        if(isNew == 1){
            System.out.println("Empregado foi adicionado\nao sistema com sucesso" );
            System.out.println("--------------------------------------------\n");
        }
    }

    public static Sindicato entradaSindicatoInfos(Scanner entrada) {
        int op = Entradas.lerInt(entrada, "O seu empregado é membro do Sindicato ?\n[1] Sim [2] Não");
        Sindicato sindicato;
        if(op==1){
            System.out.println(printHeader("Membro do Sindicato"));
            int taxaMensal = Entradas.lerInt(entrada,"Digite a taxa mensal do Sindicato:");
            int taxaDeServico = Entradas.lerInt(entrada,"Digite a taxa de serviço do Sindicato");
            sindicato = new Sindicato(taxaMensal, taxaDeServico, UUID.randomUUID(), 1);
        } 
        else{
            sindicato = new Sindicato(0, 0, new UUID(0,0), 0);
        }
        return sindicato;
        }

    public static int estruturaFolhaDePagamento(int aux, int flag, String dataFormatada, List <Empregado> listaDeEmpregados,String mensagem) {
        if(flag==0){
            System.out.println(printHeader(mensagem));
            System.out.println("===== " + dataFormatada + " ====");
            System.out.println("--------------------------------------------\n");
            flag=1;
        }
        System.out.println(listaDeEmpregados.get(aux).printInfoPagamento());
        return flag;
    }
    
    public static Pagamento entradaPagamentoInfo(Scanner entrada) {
        System.out.println(printHeader("Informações de Pagamento"));
        System.out.println("Qual o método de pagamento do empregado ?");
        int metodoDePagamento = Entradas.lerInt(entrada,"[1] Em mãos [2] Via correspondência [3] Depósito em conta");
        System.out.println(printHeader("Informações Bancárias"));
        String banco = Entradas.lerString(entrada,"Qual o nome do Banco do seu empregado ?");
        int agencia = Entradas.lerInt(entrada,"Qual o número da agência ?");
        int conta = Entradas.lerInt(entrada,"Qual o número da conta ?");
        Pagamento pagamento = new Pagamento(metodoDePagamento, banco, agencia, conta);
        return pagamento;
    }
    public static void estruturaMudarEmpregadoInfo(int opt, Empregado empregado, Scanner entrada, List<Empregado> listaDeEmpregados, int numEmpregado) {
        switch(opt){
            case 1:
                System.out.println("Esse é o nome atual do empregado");
                System.out.println(empregado.getName());
            break;
            case 2:
                System.out.println("Esse é o endereço atual do empregado:");
                System.out.println(empregado.getEndereco());
            break;
            case 3:
                System.out.println("Esse é o tipo atual do empregado:");
                System.out.println(empregado.checarTipoDeEmpregado());
            break;
            case 4:
                System.out.println("Esse é o método de pagamento atual do empregado:");
                System.out.println(empregado.getPagamento().printMetodoDePagamento());
            break;
            case 5:
                System.out.println("Atualmente, o empregado");
                System.out.println(empregado.checarMembro() + " do sindicato.");
            break;
            case 6:
                System.out.println("Essa é a ID do sindicato do empregado:");
                System.out.println(empregado.getSindicato().getIdSindicato());
            break;
            case 7:
                System.out.println("Essa é a taxa de serviço atual do sindicato:");
                System.out.println(empregado.getSindicato().getTaxaDeServico());
            break;
        }
        if(opt != 0){
            int op = Entradas.lerInt(entrada,"Tem certeza que deseja alterar ?\n[1] Sim [2] Não");
            
            if(op == 1){
                switch(opt){
                    case 1:
                        mudarNome(entrada, empregado);
                    break;
                    case 2:
                        mudarEndereco(entrada, empregado);
                    break;
                    case 3:
                        mudarTipoDeEmpregado(entrada, empregado, listaDeEmpregados, numEmpregado);
                    break;
                    case 4:
                        mudarMetodoDePagamento(entrada, empregado);
                    break;
                    case 5:
                        mudarSindicato(entrada, empregado);
                    break;
                    case 6:
                        mudarID(entrada, empregado);
                    break;
                    case 7:
                        mudarTaxaServico(entrada, empregado);
                    break;
                }
            }
        }
    }

    public static void mudarNome(Scanner entrada, Empregado empregado) {
        System.out.println("============================================");
        empregado.setName(Entradas.lerString(entrada, "Digite o novo nome a ser atribuído"));
        System.out.println("Nome alterado com sucesso");
        System.out.println("--------------------------------------------\n");
    }

    public static void mudarEndereco(Scanner entrada, Empregado empregado) {
        empregado.setEndereco(Entradas.lerString(entrada, "Digite o novo endereço a ser atribuído:\n"));
        System.out.println("Endereço alterado com sucesso");
        System.out.println("--------------------------------------------\n");
    }
    public static void mudarTipoDeEmpregado(Scanner entrada, Empregado empregado, List<Empregado> listaDeEmpregados,int numEmpregado) {
        System.out.println("============================================");
        int opt = Entradas.lerInt(entrada,"Digite o novo tipo de empregado a ser atribuído:\n [1] Assalariado [2] Comissionado [3] Horista");
        if(opt == empregado.getTipoDeEmpregado()){
            System.out.println("O empregado já é do tipo selecionado, tente novamente.");
            System.out.println("--------------------------------------------\n");
        }
        else{
            empregadoViaTipo(entrada, 0, empregado.getName(), empregado.getEndereco(), empregado.getTipoDeEmpregado(), empregado.getSindicato(), empregado.getPagamento(), listaDeEmpregados, numEmpregado);
            System.out.println(empregado.printEmpregadoInfo());
            System.out.println("Empregado foi alterado\nno sistema com sucesso" );
            System.out.println("--------------------------------------------\n");
        }
    }

    public static void mudarMetodoDePagamento(Scanner entrada, Empregado empregado) {
        empregado.getPagamento().setMetodoDePagamento(Entradas.lerInt(entrada,"Escolha o novo método de pagamento\n[1] Em mãos [2] Via correspondência [3] Depósito em conta"));
        System.out.println("Método de pagamento alterado com sucesso");
        System.out.println("--------------------------------------------\n");
    }

    public static void mudarSindicato(Scanner entrada, Empregado empregado) {
        if(empregado.getSindicato().getMembro()==0) {
            System.out.println(printHeader("Membro do Sindicato"));
            empregado.getSindicato().setTaxaMensal(Entradas.lerInt(entrada,"Digite a taxa mensal do Sindicato:"));
            empregado.getSindicato().setTaxaDeServico(Entradas.lerInt(entrada,"Digite a taxa de serviço do Sindicato"));
            empregado.getSindicato().setMembro(1);
            empregado.getSindicato().setIdSindicato(UUID.randomUUID());
            System.out.println("Alteração feita com sucesso.\nAgora o empregado é membro do sindicato");
            System.out.println("--------------------------------------------\n");
       } else {
            empregado.getSindicato().setMembro(0);
            empregado.getSindicato().setIdSindicato(new UUID(0,0));
            System.out.println("Alteração feita com sucesso.\nAgora o empregado não é membro do sindicato");
            System.out.println("--------------------------------------------\n");
       } 
   }

    public static void mudarID(Scanner entrada, Empregado empregado) {
        // Mudar ID do sindicato
        UUID newIdSindicato = UUID.randomUUID();
        System.out.println("Nova ID do sindicato: " + newIdSindicato);
        System.out.println("--------------------------------------------");
        empregado.getSindicato().setIdSindicato(newIdSindicato);
        System.out.println("ID do sindicato alterada com sucesso");
        System.out.println("--------------------------------------------\n");
    }

    public static void mudarTaxaServico(Scanner entrada, Empregado empregado) {
         // Mudar taxa de serviço do sindicato
         empregado.getSindicato().setTaxaDeServico(Entradas.lerInt(entrada, "Digite o valor da nova taxa de serviço:"));
         System.out.println("Taxa de serviço alterada com sucesso");
         System.out.println("--------------------------------------------\n");
    }
}
