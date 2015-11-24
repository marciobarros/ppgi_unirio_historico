package br.unirio.ppgi.historico.modelo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Classe que representa um curso
 * 
 * @author Marcio
 */
public class Curso 
{
	private @Getter String id;
	private @Getter String nome;
	private List<VersaoCurso> versoes;

	/**
	 * Inicializa um curso
	 */
	public Curso(String id, String nome)
	{
		this.id = id;
		this.nome = nome;
		this.versoes = new ArrayList<VersaoCurso>();
	}
	
	/**
	 * Adiciona uma versao no curso
	 */
	public VersaoCurso adicionaVersao(String idVersao)
	{
		VersaoCurso versao = new VersaoCurso(idVersao);
		versoes.add(versao);
		return versao;
	}

	/**
	 * Retorna uma versao do curso, dado seu identificador
	 */
	public VersaoCurso pegaVersaoIdentificador(String id) 
	{
		for (VersaoCurso versao : versoes)
			if (versao.getId().compareTo(id) == 0)
				return versao;
		
		return null;
	}
	
	/**
	 * Retorna uma enumeracao das versoes do curso
	 */
	public Iterable<VersaoCurso> getVersoes()
	{
		return versoes;
	}
}