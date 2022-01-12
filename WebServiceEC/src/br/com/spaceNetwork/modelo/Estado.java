package br.com.spaceNetwork.modelo;

public class Estado {
	
	private int id;
	private String nome;
	private int qtdCidades;
	private double qtdArea;
	
	public Estado( String nome) 
	{
		this.nome = nome;
	}
	public Estado( int id,String nome) 
	{
		this.id = id;
		this.nome = nome;
	}
	
	public Estado( String nome, int qtdCidades) 
	{
		this.nome = nome;
		this.qtdCidades = qtdCidades;
	}
	
	public Estado( String nome, double qtdArea) 
	{
		this.nome = nome;
		this.qtdArea = qtdArea;
	}
	
	public int getQtdCidades() {
		return qtdCidades;
	}
	public void setQtdCidades(int qtdCidades) {
		this.qtdCidades = qtdCidades;
	}
	public double getQtdArea() {
		return qtdArea;
	}
	public void setQtdArea(double qtdArea) {
		this.qtdArea = qtdArea;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		
		return String.format(" %02d | %3s " , this.id,this.nome);
	}

}
