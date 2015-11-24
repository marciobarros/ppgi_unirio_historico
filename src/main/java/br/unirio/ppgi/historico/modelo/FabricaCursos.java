package br.unirio.ppgi.historico.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma fabrica de cursos
 * 
 * @author Marcio
 */
public class FabricaCursos 
{
	/**
	 * Instancia unica da fabrica de cursos
	 */
	private static FabricaCursos instance = null;
	
	/**
	 * Lista de cursos registrados na fabrica
	 */
	private List<Curso> cursos;
	
	/**
	 * Inicializa a fabrica de cursos
	 */
	private FabricaCursos()
	{
		this.cursos = new ArrayList<Curso>();
		this.cursos.add(criaCursoMestrado());
	}
	
	/**
	 * Retorna a instancia unica da fabrica de cursos
	 */
	public static FabricaCursos getInstance()
	{
		if (instance == null)
			instance = new FabricaCursos();
		
		return instance;
	}

	/**
	 * Cria o curso de Mestrado do PPGI/UNIRIO, com suas versoes e disciplinas
	 */
	private Curso criaCursoMestrado()
	{
		Curso curso = new Curso("09P9M", "Informatica - Mestrado Academico");
		VersaoCurso versao = curso.adicionaVersao("2012/2");
		versao.adicionaDisciplina("09P9M01", "Modelagem de Sistemas de Informa��o", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M02", "An�lise e Projeto de Algoritmos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M03", "L�gica", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M04", "T�cnicas Avan�adas de Constru��o de Sistemas", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M05", "Fundamentos de Engenharia de Sof tware", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M06", "Fundamentos de Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M07", "Modelos de Sistemas de Computa��o e Comunica��o", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M09", "Intelig�ncia Artificial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M14", "Fundamentos de Sistemas Multimidia", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M52", "Fundamentos de Redes de Computadores", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M53", "Fundamentos de Sistemas de Informa��o", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M54", "Gest�o de dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M55", "Processo de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M08", "Metodologia Cient�fica Obrigat�ria", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M10", "Descoberta do Conhecimento em Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M11", "T�picos Especiais em Representa��o de Conhecimento e Racioc�nio I (Aplic. de L�gica e Pljto a SI)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M12", "T�picos Especiais em Representa��o de Conhecimento e Racioc�nio II (Ci�ncia da WEB)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M16", "T�picos Especiais em Sistemas Distribuidos I (SOA e MDA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M18", "Sistemas de Apoio � Intelig�ncia do Neg�cio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M20", "Ger�ncia de Projetos de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M21", "T�picos Especiais em Sistemas de Apoio a Neg�cios I (Medi��o de Software)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M22", "T�picos Especiais em Sistemas de Apoio a Neg�cios II (Acessibilidade)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M25", "Heur�sticas para Problemas Combinat�rios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M28", "Gest�o de Conhecimento e Aprendizagem Organizacional", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M29", "Intera��o Homem Computador", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M31", "Estudos Dirigidos I", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M32", "Estudos Dirigidos II", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M34", "Disciplina cursada em outra IES", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M38", "T�picos Especiais em Sistemas de Apoio a Neg�cios III (Cibercultura)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M39", "T�picos Especiais em Sistemas de Apoio a Neg�cios IV (Experimenta��o em ES)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M41", "T�picos Especiais em Representa��o de Conhecimento e Racioc�nio III (Aplica��o de IA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M43", "T�picos Especiais em Representa��o de Conhecimento e Racioc�nio IV", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M44", "Gest�o de Processos de Neg�cios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M45", "Sistemas Colaborativos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M46", "Arquitetura Empresarial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M47", "Gest�o de dados em ambientes distribu�dos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M48", "Redes de computadores sem fio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M49", "T�picos Especiais em Redes de computadores I ((Ferr. M. S. Avalia��o de Desempenho)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M50", "T�picos Especiais em Redes de Computadores II (Ger. Seg. Mob)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M51", "Sem�ntica na web", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M56", "Disciplina cursada em outra IES 2", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M23", "Pesquisa para Disserta��o - Mestrado I", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M24", "Est�gio Doc�ncia", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M35", "Pesquisa para Disserta��o - Mestrado II", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M36", "Pesquisa para Disserta��o - Mestrado III", TipoDisciplina.Optativa, 2, 30);
		versao.adicionaDisciplina("09P9M37", "Pesquisa para Disserta��o - Mestrado IV", TipoDisciplina.Optativa, 1, 15);
		return curso;
	}
	
	/**
	 * Retorna um curso, dado seu codigo
	 */
	public Curso pegaCursoIdentificador(String id)
	{
		for (Curso curso : cursos)
			if (curso.getId().compareTo(id) == 0)
				return curso;
		
		return null;
	}
}