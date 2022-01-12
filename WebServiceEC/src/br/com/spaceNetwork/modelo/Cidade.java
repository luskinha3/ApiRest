package br.com.spaceNetwork.modelo;

public class Cidade {
	
	private int id;
	private String nome;
	private int populacao;
	private double area;
	private int estado_id;
	private String estado_nome;
	
	public Cidade( String nome, int população, double area, int estado_id) 
	{
		this.nome = nome;
		this.populacao = população;
		this.area = area;
		this.estado_id = estado_id;
	}
	public Cidade( int id, String nome, int população, double area, int estado_id) 
	{
		this.id = id;
		this.nome = nome;
		this.populacao = população;
		this.area = area;
		this.estado_id = estado_id;
	}
	
	public Cidade( int id, String nome, int população, double area, String estado_nome) 
	{
		this.id = id;
		this.nome = nome;
		this.populacao = população;
		this.area = area;
		this.estado_nome = estado_nome;
	}
	public Cidade (String estado_nome, Double area) 
	{
		this.area = area;
		this.estado_nome = nome;
	}
	public Cidade( int id, String nome, int população, double area,int estado_id, String estado_nome) 
	{
		this.id = id;
		this.nome = nome;
		this.populacao = população;
		this.area = area;
		this.estado_nome = estado_nome;
		this.estado_id = estado_id;
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

	public int getPopulação() {
		return populacao;
	}

	public void setPopulação(int população) {
		this.populacao = população;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}
	
	@Override
	public String toString() {
		
		return String.format(" %04d | %30s |%08d |%06.3f |%2s " , this.id,this.nome, this.populacao,this.area,this.estado_nome);
	}
	
}
