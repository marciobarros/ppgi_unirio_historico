package br.unirio.ppgi.historico.modelo;

import lombok.Getter;

/**
 * Enumeração dos possíveis status de resultado do curso
 * 
 * @author marciobarros
 */
public enum StatusCurso 
{
	Aprovado("APV"),
	Reprovado("REP");
	
	private @Getter String codigo;
	
	private StatusCurso(String codigo)
	{
		this.codigo = codigo;
	}
	
	public static StatusCurso get(String codigo)
	{
		for (StatusCurso status : values())
			if (status.getCodigo().compareTo(codigo) == 0)
				return status;
		
		return null;
	}
}