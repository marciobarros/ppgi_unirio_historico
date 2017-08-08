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
		this.cursos.add(criaCursoGraduacao());
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
		
		versao.adicionaDisciplina("09P9M01", "Modelagem de Sistemas de Informacao", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M02", "Analise e Projeto de Algoritmos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M03", "Logica", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M04", "Tecnicas Avancadas de Construcao de Sistemas", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M05", "Fundamentos de Engenharia de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M06", "Fundamentos de Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M07", "Modelos de Sistemas de Computacao e Comunicacao", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M09", "Inteligencia Artificial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M14", "Fundamentos de Sistemas Multimidia", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M52", "Fundamentos de Redes de Computadores", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M53", "Fundamentos de Sistemas de Informacao", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M54", "Gestao de dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M55", "Processo de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M08", "Metodologia Cientifica", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("09P9M10", "Descoberta do Conhecimento em Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M11", "Topicos Especiais em Representacao de Conhecimento e Raciocinio I (Aplic. de Logica e Pljto a SI)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M12", "Topicos Especiais em Representacao de Conhecimento e Raciocinio II (Ciencia da WEB)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M16", "Topicos Especiais em Sistemas Distribuidos I (SOA e MDA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M18", "Sistemas de Apoio a Inteligencia do Negocio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M20", "Gerencia de Projetos de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M21", "Topicos Especiais em Sistemas de Apoio a Negocios I (Medicao de Software)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M22", "Topicos Especiais em Sistemas de Apoio a Negocios II (Acessibilidade)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M25", "Heuristicas para Problemas Combinatorios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M28", "Gestao de Conhecimento e Aprendizagem Organizacional", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M29", "Interacao Homem Computador", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M31", "Estudos Dirigidos I", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M32", "Estudos Dirigidos II", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M34", "Disciplina cursada em outra IES", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M38", "Topicos Especiais em Sistemas de Apoio a Negocios III (Cibercultura)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M39", "Topicos Especiais em Sistemas de Apoio a Negocios IV (Experimentacao em ES)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M41", "Topicos Especiais em Representacao de Conhecimento e Raciocinio III (Aplicacao de IA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M43", "Topicos Especiais em Representacao de Conhecimento e Raciocinio IV", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M44", "Gestao de Processos de Negocios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M45", "Sistemas Colaborativos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M46", "Arquitetura Empresarial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M47", "Gestao de dados em ambientes distribuidos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M48", "Redes de computadores sem fio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M49", "Topicos Especiais em Redes de computadores I ((Ferr. M. S. Avaliacao de Desempenho)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M50", "Topicos Especiais em Redes de Computadores II (Ger. Seg. Mob)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M51", "Semantica na web", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M56", "Disciplina cursada em outra IES 2", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M23", "Pesquisa para Dissertacao - Mestrado I", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M24", "Estagio Docencia", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M35", "Pesquisa para Dissertacao - Mestrado II", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M36", "Pesquisa para Dissertacao - Mestrado III", TipoDisciplina.Optativa, 2, 30);
		versao.adicionaDisciplina("09P9M37", "Pesquisa para Dissertacao - Mestrado IV", TipoDisciplina.Optativa, 1, 15);
		
		versao.adicionaNucleoBasico("09P9M53");
		versao.adicionaNucleoBasico("09P9M02");
		versao.adicionaNucleoBasico("09P9M54");
		versao.adicionaNucleoBasico("09P9M09");
		versao.adicionaNucleoBasico("09P9M03");
		versao.adicionaNucleoBasico("09P9M55");
		versao.adicionaNucleoBasico("09P9M04");
		versao.adicionaNucleoBasico("09P9M07");
		versao.adicionaNucleoBasico("09P9M52");

		return curso;
	}

	/**
	 * Cria o curso de Graduação do BSI/UNIRIO, com suas versoes e disciplinas
	 */
	private Curso criaCursoGraduacao()
	{
		Curso curso = new Curso("210", "Sistemas de Informação - Bacharelado");
		VersaoCurso versao = curso.adicionaVersao("2008/1");
		
		versao.adicionaDisciplina("TIN0106", "DESENVOLVIMENTO DE PÁGINAS WEB", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0112", "FUNDAMENTOS DE SISTEMAS DE INFORMAÇÃO", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TME0101", "MATEMÁTICA BÁSICA", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("TIN0108", "ORGANIZAÇÃO DE COMPUTADORES", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0107", "TÉCNICAS DE PROGRAMAÇÃO I", TipoDisciplina.Obrigatoria, 5, 90);
		versao.adicionaDisciplina("HTD0058", "TEORIAS E PRÁTICAS DISCURSIVAS NA ESFERA ACADÊMICA", TipoDisciplina.Obrigatoria, 3, 60);
		
		versao.adicionaDisciplina("TME0015", "Álgebra Linear", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0150", "AMBIENTE OPERACIONAL UNIX", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0013", "Análise Empresarial e Administrativa", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("HFC0008", "COMUNICAÇÃO", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0105", "INTRODUÇÃO À LÓGICA COMPUTACIONAL", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0116", "SISTEMAS OPERACIONAIS", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0011", "TÉCNICAS DE PROGRAMAÇÃO II", TipoDisciplina.Obrigatoria, 4, 60);

		versao.adicionaDisciplina("TIN0120", "BANCO DE DADOS I", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0114", "ESTRUTURAS DE DADOS I", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0110", "INTERAÇÃO HUMANO-COMPUTADOR", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0123", "REDES DE COMPUTADORES I", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TIN0141", "TÓPICOS AVANÇADOS EM REDES DE COMPUTADORES I", TipoDisciplina.Obrigatoria, 4, 60);
		versao.adicionaDisciplina("TME0112", "CÁLCULO DIFERENCIAL E INTEGRAL I", TipoDisciplina.Obrigatoria, 4, 60);
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