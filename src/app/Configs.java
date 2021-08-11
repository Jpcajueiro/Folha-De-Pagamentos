package app;
import model.empregados.*;
import model.empresa.*;
import java.util.*;
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
    
    public static Empregado novoEmpregado(Scanner entrada) {
        Empregado empregado;
        Sindicato sindicato;
        Pagamento pagamento;
        System.out.println("============================================");
        System.out.println("======== Cadastro de Novo Empregado ========");
        System.out.println("--------------------------------------------");
        System.out.println("Digite o nome do empregado:");
        String name = Entradas.lerString(entrada);
        System.out.println("Digite o endereço do empregado:");
        String endereco = Entradas.lerString(entrada);
        System.out.println("O seu empregado é membro do Sindicato ?");
        System.out.println("[1] Sim\n[2] Não");
        int membro = Entradas.lerInt(entrada);
        if (membro == 1){
            System.out.println("============================================");
            System.out.println("=========== Membro do Sindicato ============");
            System.out.println("--------------------------------------------");
            System.out.println("Digite a taxa mensal do Sindicato:");
            int taxaMensal = Entradas.lerInt(entrada);
            System.out.println("Digite a taxa de serviço do Sindicato");
            int taxaDeServico = Entradas.lerInt(entrada);
            sindicato = new Sindicato(taxaMensal, taxaDeServico, UUID.randomUUID(), 1);
        } 
        else{
            sindicato = new Sindicato(0, 0, new UUID(0,0), 0);
        }
        System.out.println("============================================");
        System.out.println("======== Informações de Pagamento ==========");
        System.out.println("--------------------------------------------");
        System.out.println("Qual o método de pagamento do empregado ?");
        System.out.println("[1] Em mãos [2] Via correspondência [3] Depósito em conta");
        int metodoDePagamento = Entradas.lerInt(entrada);
        System.out.println("============================================");
        System.out.println("========== Informações Bancárias ===========");
        System.out.println("--------------------------------------------");
        System.out.println("Qual o nome do Banco do seu empregado ?");
        String banco = Entradas.lerString(entrada);
        System.out.println("Qual o número da agência ?");
        int agencia = Entradas.lerInt(entrada);
        System.out.println("Qual o número da conta ?");
        int conta = Entradas.lerInt(entrada);
        pagamento = new Pagamento(metodoDePagamento, banco, agencia, conta);
        System.out.println("Qual o tipo do empregado ?");
        System.out.println("[1] Assalariado\n[2] Comissionado\n[3] Horista");
        int tipoDeEmpregado = Entradas.lerInt(entrada);
        String diaDoPagamento = "";
        int salario;
        switch (tipoDeEmpregado) {
            case 1:
                diaDoPagamento = "Mensal - Último dia do mês"; 
                System.out.println("Qual o salário do empregado ?");
                salario = Entradas.lerInt(entrada);
                empregado = new Assalariado(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salario);
                System.out.println(empregado.printEmpregadoInfo());
                break;
            case 2:
                diaDoPagamento = "Quinzenal - Às sextas-feiras";
                System.out.println("Qual o salário do empregado ?");
                salario = Entradas.lerInt(entrada);
                System.out.println("Qual a comissão do empregado ?");
                int comissao = Entradas.lerInt(entrada);
                empregado = new Comissionado(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salario, comissao, null);
                break;
            case 3:
                diaDoPagamento = "Semanal - Às sextas-feiras";
                System.out.println("Qual é o salario por hora trabalhada do empregado ?");
                int salarioPorHora = Entradas.lerInt(entrada);
                empregado = new Horista(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento, salarioPorHora);
                break;
            default:
                // tipoDeEmpregado inválido
                empregado = new Empregado(name, UUID.randomUUID(), endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento);
                break;
        }
        return empregado;
    }

    public static void removerEmpregado(Scanner entrada, List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,1);
        System.out.println("Digite o número correspondente ao empregado a ser removido:");
        int n = Entradas.lerInt(entrada);
        String name = listaDeEmpregados.get(n).getName();
        listaDeEmpregados.remove(n);
        System.out.println("Empregado (" + name + ") foi removido do sistema com sucesso" );
    }

    public static void printTodosEmpregados(Scanner entrada, List<Empregado> listaDeEmpregados, int op) {
        int n = 0;
        // op = 0 Printa tudo , op  = 1 basico, op = qlqr coisa Printa pagamento
        System.out.println("============================================");
        System.out.println("=========== Lista de Empregados ============");
        for(Empregado empregado : listaDeEmpregados){
            System.out.println("============================================");
            System.out.println("Empregado número " + n);
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
            System.out.println("Digite o número correspondente ao empregado\ncujo cartão de ponto será adicionado:");
            int j = Entradas.lerInt(entrada);
            Horista empregadoHorista = (Horista) horistaEmpregados.get(j);
            System.out.println("Qual a hora (00-24) de entrada ?");
            int horaEntrada = Entradas.lerInt(entrada);
            System.out.println("Qual a minutagem (00-60) de entrada ?");
            int minutoEntrada = Entradas.lerInt(entrada);
            System.out.println("Qual a hora (00-24) de saída ?");
            int horaSaida = Entradas.lerInt(entrada);
            System.out.println("Qual a minutagem (00-60) de saída ?");
            int minutoSaida = Entradas.lerInt(entrada);
            System.out.println("Agora, insira a data :");

            LocalTime horarioEntrada = LocalTime.of(horaEntrada, minutoEntrada);
            LocalTime horarioSaida = LocalTime.of(horaSaida, minutoSaida);
            LocalDate data = Entradas.lerData(entrada);
            
            CartaoDePonto cartaoDePonto = new CartaoDePonto(data, horarioEntrada, horarioSaida);
            empregadoHorista.getCartaoDePonto().add(cartaoDePonto);
            System.out.println("Cartão de ponto adicionado com sucesso");
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
            System.out.println("Insira o número correspondente ao empregado cujo relatório de vendas será adicionado");
            int j = Entradas.lerInt(entrada);
            Comissionado empregadoComissionado = (Comissionado) comissionadoEmpregados.get(j);
            System.out.println("============================================");
            System.out.println(empregadoComissionado.getName());
            System.out.println("--------------------------------------------");
            System.out.println("Insira o preço da venda:");
            int valorDeVenda = Entradas.lerInt(entrada);
            System.out.println("Agora a data da venda:");
            LocalDate dataDeVenda = Entradas.lerData(entrada);

            RelatorioDeVendas relatorioDeVendas = new RelatorioDeVendas(dataDeVenda, valorDeVenda);
            empregadoComissionado.getRelatorioDeVendas().add(relatorioDeVendas);
            System.out.println("Resultado de vendas adicionado com sucesso");
            return;
        }
    }

    public static void addTaxaDeServico(Scanner entrada, List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,1);
        System.out.println("Insira o número do empregado cujo você deseja adicionar uma taxa de serviço:");
        int j = Entradas.lerInt(entrada);
        Empregado empregado = listaDeEmpregados.get(j);
        if(empregado.getSindicato().getMembro() ==0){
            System.out.println("Não há como adicionar uma taxa de serviço,\no empregado escolhido não é membro do sindicato");
            return;
        }
        else{
            System.out.println("============================================");
            System.out.println(empregado.getName());
            System.out.println("--------------------------------------------");
            System.out.println("Insira o valor da taxa de serviço:");
            int taxaDeServico = Entradas.lerInt(entrada);
            empregado.getSindicato().setTaxaDeServico(taxaDeServico);
            System.out.println("Taxa de serviço adicionada com sucesso");
            return;
        }
    }

    public static void mudarInfoEmpregado(Scanner entrada,List<Empregado> listaDeEmpregados) {
        printTodosEmpregados(entrada, listaDeEmpregados,0);
        System.out.println("Escolha o número correspondente ao empregado cujos dados serão alterados:");
        int j = Entradas.lerInt(entrada);
        Empregado empregado = listaDeEmpregados.get(j);
        int op=-1;
        int opt=-1;
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
            op = Entradas.lerInt(entrada);
            opt=-1;
            switch (op) {
                case 1:
                    System.out.println("============================================");
                    System.out.println("Digite o novo nome a ser atribuído:");
                    String novoNome = Entradas.lerString(entrada);
                    System.out.println("--------------------------------------------");
                    System.out.println("Nome atual: " + empregado.getName());
                    System.out.println("Novo nome: " + novoNome);
                    System.out.println("--------------------------------------------");
                    System.out.println("Tem certeza que deseja alterar ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        empregado.setName(novoNome);
                        System.out.println("Nome alterado com sucesso");
                    }
                    break;
                case 2:
                    System.out.println("============================================");
                    System.out.println("Digite o novo endereço a ser atribuído:");
                    String novoEndereco = Entradas.lerString(entrada);
                    System.out.println("--------------------------------------------");
                    System.out.println("Endereço atual: " + empregado.getEndereco());
                    System.out.println("Novo endereço: " + novoEndereco);
                    System.out.println("--------------------------------------------");
                    System.out.println("Tem certeza que deseja alterar ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        empregado.setName(novoEndereco);
                        System.out.println("Endereço alterado com sucesso");
                        System.out.println("--------------------------------------------\n");
                    }
                    break;
                case 3:
                    System.out.println("============================================");
                    System.out.println("Digite o novo tipo de empregado a ser atribuído:");
                    System.out.println("[1] Assalariado [2] Comissionado [3] Horista");
                    opt = Entradas.lerInt(entrada);
                    if(opt == empregado.getTipoDeEmpregado()){
                        System.out.println("O empregado já é do tipo selecionado, tente novamente.");
                        System.out.println("--------------------------------------------\n");
                    }
                    else{
                        Empregado novoEmpregado;
                        String diaDoPagamento = "";
                        int salario=0;
                        switch (opt) {
                            case 1:
                                // Assalariado
                                diaDoPagamento = "Mensal - Último dia do mês"; 
                                System.out.println("Qual o salário do empregado ?");
                                salario = Entradas.lerInt(entrada);
                                System.out.println(empregado.printEmpregadoInfo());
                                novoEmpregado = new Assalariado(empregado.getName(), empregado.getId(), empregado.getEndereco(), 1, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salario);
                                //Substituindo o 'antigo empregado' da lista
                                listaDeEmpregados.set(j, novoEmpregado);
                                break;
                            case 2:
                                // Comissionado
                                diaDoPagamento = "Quinzenal - Às sextas-feiras";
                                System.out.println("Qual o salário do empregado ?");
                                salario = Entradas.lerInt(entrada);
                                System.out.println("Qual a comissão do empregado ?");
                                int comissao = Entradas.lerInt(entrada);
                                novoEmpregado = new Comissionado(empregado.getName(), empregado.getId(), empregado.getEndereco(), 2, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salario, comissao, null);
                                //Substituindo o 'antigo empregado' da lista
                                listaDeEmpregados.set(j, novoEmpregado);
                                break;
                            case 3:
                                diaDoPagamento = "Semanal - Às sextas-feiras";
                                System.out.println("Qual é o salario por hora trabalhada do empregado ?");
                                int salarioPorHora = Entradas.lerInt(entrada);
                                empregado = new Horista(empregado.getName(), empregado.getId(), empregado.getEndereco(), 3, empregado.getSindicato(), empregado.getPagamento(), diaDoPagamento, salarioPorHora);
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("============================================");
                    System.out.println("Digite o número correspondente ao novo método a ser atribuído:");
                    System.out.println("[1] Em mãos [2] Via correspondência [3] Depósito em conta");
                    int novoMetodoDePagamento = Entradas.lerInt(entrada);
                    Pagamento novoPagamento = new Pagamento(novoMetodoDePagamento, empregado.getPagamento().getBanco(), empregado.getPagamento().getAgencia(), empregado.getPagamento().getConta());
                    System.out.println("Qual o tipo do empregado ?");
                    System.out.println("--------------------------------------------");
                    System.out.println("Método de pagamento atual: " + empregado.getPagamento().printMetodoDePagamento());
                    System.out.println("Novo método: " +  novoPagamento.printMetodoDePagamento());
                    System.out.println("--------------------------------------------");
                    System.out.println("Tem certeza que deseja alterar ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        empregado.setPagamento(novoPagamento);
                        System.out.println("Método de pagamento alterado com sucesso");
                        System.out.println("--------------------------------------------\n");
                    }
                    break;
                case 5:
                    System.out.println("============================================");
                    System.out.println("O empregado selecionado:" + empregado.checarMembro());
                    System.out.println("--------------------------------------------\n");
                    System.out.println("Tem certeza que deseja alterar a situação atual ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        if(empregado.getSindicato().getMembro()==0){
                            empregado.getSindicato().setMembro(1);
                            empregado.getSindicato().setIdSindicato(UUID.randomUUID());
                            System.out.println("Alteração feita com sucesso.\nAgora o empregado é membro do sindicato");
                        }
                        else if(empregado.getSindicato().getMembro()==1){
                            empregado.getSindicato().setMembro(0);
                            empregado.getSindicato().setIdSindicato(new UUID(0,0));
                            System.out.println("Alteração feita com sucesso.\nAgora o empregado não é membro do sindicato");
                        }
                    }
                    break;
                case 6:
                    // Mudar ID do sindicato
                    System.out.println("============================================");
                    System.out.println("ID do sindicato atual: " + empregado.getSindicato().getIdSindicato());
                    UUID newIdSindicato = UUID.randomUUID();
                    System.out.println("Nova ID do sindicato: " + newIdSindicato);
                    System.out.println("--------------------------------------------");
                    System.out.println("Tem certeza que deseja alterar a identificação atual ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        empregado.getSindicato().setIdSindicato(newIdSindicato);
                        System.out.println("ID do sindicato alterada com sucesso");
                        System.out.println("--------------------------------------------\n");
                    }
                    break;
                case 7:
                    // Mudar taxa de serviço do sindicato
                    System.out.println("============================================");
                    System.out.println("Digite o valor da nova taxa de serviço:");
                    int novaTaxaDeServico = Entradas.lerInt(entrada);
                    System.out.println("Taxa de serviço atual: " + empregado.getSindicato().getTaxaDeServico());
                    System.out.println("Nova taxa de serviço:" + novaTaxaDeServico);
                    System.out.println("--------------------------------------------");
                    System.out.println("Tem certeza que deseja alterar a taxa de serviço atual ?");
                    System.out.println("[1] Sim\n[2] Não");
                    opt = Entradas.lerInt(entrada);
                    if(opt==1){
                        empregado.getSindicato().setTaxaDeServico(novaTaxaDeServico);
                        System.out.println("Taxa de serviço alterada com sucesso");
                        System.out.println("--------------------------------------------\n");
                    }
                    break;
                default:
                    break;
            }
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
        int aux,aux2,flag=0;
        String dataFormatada;
        for( aux = 0; aux <= periodo ; aux++){
            data = inicio.plusDays(aux);
            dataFormatada = data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
            if(data.isEqual(checarUltimoDiaDoMes(data.with(TemporalAdjusters.lastDayOfMonth())))){
                flag=1;
                System.out.println("============================================");
                System.out.println("========== Último dia útil do mês ==========");
                System.out.println("===== " + dataFormatada + " ====");
                System.out.println("--------------------------------------------\n");
                for(aux2=0;aux2<tam;aux2++){
                    if(listaDeEmpregados.get(aux2).getDiaDoPagamento()=="Mensal - Último dia do mês"){
                        System.out.println(listaDeEmpregados.get(aux2).printInfoPagamento());
                    }
                }
            }

            if(data.getDayOfWeek() == DayOfWeek.FRIDAY){
                flag=1;
                System.out.println("============================================");
                System.out.println("===== " + dataFormatada + " ====");
                System.out.println("--------------------------------------------\n");
                if(Sexta % 2 == 0){
                    for(aux2=0;aux2<tam;aux2++){
                        if(listaDeEmpregados.get(aux2).getDiaDoPagamento()=="Quinzenal - Às sextas-feiras"){
                            System.out.println(listaDeEmpregados.get(aux2).printInfoPagamento());
                        }
                    }
                }
                for(aux2=0;aux2<tam;aux2++){
                    if(listaDeEmpregados.get(aux2).getDiaDoPagamento() == "Semanal - Às sextas-feiras"){
                        System.out.println(listaDeEmpregados.get(aux2).printInfoPagamento());
                    }
                }                
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
        System.out.println("============================================");
        System.out.println("========= Nova agenda de pagamento =========");
        System.out.println("--------------------------------------------");
        System.out.println("Você pode criar uma nova agenda de pagamentos\n");
        System.out.println("Mensal 1: paga todo dia 1");
        System.out.println("Semanal 2 Terça: paga quinzenalmente as terças");
        System.out.println("Semanal 1 Segunda: paga toda segunda-feira\n");
        System.out.println("--------------------------------------------");
        System.out.println("Digite o novo cronograma");
        String cronograma = Entradas.lerString(entrada);
        folhaDePagamento.cronograma.add(cronograma);
        System.out.println("--------------------------------------------");
        System.out.println("Nova agenda de pagamentos criada com sucesso");
        System.out.println("--------------------------------------------");
        System.out.println("Agora temos essas opçções de agendas:");
        printAgenda(folhaDePagamento.cronograma);
        System.out.println("--------------------------------------------");
    }
}
