# Folha de Pagamentos :receipt:	:moneybag:
O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do
gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve
gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber
o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e
impostos deduzidos do salário.
* Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles
submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele
dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal
durante as horas extras. Eles são pagos toda sexta-feira.
* Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês
(desconsidere feriados). Tais empregados são chamados "assalariados".
* Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um
percentual das vendas que realizam. Eles submetem resultados de vendas que informam a
data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles
são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas
de salário fixo mais as comissões do período.
  * Empregados podem escolher o método de pagamento.
  * Podem receber um cheque pelos correios
  * Podem receber um cheque em mãos
  * Podem pedir depósito em conta bancária
  * Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato).
O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre
empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode
ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço
são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo
contracheque do empregado. A identificação do empregado no sindicato não é a mesma da
identificação no sistema de folha de pagamento.
* A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem
naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o
pagamento para cada empregado desde a última vez em que este foi pago.


Func | Título | Breve descrição
------------ | ------------- | -------------  
**1**| Adição de um empregado | Um novo empregado é adicionado ao sistema. Os seguintes atributos são fornecidos: nome, endereço, tipo (_hourly_, _salaried_, _commissioned_) e os atributos associados (salário horário, salário mensal, comissão). Um número de empregado (único) deve ser escolhidoautomaticamente pelo sistema.
**2** | Remoção de um empregado | Um empregado é removido do sistema.
**3** | Lançar um Cartão de Ponto | O sistema anotará a informação do cartão de ponto e a associará ao empregado correto.
**4** | Lançar um Resultado Venda | O sistema anotará a informação do resultado da venda e a associará ao empregado correto.
**5** | Lançar uma taxa de serviço | O sistema anotará a informação da taxa de serviço e a  associará ao empregado correto. 
**6** | Alterar detalhes de um empregado | Os seguintes atributos de um empregado podem ser  alterados: nome, endereço, tipo (_hourly_, _salaried_, _commisioned_), método de pagamento, se pertence ao sindicato ou não, identificação no sindicato, taxa sindical. 
**7** | Rodar a folha de pagamento para hoje | O sistema deve achar todos os empregados que devem  ser pagos no dia indicado, deve calcular o valor do salário  e deve providenciar o pagamento de acordo com o método escolhido pelo empregado
**8** | Undo/redo | Qualquer transação associada as funcionalidades **1** a **7**  deve ser _desfeita_ (*undo*) ou _refeita_ (*redo*).
**9** | Agenda de Pagamento | Cada empregado é pago de acordo com uma "_agenda de  pagamento_". Empregados podem selecionar a agenda de  pagamento que desejam. Por _default_, as agendas  "_semanalmente_", "_mensalmente_" e "_bi-semanalmente_" são usadas, como explicado na descrição geral. Posteriormente, um empregado pode pedir para ser pago de acordo com qualquer uma dessas agendas.
**10** | Criação de Novas Agendas de Pagamento | A direção da empresa pode criar uma nova agenda de  pagamento e disponibilizá-la para os empregados  escolherem, se assim desejarem. Uma agenda é especificada através de um string. Alguns exemplos  mostram as possibilidades: "**mensal 1**": _dia 1 de todo mês_ ; "**mensal $**": _último dia útil de todo mês_; "**semanal 1 segunda**": _toda semana  às segundas-feiras_; "**semanal 2 segunda**": _a cada 2 semanas às segundas-feiras_ ; etc.

# CODE SMELLS

* ## Código Duplicado
  * Na classe **App** _método **Main**_ há repetições na estrutura condicional (switch case) do menu inicial.
    * Há a repetição da verificação da lista de empregados (se está vazia).
  * Na classe **Configs** _método **mudarInfoEmpregado**_ há repetição na estrututra condicional para cada opção que pode ser escolhida.
  * Na classe **Configs** _métodos **novoEmpregado** e **mudarInfoEmpregado**_ há a repetição do condicional (switch case) na seleção do tipo de empregado.
 
* ## Long Method 
  * Nas classes **App** _método **Main**_ e **Configs** _método **mudarInfoEmpregado**_ há a repetição na estrutura condicional (switch case).
  * A classe **Configs** _método **novoEmpregado**_ é demasiadamente grande, recolhe muitas informações sozinha.

* ## Large Class
  * A classe **Configs** possui muitos métodos.

* ## Speculative Generality
  * Muitos construtores, setter e getters de várias classes não são utilizados.

* ## Data Class
  * A maioria das classes apenas armazenam dados e possuem getters e setters.

# Refatoração

## Template Method
Para esse tipo de solução, o problema apresentado se comporta da seguinte forma: Há uma série de passos que é repetida na mesma ordem, mas com alguma alteração que faz com que não sejam exatamente iguais. Esse padrão de projeto comportamental define o esqueleto de um algoritmo na superclasse mas deixa as subclasses sobrescreverem etapas específicas do algoritmo sem modificar sua estrutura.
  * O problema foi solucionado com a implementação da classe **Auxiliar**, com vários métodos criados para reduzir a quantidade de código duplicado. _Métodos **empregadoViaTipo**, **estruturaMudarEmpregadoInfo** e **estruturaFolhaDePagamento**_ são exemplos.
  * **Antes**
      * [Main](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/8a46013da873d93f9f09d85c1c4ea05f7279180d/src/app/App.java#L35) 
      * [novoEmpregado](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/8a46013da873d93f9f09d85c1c4ea05f7279180d/src/app/Configs.java#L21)
      * [mudarInfoEmpregado](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/8a46013da873d93f9f09d85c1c4ea05f7279180d/src/app/Configs.java#L213)
      * [rodarFolhaDePagamento](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/8a46013da873d93f9f09d85c1c4ea05f7279180d/src/app/Configs.java#L438)
  * **Depois**
      * [Main](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/App.java#L11)
        * [appMenu](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java#L28)
      * [novoEmpregado](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Configs.java#L21)
      * [mudarInfoEmpregado](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Configs.java#L123)
        * [estruturaMudarInfoEmpregado](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java#L189)
      * [rodarFolhaDePagamento](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Configs.java#L199)
        * [estruturaFolhaDePagamento](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java#L167)
  
## Handle Exceptcions
  * Para tratar as exceptions existentes no código,a classe [Entradas](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Entradas.java), a qual possui métodos para tratar a leitura de Int, String, etcs. Foi atualizada para que os métodos utilizem [try/catch](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Entradas.java#L17).

## Outras alterações
  * Um bad smell do tipo _código duplicado_ estava presente na classe **App** _método **Main**_, o qual havia repetição na verificação do tamanho da lista, em todos os casos, e sempre que estava vazia, ocorria um break. Para retirar essa repetição, foi colocado  um único condicional if antes do switch case.
    * [Antes](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/8a46013da873d93f9f09d85c1c4ea05f7279180d/src/app/App.java#L71)
    * [Depois](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java#L32)
 
  * A classe **Configs** era um bad smell _Large Class_ e foi dividida em duas, sendo criada a classe [Auxiliar](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java) que conta com implementações novas (_templates_), e algumas coisas já existentes na antiga classe.
  * O _método **novoEmpregado**_, da classe **Configs** configura um bad smell _Long Method_, e o mesmo foi refatorado, sendo dividido com a classe **Auxiliar** e seus métodos.
  * Foi criado o _método_ [limparConsole](https://github.com/Jpcajueiro/Folha-De-Pagamentos/blob/main/src/app/Auxiliar.java#L16), que pode ser chamado pelo usuário para limpar o console.
  * Alguns métodos setters, getters não estavam sendo usados, o que configurava um bad smell do tipo "speculativy generality", portanto, foram excluídos.
