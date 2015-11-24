package br.unirio.ppgi.historico.modelo;

import lombok.Getter;

/**
 * Enumeração dos status de resultado de um aluno em uma disciplina
 * 
 * @author marciobarros
 */
public enum StatusDisciplina 
{
	Aprovado("APV"),
	Reprovado("REP");
	
	private @Getter String codigo;
	
	private StatusDisciplina(String codigo)
	{
		this.codigo = codigo;
	}
	
	public static StatusDisciplina get(String codigo)
	{
		for (StatusDisciplina status : values())
			if (status.getCodigo().compareTo(codigo) == 0)
				return status;
		
		return null;
	}
}