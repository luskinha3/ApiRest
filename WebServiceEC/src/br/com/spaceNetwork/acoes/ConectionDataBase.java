package br.com.spaceNetwork.acoes;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConectionDataBase 
{
	

	public Connection getConexao() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		 
		Connection conexao = null;
		try 
		{
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/estados_cidades", "root", "159753Pb");
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conexao;
	}
	
	
	
}
	


   
    