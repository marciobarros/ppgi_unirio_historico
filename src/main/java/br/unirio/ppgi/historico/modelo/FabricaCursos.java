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
		versao.adicionaDisciplina("09P9M01", "Modelagem de Sistemas de Informação", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M02", "Análise e Projeto de Algoritmos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M03", "Lógica", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M04", "Técnicas Avançadas de Construção de Sistemas", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M05", "Fundamentos de Engenharia de Sof tware", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M06", "Fundamentos de Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M07", "Modelos de Sistemas de Computação e Comunicação", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M09", "Inteligência Artificial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M14", "Fundamentos de Sistemas Multimidia", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M52", "Fundamentos de Redes de Computadores", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M53", "Fundamentos de Sistemas de Informação", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M54", "Gestão de dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M55", "Processo de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M08", "Metodologia Científica Obrigatória", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M10", "Descoberta do Conhecimento em Banco de Dados", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M11", "Tópicos Especiais em Representação de Conhecimento e Raciocínio I (Aplic. de Lógica e Pljto a SI)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M12", "Tópicos Especiais em Representação de Conhecimento e Raciocínio II (Ciência da WEB)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M16", "Tópicos Especiais em Sistemas Distribuidos I (SOA e MDA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M18", "Sistemas de Apoio à Inteligência do Negócio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M20", "Gerência de Projetos de Software", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M21", "Tópicos Especiais em Sistemas de Apoio a Negócios I (Medição de Software)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M22", "Tópicos Especiais em Sistemas de Apoio a Negócios II (Acessibilidade)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M25", "Heurísticas para Problemas Combinatórios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M28", "Gestão de Conhecimento e Aprendizagem Organizacional", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M29", "Interação Homem Computador", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M31", "Estudos Dirigidos I", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M32", "Estudos Dirigidos II", TipoDisciplina.Eletiva, 2, 30);
		versao.adicionaDisciplina("09P9M34", "Disciplina cursada em outra IES", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M38", "Tópicos Especiais em Sistemas de Apoio a Negócios III (Cibercultura)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M39", "Tópicos Especiais em Sistemas de Apoio a Negócios IV (Experimentação em ES)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M41", "Tópicos Especiais em Representação de Conhecimento e Raciocínio III (Aplicação de IA)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M43", "Tópicos Especiais em Representação de Conhecimento e Raciocínio IV", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M44", "Gestão de Processos de Negócios", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M45", "Sistemas Colaborativos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M46", "Arquitetura Empresarial", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M47", "Gestão de dados em ambientes distribuídos", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M48", "Redes de computadores sem fio", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M49", "Tópicos Especiais em Redes de computadores I ((Ferr. M. S. Avaliação de Desempenho)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M50", "Tópicos Especiais em Redes de Computadores II (Ger. Seg. Mob)", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M51", "Semântica na web", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M56", "Disciplina cursada em outra IES 2", TipoDisciplina.Optativa, 4, 60);
		versao.adicionaDisciplina("09P9M23", "Pesquisa para Dissertação - Mestrado I", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M24", "Estágio Docência", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M35", "Pesquisa para Dissertação - Mestrado II", TipoDisciplina.Obrigatoria, 2, 30);
		versao.adicionaDisciplina("09P9M36", "Pesquisa para Dissertação - Mestrado III", TipoDisciplina.Optativa, 2, 30);
		versao.adicionaDisciplina("09P9M37", "Pesquisa para Dissertação - Mestrado IV", TipoDisciplina.Optativa, 1, 15);
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