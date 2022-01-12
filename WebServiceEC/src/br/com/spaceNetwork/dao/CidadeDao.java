package br.com.spaceNetwork.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spaceNetwork.acoes.ConectionDataBase;
import br.com.spaceNetwork.modelo.Cidade;


public class CidadeDao {
	
	private ConectionDataBase  conection; 
	private Connection con ;
	
	public CidadeDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		this.conection = new ConectionDataBase();
		this.con = conection.getConexao();
	}
	public boolean Cadastrar(Cidade cidade) throws SQLException 
	{
			
		con.setAutoCommit(false);
		try (PreparedStatement pst = con.prepareStatement("INSERT INTO cidades (nome_cidade,populacao,area,estado) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS))
		{
			pst.setString(1, cidade.getNome() );
			pst.setInt(2, cidade.getPopulação());
			pst.setDouble(3, cidade.getArea());
			pst.setInt(4,cidade.getEstado_id());
			pst.execute();
				
			con.commit();
			return true;
			
		}
		catch (Exception e) {
			e.getStackTrace();
			con.rollback();
			return false;
		}
	}
	
	public List<Cidade> listar() throws SQLException 
	{
		
		 List<Cidade> cidades = new  ArrayList<Cidade>();
		 
		 try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade,nome_cidade,populacao,area,estado,estados.nome_estado "
		 		+ "FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado"))
		{
			pst.execute();		
			try (ResultSet rst = pst.getResultSet())
			{			
				while(rst.next()) 
				{						
					Cidade cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getInt(5),rst.getString(6));
					cidades.add(cidade);							
				}
			}
		}
		 
		 return cidades;
		 
	}
	
	public boolean deletar(int id) throws SQLException 
	{
		try (PreparedStatement pst = con.prepareStatement("DELETE FROM cidades WHERE id_cidade = ?", Statement.RETURN_GENERATED_KEYS))
		{
			pst.setInt(1, id );
			pst.execute();
			
			return true;
		}
		
	}
	
	public boolean Atualizar (Cidade cidade) throws SQLException 
	{
		try (PreparedStatement pst = con.prepareStatement("UPDATE cidades SET nome_cidade=?,populacao=?,area=?,estado=? WHERE id_cidade = ?", Statement.RETURN_GENERATED_KEYS))
		{
			
			pst.setString(1, cidade.getNome());
			pst.setInt(2, cidade.getPopulação());
			pst.setDouble(3, cidade.getArea());
			pst.setInt(4,cidade.getEstado_id());
			pst.setInt(5, cidade.getId());
			pst.execute();
			
			return true;
		}
	}
	
	public Cidade BuscarCidade (String nome) throws SQLException 
	{
		Cidade cidade = null;
		 try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade,nome_cidade,populacao,area,estado,estados.nome_estado "
		 		+ "FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado where nome_cidade = ?"))
			{
			 	pst.setString(1, nome);
				pst.execute();
				
				try (ResultSet rst = pst.getResultSet())
				{			
					while(rst.next()) 
					{						
						cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getInt(5),rst.getString(6));			
					}
				}
			}
		 return cidade;
		
	}
	
	public List<Cidade> BuscarPopulacao (int min, int max) throws SQLException
	{
		List<Cidade> cidades = new  ArrayList<Cidade>();
		
		try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade, nome_cidade, populacao, area, estado, estados.nome_estado FROM cidades "
				+ "INNER JOIN estados on cidades.estado = estados.id_estado WHERE populacao  BETWEEN ? AND ?;"))
		{
		 	pst.setInt(1, min);
		 	pst.setInt(2, max);
			pst.execute();
			
			try (ResultSet rst = pst.getResultSet())
			{			
				while(rst.next()) 
				{						
					Cidade cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getInt(5),rst.getString(6));
					cidades.add(cidade);
				}
			}
		}
		
		return cidades;
	}
	
	public List<Cidade> OrdenarLista(boolean ordem) throws SQLException
	{
		List<Cidade> cidades = new  ArrayList<Cidade>();
		
		if(ordem == true) 
		{
			try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade,nome_cidade,populacao,area,estado, estados.nome_estado "
			 		+ "FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado ORDER BY id_cidade DESC"))
			{
				pst.execute();		
				try (ResultSet rst = pst.getResultSet())
				{			
					while(rst.next()) 
					{						
						Cidade cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getInt(5),rst.getString(6));
						cidades.add(cidade);							
					}
				}
			}
			return cidades;
		}
		
		cidades = this.listar();
		return cidades;
	}
	public List<Cidade> listarCidadesPopulacaoGrande() throws SQLException 
	{
		
		 List<Cidade> cidades = new  ArrayList<Cidade>();
		 
		 try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade,nome_cidade,populacao,area,estado, estados.nome_estado "
		 		+ "FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado where cidades.populacao > 10000"))
		{
			pst.execute();		
			try (ResultSet rst = pst.getResultSet())
			{			
				while(rst.next()) 
				{						
					Cidade cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getInt(5),rst.getString(6));
					cidades.add(cidade);							
				}
			}
		}
		 
		 return cidades;
		 
	}
	
	public List<Cidade> OrdenarPor(String ordem) throws SQLException
	{
		List<Cidade> cidades = new  ArrayList<Cidade>();
		
		
		
		try (PreparedStatement pst = con.prepareStatement("SELECT id_cidade,nome_cidade,populacao,area,estado,estados.nome_estado "
					+ "FROM cidades INNER JOIN estados on cidades.estado = estados.id_estado ORDER BY ? "))
			{
				pst.setString(1, ordem);
				pst.execute();
				try (ResultSet rst = pst.getResultSet())
				{			
					while(rst.next()) 
					{						
						Cidade cidade = new Cidade(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getDouble(4),rst.getString(5));
						cidades.add(cidade);							
					}
				}
			}
		
		return cidades;
	}
	
	
	
	


}
