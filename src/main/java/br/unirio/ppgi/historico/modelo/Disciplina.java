package br.unirio.ppgi.historico.modelo;

import lombok.Getter;

/**
 * Classe que representa uma disciplina em um curso
 * 
 * @author Marcio
 */
public class Disciplina 
{
	private @Getter String codigo;
	private @Getter String nome;
	private @Getter TipoDisciplina tipo;
	private @Getter int creditos;
	private @Getter int cargaHoraria;
	
	public Disciplina(String codigo, String nome, TipoDisciplina tipo, int creditos, int cargaHoraria)
	{
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
		this.creditos = creditos;
		this.cargaHoraria = cargaHoraria;
	}
}