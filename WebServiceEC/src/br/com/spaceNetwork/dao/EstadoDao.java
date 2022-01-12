package br.com.spaceNetwork.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spaceNetwork.acoes.ConectionDataBase;
import br.com.spaceNetwork.modelo.Estado;


public class EstadoDao 
{
	private ConectionDataBase  conection; 
	private Connection con ;
	 
	
	public EstadoDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		this.conection = new ConectionDataBase();
		this.con = conection.getConexao();
	}
	
	
	
	public boolean Cadastrar(Estado estado) throws SQLException 
	{
			
		con.setAutoCommit(false);
		try (PreparedStatement pst = con.prepareStatement("INSERT INTO estados (nome_estado) VALUES (?)", Statement.RETURN_GENERATED_KEYS))
		{
			pst.setString(1, estado.getNome() );
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
	
	public List<Estado> listar() throws SQLException 
	{
		
		 List<Estado> estados = new  ArrayList<Estado>();
		 
		 try (PreparedStatement stm = con.prepareStatement("SELECT id_estado, nome_estado FROM estados"))
		{
			stm.execute();		
			try (ResultSet rst = stm.getResultSet())
			{			
				while(rst.next()) 
				{						
					Estado estado = new Estado(rst.getInt(1), rst.getString(2));
					estados.add(estado);							
				}
			}
		}
		 
		 return estados;
		 
	}
	
	public List<Estado> ListarAreaEstado() throws SQLException 
	{
		
		 List<Estado> estados = new  ArrayList<Estado>();
		 
		 try (PreparedStatement stm = con.prepareStatement("SELECT id_estado,estados.nome_estado, SUM(area) AS area FROM cidades "
		 		+ "INNER JOIN estados ON cidades.estado = estados.id_estado GROUP BY estado"))
		{
			stm.execute();		
			try (ResultSet rst = stm.getResultSet())
			{			
				while(rst.next()) 
				{						
					Estado estado = new Estado(rst.getString(1), rst.getDouble(2));
					estados.add(estado);							
				}
			}
		}
		 
		 return estados;
		 
	}
	
	public List<Estado> ListarQtdCidades () throws SQLException
	{
		List<Estado> estados = new  ArrayList<Estado>();
		
		try (PreparedStatement stm = con.prepareStatement("SELECT estados.nome_estado, COUNT(nome_cidade) AS nome_cidade FROM cidades INNER JOIN estados ON cidades.estado = estados.id_estado GROUP BY estado"))
		{
			stm.execute();		
			try (ResultSet rst = stm.getResultSet())
			{			
				while(rst.next()) 
				{						
					Estado estado = new Estado(rst.getString(1), rst.getInt(2));
					estados.add(estado);							
				}
			}
		}
		 
		 return estados;
		 
	}
		
}
