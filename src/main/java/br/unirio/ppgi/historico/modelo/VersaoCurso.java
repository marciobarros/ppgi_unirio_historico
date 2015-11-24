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

	/**
	 * Inicializa a versao do curso
	 */
	public VersaoCurso(String id)
	{
		this.id = id;
		this.disciplinas = new ArrayList<Disciplina>();
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
}