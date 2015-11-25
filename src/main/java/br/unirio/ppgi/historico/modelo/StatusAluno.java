package br.unirio.ppgi.historico.modelo;

import lombok.Getter;

/**
 * Enumeração dos status de um aluno
 * 
 * @author marciobarros
 */
public enum StatusAluno 
{
	Jubilado("JUB"),
	Concluido("CON"),
	Aberto("ABR");
	
	private @Getter String codigo;
	
	private StatusAluno(String codigo)
	{
		this.codigo = codigo;
	}
	
	public static StatusAluno get(String codigo)
	{
		for (StatusAluno status : values())
			if (status.getCodigo().compareTo(codigo) == 0)
				return status;
		
		return null;
	}
}