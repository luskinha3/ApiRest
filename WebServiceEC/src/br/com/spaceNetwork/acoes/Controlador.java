package br.com.spaceNetwork.acoes;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.google.gson.Gson;

import br.com.spaceNetwork.dao.CidadeDao;
import br.com.spaceNetwork.dao.EstadoDao;
import br.com.spaceNetwork.modelo.Cidade;
import br.com.spaceNetwork.modelo.Estado;

@Path ("controlador")
public class Controlador {
	
	
	
	@GET
    @Produces("application/json")
	@Path("get/cidades")
    public String ListarCidades() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = cidadeDao.listar();    
        Gson gson = new Gson();
        return gson.toJson(cidades);
	}
	
	@GET
    @Produces("application/json")
	@Path("get/estados")
    public String ListarEstados() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
        EstadoDao estadoDao = new EstadoDao();
        List<Estado> estados = estadoDao.listar(); 
        Gson gson = new Gson();
        return gson.toJson(estados);
	}
	
	@GET
    @Produces("application/json")
	@Path("get/cidades/populacao/{min}&{max}")
    public String ListarCidadePopulacao(@PathParam("min")int min, @PathParam("max")int max) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = cidadeDao.BuscarPopulacao(min, max);       
        Gson gson = new Gson();
        return gson.toJson(cidades);
	}
	
	@GET
    @Produces("application/json")
	@Path("get/cidades/nome/{nome}")
    public String BuscarCidade(@PathParam("nome") String nome) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		CidadeDao cidadeDao = new CidadeDao();
        Cidade cidade = cidadeDao.BuscarCidade(nome); 
        Gson gson = new Gson();
        return gson.toJson(cidade);
	}
	
	@GET
	@Produces("application/json")
	@Path("get/cidades/ordemInversa/{ordem}")
	public String OrdemInversa(@PathParam("ordem") boolean ordem) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = cidadeDao.OrdenarLista(ordem);
        Gson gson = new Gson();
        return gson.toJson(cidades);
	}
	
	@GET
	@Produces("application/json")
	@Path("get/cidades/ordenar/{ordem}")
	public String OrdenarCidades(@PathParam("ordem") String ordem) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		CidadeDao cidadeDao = new CidadeDao();
		//List<Cidade> cidades = new ArrayList();
		List<Cidade> cidades = cidadeDao.OrdenarPor(ordem);
        Gson gson = new Gson();
        return gson.toJson(cidades);
	}
		
	
	@DELETE
	@Path ("excluir/{id}")
	public boolean DeletarCidade (@PathParam ("id") int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		CidadeDao cidadeDao = new CidadeDao();
		if(cidadeDao.deletar(id)) 
		{
			return true;
		};
		return false;
	}
	
	@POST
	@Consumes("application/json")
	@Path ("inserir/estados")
	public boolean InserirEstado (String content) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		Gson g = new Gson();
		Estado e = (Estado) g.fromJson(content, Estado.class);
		EstadoDao dao = new EstadoDao();
		if (dao.Cadastrar(e)) 
		{
			return true;
		};
		return false;
	}
	
	@POST
	@Consumes("application/json")
	@Path ("inserir/cidades")
	public boolean InserirCidade (String content) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		Gson g = new Gson();
		Cidade c = (Cidade) g.fromJson(content, Cidade.class);
		CidadeDao dao = new CidadeDao();
		if (dao.Cadastrar(c)) 
		{
			return true;
		};
		return false;
	}
	
	@PUT
	@Consumes("application/json")
	@Path("atualizar/cidade")
	public boolean AtualizarCidade (String content) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		Gson g = new Gson();
		Cidade c = (Cidade) g.fromJson(content, Cidade.class);
		CidadeDao cidadeDao = new CidadeDao();
		if (cidadeDao.Atualizar(c)) 
		{
			return true;
		};
		return false;
	}
	
	
	
	
	//getSELECT
	@GET
	@Path("get/estados/area")
	public String EstadosArea () throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		EstadoDao estadoDao = new EstadoDao();
		List<Estado> estados = estadoDao.ListarAreaEstado();
		
		Gson gson = new Gson();
		return gson.toJson(estados);
	}
	
	@GET
    @Produces("application/json")
	@Path("get/cidades/populacaoGrande")
    public String ListarCidadesPopulacaoGrande() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = cidadeDao.listarCidadesPopulacaoGrande();    
        Gson gson = new Gson();
        return gson.toJson(cidades);
	}
	
	@GET
	@Produces("application/json")
	@Path("get/estados/cidades")
	public String EstadoCidadesQtd() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		Gson gson = new Gson();
		EstadoDao estadoDao = new EstadoDao();
		List<Estado> estados = estadoDao.ListarQtdCidades();
		
		return gson.toJson(estados);
	}
	
	
	

}
