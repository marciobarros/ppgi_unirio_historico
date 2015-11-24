package br.unirio.ppgi.historico.modelo;

import lombok.Data;

/**
 * Classe que representa uma disciplina cursada no histórico
 * 
 * @author marciobarros
 */
public @Data class DisciplinaCursada 
{
	private Disciplina disciplina;
	private int semestreDisciplina;
	private int anoDisciplina;
	private double nota;
	private double frequencia;
	private StatusCurso status;
}