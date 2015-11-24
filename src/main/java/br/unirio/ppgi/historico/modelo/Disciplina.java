package br.unirio.ppgi.historico.modelo;

import lombok.Getter;

/**
 * Enumeração das disciplinas do nosso curso
 * 
 * @author marciobarros
 */
public enum Disciplina 
{
	D09P9M18("09P9M18", "?"),
	D09P9M54("09P9M54", "?"),
	D09P9M22("09P9M22", "?"),
	D09P9M45("09P9M45", "?"),
	D09P9M34("09P9M34", "?"),
	D09P9M38("09P9M38", "?"),
	D09P9M03("09P9M03", "?"),
	D09P9M29("09P9M29", "?"),
	D09P9M02("09P9M02", "?"),
	D09P9M07("09P9M07", "?"),
	D09P9M52("09P9M52", "?"),
	D09P9M48("09P9M48", "?"),
	D09P9M50("09P9M50", "?"),
	D09P9M10("09P9M10", "?"),
	D09P9M44("09P9M44", "?"),
	D09P9M12("09P9M12", "?"),
	D09P9M25("09P9M25", "?"),
	D09P9M16("09P9M16", "?"),
	D09P9M28("09P9M28", "Gestão de Conhecimento e Aprendizagem Organizacional"),
	D09P9M31("09P9M31", "Estudos Dirigidos I"),
	D09P9M53("09P9M53", "Fundamentos de Sistemas de Informação"),
	D09P9M55("09P9M55", "Processo de Software"),
	D09P9M08("09P9M08", "Metodologia Cientifica"),
	D09P9M21("09P9M21", "Tópicos Especiais em Sistemas de Apoio a Negócios I"),
	D09P9M32("09P9M32", "Estudos Dirigidos II"),
	D09P9M39("09P9M39", "Tópicos Especiais em Sistemas de Apoio a Negócios IV"),
	D09P9M23("09P9M23", "Pesquisa para Dissertação - Mestrado I"),
	D09P9M24("09P9M24", "Estágio Docência"),
	D09P9M35("09P9M35", "Pesquisa para Disertação - Mestrado II"),
	D09P9M36("09P9M36", "Pesquisa para Disertação - Mestrado III"),
	D09P9M37("09P9M37", "Pesquisa para Disertação - Mestrado IV");
	
	private @Getter String codigo;
	private @Getter String nome;
	
	private Disciplina(String codigo, String nome)
	{
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public static Disciplina get(String codigo)
	{
		for (Disciplina disciplina : values())
			if (disciplina.getCodigo().compareTo(codigo) == 0)
				return disciplina;
		
		return null;
	}
}