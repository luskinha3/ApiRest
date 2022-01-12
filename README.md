# ApiRest

ApiRest utilizando bibliotecas do jersey + mysql

## EndPoints: 

Os EndPoints se encontram na classe controlador, porem a realizão das ações são feitas nas classes DAO

### ListarCidades() @GET 
	retorno = arquivo Json com lista de cidades.
	path = http://localhost:8080/WebServiceEC/api/controlador/get/cidades

### ListarEstados() @GET 
	retorno = arquivo Json com lista de Estados.
	path = http://localhost:8080/WebServiceEC/api/controlador/get/estados

### ListarCidadePopulacao(int min,int max) @GET
	recebe = dois parametros determinando faixa populacional
	retorno = arquivo Json contentado lista de Cidades que contem uma determinada faixa populaional
	ex = http://localhost:8080/WebServiceEC/api/controlador/get/cidades/populacao/10000&21000

### BuscarCidade(String nome) @GET
	recebe = string com nome da cidade
	retorno = arquivo Json contendo cidade buscado
	ex = http://localhost:8080/WebServiceEC/api/controlador/get/cidades/nome/cuité

### OrdemInversa(boolean ordem) @GET
	recebe = true ou false
	retorno = caso receba true retorna Json contendo lista de cidades em ordem DESC, caso receba false retonr Json contendo lista de cidades em ordem ASC
	ex = http://localhost:8080/WebServiceEC/api/controlador/get/cidades/ordemInversa/true
 
### DeletarCidade(int id) @DELETE
	recebe = parametro inteiro indicando id cidade a ser deletado
	retorno = true caso cidade seja deletado ou false quando cidade nao seja deletada
	ex = http://localhost:8080/WebServiceEC/api/controlador/excluir/4

### InserirEstado (String content) @POST
	recebe = arquivo Json contendo nome do estado
	ex = 
	{
	 	"nome":"PE"   
	}
	retorno = true caso estado seja cadastrado com sucesso e false quando não for possivel cadastrar
	path = http://localhost:8080/WebServiceEC/api/controlador/inserir/estados

### InserirCidade(String content) @POST
	recebe = arquivo Json contendo as informações para cadastro nome(String), populacao(int), area(double) e id_estado(int)
	ex =
	{
		 "nome":"Recife",
	     "populacao":"1661017",
	     "area":"218.0",
	     "estado_id":"3"
	}
	retorno = true caso estado seja cadastrado com sucesso e false quando não for possivel cadastrar
	path = http://localhost:8080/WebServiceEC/api/controlador/inserir/cidades

### AtualizarCidade(String Content) @PUT
	recebe = arquivo Json contendo id(int) nome(String), populacao(int), area(double) e id_estado(int) da cidade a ser modificada (obs: id é o identificador para fazer a mudança)
	ex =
	{
		"id":"5",
		 "nome":"Recife",
	     "populacao":"1661017",
	     "area":"218.0",
	     "estado_id":"3"
	}
	retorno = true caso estado seja cadastrado com sucesso e false quando não for possivel cadastrar
	path = http://localhost:8080/WebServiceEC/api/controlador/atualizar/cidades

### OrdenarCidades() @GET
	- não consegui criar a query para a chamada desse endpoint, ele apenas retorna um json com a lista de cidades.


## endpoint especificos	

### EstadosArea @GET
	Retorno = arquivo json contendo lista com a area de cada estado
	path = http://localhost:8080/WebServiceEC/api/controlador/get/estados/area

### ListarCidadesPopulacaoGrande @GET
	retorno = arquivo json contendo lista de cidade com populacao maior que 10000
	path = http://localhost:8080/WebServiceEC/api/controlador/get/cidades/populacaoGrande

### EstadoCidadesQtd @GET
	retorno = arquivo json contendo lista com a quantide de cidades por estado
	path = http://localhost:8080/WebServiceEC/api/controlador/get/estados/cidades




Observações:
* ConsumindoWS: é um projeto java para testar o consumo do webService utilizando um metodo Get e Post. A classe HttpExemplo contem um exemplo de teste.
* Banco utilizado para criar aplicação: Mysql
* Aplicação feita em localHost, então o banco deve ser criado
* Folha para criação do banco e tabelas na raíz do projeto ("sql_prova");
* testes feitos utilizando postman
