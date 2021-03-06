package br.unirio.ppgi.historico.modelo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

/**
 * Classe que representa o histórico de um aluno
 * 
 * @author marciobarros
 */
public class Historico 
{
	private @Getter @Setter DateTime dataEmissao;
	private @Getter @Setter String matricula = "";
	private @Getter @Setter String nome = "";
	private @Getter @Setter Curso curso = null;
	private @Getter @Setter VersaoCurso versao = null;
	private @Getter @Setter StatusAluno status = null;
	private List<DisciplinaCursada> disciplinasCursadas;

	/**
	 * Inicializa o histórico
	 */
	public Historico()
	{
		this.disciplinasCursadas = new ArrayList<DisciplinaCursada>();
	}
	
	/**
	 * Retorna o número de disciplinas cursadas
	 */
	public int pegaNumeroDisciplinasCursadas()
	{
		return disciplinasCursadas.size();
	}
	
	/**
	 * Retorna uma disciplina cursada, dado seu índice
	 */
	public DisciplinaCursada pegaDisciplinaCursadaIndice(int indice)
	{
		return disciplinasCursadas.get(indice);
	}
	
	/**
	 * Adiciona uma disciplina cursada no histórico
	 */
	public void adicionaDisciplinaCursada(DisciplinaCursada disciplina)
	{
		disciplinasCursadas.add(disciplina);
	}
	
	/**
	 * Retorna as disciplinas cursadas do histórico
	 */
	public Iterable<DisciplinaCursada> getDisciplinasCursadas()
	{
		return disciplinasCursadas;
	}

	/**
	 * Verifica se uma disciplina foi cursada com sucesso
	 */
	public boolean verificaDisciplinaCursada(String codigo) 
	{
		for (DisciplinaCursada disciplina : disciplinasCursadas)
			if (disciplina.getStatus() != StatusDisciplina.Reprovado && disciplina.getDisciplina().getCodigo().compareTo(codigo) == 0)
				return true;
		
		return false;
	}
}