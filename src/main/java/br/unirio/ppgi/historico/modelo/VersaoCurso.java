package br.unirio.ppgi.historico.modelo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Classe que representa uma versao de um curso
 * 
 * @author Marcio
 */
public class VersaoCurso 
{
	private @Getter String id;
	private List<Disciplina> disciplinas;
	private List<Disciplina> nucleoBasico;

	/**
	 * Inicializa a versao do curso
	 */
	public VersaoCurso(String id)
	{
		this.id = id;
		this.disciplinas = new ArrayList<Disciplina>();
		this.nucleoBasico = new ArrayList<Disciplina>();
	}
	
	/**
	 * Adiciona uma disciplina na versao do curso
	 */
	public VersaoCurso adicionaDisciplina(String codigo, String nome, TipoDisciplina tipo, int creditos, int cargaHoraria)
	{
		disciplinas.add(new Disciplina(codigo, nome, tipo, creditos, cargaHoraria));
		return this;
	}

	/**
	 * Retorna uma disciplina, dado seu codigo
	 */
	public Disciplina pegaDisciplinaCodigo(String codigo) 
	{
		for (Disciplina disciplina : disciplinas)
			if (disciplina.getCodigo().compareTo(codigo) == 0)
				return disciplina;
		
		return null;
	}
	
	/**
	 * Retorna uma enumeracao das disciplinas do curso
	 */
	public Iterable<Disciplina> getDisciplinas()
	{
		return disciplinas;
	}
	
	/**
	 * Adiciona uma disciplina no nucleo basico do curso
	 */
	public boolean adicionaNucleoBasico(String codigo)
	{
		Disciplina disciplina = pegaDisciplinaCodigo(codigo);
		
		if (disciplina != null)
		{
			nucleoBasico.add(disciplina);
			return true;
		}
		
		return false;
	}

	/**
	 * Verifica se uma disciplina pertence ao nucleo basico
	 */
	public boolean verificaNucleoBasico(String codigo) 
	{
		for (Disciplina disciplina : nucleoBasico)
			if (disciplina.getCodigo().compareTo(codigo) == 0)
				return true;
		
		return false;
	}
	
	/**
	 * Retorna uma enumeracao das disciplinas do nucleo basico do curso
	 */
	public Iterable<Disciplina> getDisciplinasNucleoBasico()
	{
		return nucleoBasico;
	}
}