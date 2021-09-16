package app;
import model.empregados.*;
import java.util.*;
import model.empresa.*;
import java.util.Scanner;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.time.temporal.TemporalAdjusters;

public class Configs {
    
    public static void novoEmpregado(Scanner entrada, List<Empregado> listaDeEmpregados) {
        Sindicato sindicato;
        Pagamento pagamento;
        String name = Auxiliar.entradaNome(entrada);
        String endereco = Auxiliar.entradaEndereco(entrada);
        sindicato = Auxiliar.entradaSindicatoInfos(entrada);
        pagamento = Auxiliar.entradaPagamentoInfo(entrada);
        int tipoDeEmpregado = Auxiliar.entradaTipoEmpregado(entrada);
        Auxiliar.empregadoViaTipo(entrada, 1, name, endereco, tipoDeEmpregado, sindicato, pagamento, listaDeEmpregados, 0);
    }

    public static void removerEmpregado(Scanner entrada, List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,1);
        int n = Entradas.lerInt(entrada,"Digite o número correspondente ao empregado a ser removido:");
        String name = listaDeEmpregados.get(n).getName();
        listaDeEmpregados.remove(n);
        System.out.println("Empregado (" + name + ") foi removido do sistema com sucesso" );
    }

    public static void printTodosEmpregados(Scanner entrada, List<Empregado> listaDeEmpregados, int op) {
        int n = 0;
        // op = 0 Printa tudo , op  = 1 basico, op = qlqr coisa Printa pagamento
        Auxiliar.printHeader("Lista de Empregados");
        for(Empregado empregado : listaDeEmpregados){
            System.out.println("============================================");
            System.out.println("==================== "+n+" =====================");
            System.out.println("--------------------------------------------");
            if(op==1)System.out.println(empregado.printInfoBasica());
            else if(op==0) System.out.println(empregado.printEmpregadoInfo());
            else System.out.println(empregado.printInfoPagamento());
            n++;
        }
    }

    public static void addCartaoDePonto(Scanner entrada, List<Empregado> listaDeEmpregados) {
        Predicate<Empregado> ehHorista = horistaEmpregado -> horistaEmpregado instanceof Horista;
        List<Empregado> horistaEmpregados = listaDeEmpregados.stream().filter(ehHorista).collect(Collectors.toList()); 

        if(horistaEmpregados.isEmpty()){
            System.out.println("Não há empregados horistas cadastrados no sistema");
        }else{
            printTodosEmpregados(entrada, horistaEmpregados,1);
            int j = Entradas.lerInt(entrada,"Digite o número correspondente ao empregado\ncujo cartão de ponto será adicionado:");
            Horista empregadoHorista = (Horista) horistaEmpregados.get(j);
            LocalTime horarioEntrada = Entradas.lerHorario(entrada, "Insira o horário de entrada do funcionário:");
            LocalTime horarioSaida = Entradas.lerHorario(entrada, "Insira o horário de saída do funcionário:");
            System.out.println("Agora, insira a data :");
            LocalDate data = Entradas.lerData(entrada);
            CartaoDePonto cartaoDePonto = new CartaoDePonto(data, horarioEntrada, horarioSaida);
            empregadoHorista.getCartaoDePonto().add(cartaoDePonto);
            System.out.println("Cartão de ponto adicionado com sucesso");
            System.out.println("--------------------------------------------\n");
            return;
        }
    }

    public static void addRelatorioDeVendas(Scanner entrada, List<Empregado> listaDeEmpregados) {
        Predicate<Empregado> ehComissionado = comissionadoEmpregado -> comissionadoEmpregado instanceof Comissionado; 
        List<Empregado> comissionadoEmpregados = listaDeEmpregados.stream().filter(ehComissionado).collect(Collectors.toList()); 
        if(comissionadoEmpregados.isEmpty()){
            System.out.println("Não há empregados comissionados cadastrados no sistema");
        }
        else{
            printTodosEmpregados(entrada, comissionadoEmpregados,1);
            int j = Entradas.lerInt(entrada,"Insira o número correspondente ao empregado cujo relatório de vendas será adicionado");
            Comissionado empregadoComissionado = (Comissionado) comissionadoEmpregados.get(j);
            System.out.println("============================================");
            System.out.println(empregadoComissionado.getName());
            System.out.println("--------------------------------------------");
            int valorDeVenda = Entradas.lerInt(entrada,"Insira o preço da venda:");
            System.out.println("Agora a data da venda:");
            LocalDate dataDeVenda = Entradas.lerData(entrada);

            RelatorioDeVendas relatorioDeVendas = new RelatorioDeVendas(dataDeVenda, valorDeVenda);
            empregadoComissionado.getRelatorioDeVendas().add(relatorioDeVendas);
            System.out.println("Resultado de vendas adicionado com sucesso");
            System.out.println("--------------------------------------------\n");
            return;
        }
    }

    public static void addTaxaDeServico(Scanner entrada, List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,1);
        int j = Entradas.lerInt(entrada,"Insira o número do empregado cujo você deseja adicionar uma taxa de serviço:");
        Empregado empregado = listaDeEmpregados.get(j);
        if(empregado.getSindicato().getMembro() == 0){
            System.out.println("Não há como adicionar uma taxa de serviço,\no empregado escolhido não é membro do sindicato");
            System.out.println("--------------------------------------------\n");
            return;
        }
        else{
            System.out.println("============================================");
            System.out.println(empregado.getName());
            System.out.println("--------------------------------------------");
            int taxaDeServico = Entradas.lerInt(entrada,"Insira o valor da taxa de serviço:");
            empregado.getSindicato().setTaxaDeServico(taxaDeServico);
            System.out.println("Taxa de serviço adicionada com sucesso");
            System.out.println("--------------------------------------------\n");
            return;
        }
    }

    public static void mudarInfoEmpregado(Scanner entrada,List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,0);
        int numEmpregado = Entradas.lerInt(entrada,"Escolha o número correspondente ao empregado cujos dados serão alterados:");
        Empregado empregado = listaDeEmpregados.get(numEmpregado);
        int op=-1;
        while(op!=0){
            System.out.println("--------------------------------------------");
            System.out.println(empregado.printEmpregadoInfo());
            System.out.println("Selecione o dado que deseja alterar:\n");
            System.out.println("[0] Voltar ao menu inicial");
            System.out.println("[1] Mudar nome");
            System.out.println("[2] Mudar endereço");
            System.out.println("[3] Mudar o tipo de empregado");
            System.out.println("[4] Mudar método de pagamento");
            System.out.println("[5] Mudar participação no sindicato");
            System.out.println("[6] Mudar ID do sindicato");
            System.out.println("[7] Mudar taxa de serviço do sindicato");
            System.out.println("...");
            op = Entradas.lerInt(entrada,"");
            Auxiliar.estruturaMudarEmpregadoInfo(op, empregado, entrada, listaDeEmpregados, numEmpregado);
        }
    }

    public static LocalDate checarUltimoDiaDoMes(LocalDate ultimoDia) {
        LocalDate ultimoDiaCorrigido;

        switch(ultimoDia.getDayOfWeek()){
            case SATURDAY:
                //SABADO
                ultimoDiaCorrigido = ultimoDia.minusDays(1);
                break;
            case SUNDAY:
                //DOMINGO 
                ultimoDiaCorrigido = ultimoDia.minusDays(2);
                break;
            default:
                ultimoDiaCorrigido = ultimoDia;
                break;
        }
        return ultimoDiaCorrigido;
    }

    public static void rodarFolhaDePagamento(Scanner entrada,List<Empregado> listaDeEmpregados, FolhaDePagamento folhaDePagamento) {    
        // printTodosEmpregados(entrada, listaDeEmpregados, 0);
        System.out.println("--------------------------------------------\n");
        System.out.println("Insira a data de início: ");
        LocalDate inicio = Entradas.lerData(entrada);
        System.out.println("--------------------------------------------\n");
        System.out.println("Insira a data de término: ");
        LocalDate fim = Entradas.lerData(entrada);
        long periodo = ChronoUnit.DAYS.between(inicio, fim);
        //auxiliares
        int Sexta = 0;
        LocalDate data  = inicio;
        int tam = listaDeEmpregados.size();
        int aux,aux2,flag=0,flag2=0;
        String dataFormatada;
        for( aux = 0; aux <= periodo ; aux++){
            data = inicio.plusDays(aux);
            dataFormatada = data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
            if(data.isEqual(checarUltimoDiaDoMes(data.with(TemporalAdjusters.lastDayOfMonth())))){
                flag=1;
                for(aux2=0;aux2<tam;aux2++){
                    if(listaDeEmpregados.get(aux2).getDiaDoPagamento()=="Mensal - Último dia do mês"){
                        flag2 = Auxiliar.estruturaFolhaDePagamento(aux2, flag2, dataFormatada, listaDeEmpregados,"Último útil do mês"); 
                    }
                }
                flag2=0;
            }

            if(data.getDayOfWeek() == DayOfWeek.FRIDAY){
                flag=1;
                if(Sexta % 2 == 0){
                    for(aux2=0;aux2<tam;aux2++){
                        if(listaDeEmpregados.get(aux2).getDiaDoPagamento()=="Quinzenal - Às sextas-feiras"){
                            flag2 = Auxiliar.estruturaFolhaDePagamento(aux2, flag2, dataFormatada, listaDeEmpregados,""); 
                        }
                    }
                }
                for(aux2=0;aux2<tam;aux2++){
                    if(listaDeEmpregados.get(aux2).getDiaDoPagamento() == "Semanal - Às sextas-feiras"){
                        flag2 = Auxiliar.estruturaFolhaDePagamento(aux2, flag2, dataFormatada, listaDeEmpregados,""); 
                    }
                }
                flag2=0;                
            }
            Sexta++;
        }   
        if(flag==0){
            System.out.println("--------------------------------------------");
            System.out.println("Não nenhum funcionário há ser pago\n no intervalo de tempo inserido");
            System.out.println("--------------------------------------------\n");
        }
    }

    public static void printAgenda(List<String> cronograma){
        int tam = cronograma.size();
        int aux;
        for (aux=0;aux<tam;aux++){
            System.out.println(cronograma.get(aux));
        }
    }

    public static void criarAgenda(Scanner entrada, FolhaDePagamento folhaDePagamento) {
        Auxiliar.printHeader("Nova Agenda de Pagamentos");
        System.out.println("Você pode criar uma nova agenda de pagamentos\n");
        System.out.println("Mensal 1: paga todo dia 1");
        System.out.println("Semanal 2 Terça: paga quinzenalmente as terças");
        System.out.println("Semanal 1 Segunda: paga toda segunda-feira\n");
        System.out.println("--------------------------------------------");
        String cronograma = Entradas.lerString(entrada,"Digite o novo cronograma");
        folhaDePagamento.cronograma.add(cronograma);
        System.out.println("--------------------------------------------");
        System.out.println("Nova agenda de pagamentos criada com sucesso");
        System.out.println("--------------------------------------------");
        System.out.println("Agora temos essas opçções de agendas:");
        printAgenda(folhaDePagamento.cronograma);
        System.out.println("--------------------------------------------");
    }
}
