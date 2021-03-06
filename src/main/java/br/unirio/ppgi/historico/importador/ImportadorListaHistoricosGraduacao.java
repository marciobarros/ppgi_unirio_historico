package br.unirio.ppgi.historico.importador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.unirio.ppgi.historico.modelo.Curso;
import br.unirio.ppgi.historico.modelo.Disciplina;
import br.unirio.ppgi.historico.modelo.DisciplinaCursada;
import br.unirio.ppgi.historico.modelo.FabricaCursos;
import br.unirio.ppgi.historico.modelo.Historico;
import br.unirio.ppgi.historico.modelo.ListaHistoricos;
import br.unirio.ppgi.historico.modelo.StatusDisciplina;
import br.unirio.ppgi.historico.modelo.VersaoCurso;

/**
 * Classe responsavel por importar o conteudo de um arquivo de historico
 * 
 * @author marciobarros
 */
public class ImportadorListaHistoricosGraduacao 
{
	/**
	 * Importa o conteudo de um arquivo de historicos
	 */
	public ListaHistoricos importa(String conteudo) throws Exception
	{
		ListaHistoricos historicos = new ListaHistoricos();
		
		int posicaoAtual = conteudo.indexOf("Data: ");
		
		if (posicaoAtual == -1)
			throw new Exception("");
		
		String sDataEmissao = conteudo.substring(posicaoAtual + 6, posicaoAtual + 16);
		posicaoAtual = posicaoAtual + 16;

		DateTimeFormatter sdf = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dataEmissao = sdf.parseDateTime(sDataEmissao);

		while (posicaoAtual < conteudo.length())
		{
			int posicaoFinal = conteudo.indexOf("Data: " + sDataEmissao, posicaoAtual);
			
			if (posicaoFinal == -1)
				posicaoFinal = conteudo.length();
			else
				posicaoFinal = posicaoFinal + 17;
			
			String conteudoHistorico = conteudo.substring(posicaoAtual, posicaoFinal);
			
			Historico historico = importaHistorico(conteudoHistorico, dataEmissao);
			historicos.add(historico);
			
			posicaoAtual = posicaoFinal;
		}
		
		return historicos;
	}
	
	/**
	 * Importa o historico de um aluno
	 */
	private Historico importaHistorico(String conteudo, DateTime dataEmissao) throws Exception
	{
		Historico historico = new Historico();
		historico.setDataEmissao(dataEmissao);
		
		conteudo = conteudo.replace('\n', ' ');
		importaCabecalho(historico, conteudo);
		importaRodape(historico, conteudo);
		
		int posicaoInicio = conteudo.indexOf(" semestre de ");
		
		while (posicaoInicio > 0)
		{
			int posicaoFinal = conteudo.indexOf(" semestre de ", posicaoInicio + 13);
			String trecho = (posicaoFinal != -1) ? conteudo.substring(posicaoInicio-3, posicaoFinal-3) : conteudo.substring(posicaoInicio-3);
			importaSemestre(historico, trecho);
			posicaoInicio = posicaoFinal;
		}
		
		return historico;
	}

	/**
	 * Importa o cabecalho do historico de um aluno
	 */
	private void importaCabecalho(Historico historico, String conteudo) throws Exception 
	{
		String padrao = "Curso: (\\d+) - .* - Turno .* Versão: (\\d+\\/\\d) Matrícula: (\\d+) (.+) Nome Aluno: ";

		Pattern pattern = Pattern.compile(padrao);
		Matcher matcher = pattern.matcher(conteudo);
		
		if (!matcher.find())
			throw new Exception("Nao foi possivel encontrar o cabecalho de um historico.");

		String idVersaoCurso = matcher.group(2);
		String idCurso = matcher.group(1);
		String matricula = matcher.group(3);
		String nomeAluno = matcher.group(4);
		
		Curso curso = FabricaCursos.getInstance().pegaCursoIdentificador(idCurso);
		
		if (curso == null)
			throw new Exception("O curso com identificador '" + idCurso + "' nao foi encontrado no sistema.");
		
		VersaoCurso versao = curso.pegaVersaoIdentificador(idVersaoCurso);
		
		if (versao == null)
			throw new Exception("O curso '" + idCurso + "' nao possui uma versao '" + idVersaoCurso + "'.");

		historico.setCurso(curso);
		historico.setMatricula(matricula);
		historico.setNome(nomeAluno);
		historico.setVersao(versao);
	}

	/**
	 * Importa o rodape do historico de um aluno
	 */
	private void importaRodape(Historico historico, String conteudo) throws Exception 
	{
//		Pattern pattern = Pattern.compile("Situa..o do Aluno (Sem evasão|S m  vasão|\\w{3})");
//		Matcher matcher = pattern.matcher(conteudo);
//		
//		if (!matcher.find())
//			throw new Exception("Nao foi possivel encontrar o cabecalho de um historico.");
//
//		String sStatus = matcher.group(1);
//		StatusAluno status = null;
//		
//		if (sStatus.compareTo("Sem evasão") == 0 || sStatus.compareTo("S m  vasão") == 0)
//			status = StatusAluno.Aberto;
//		else
//			status = StatusAluno.get(sStatus);
//		
//		if (status == null)
//			throw new Exception("O status do aluno '" + sStatus + "' nao foi encontrado no sistema.");
//		
//		historico.setStatus(status);
	}

	/**
	 * Importa os dados de um trecho referente a um semestre
	 */
	private void importaSemestre(Historico historico, String conteudo) throws Exception
	{
		// Processa o cabecalho do semestre
		Pattern patternCabecalho = Pattern.compile("(\\d).\\. semestre de (\\d{4})");
		Matcher matcherCabecalho = patternCabecalho.matcher(conteudo);

		System.out.println("===");
		System.out.println(conteudo);
		
		if (!matcherCabecalho.find())
			throw new Exception("O cabecalho de um semestre nao foi encontrado.");
		
		int numero = Integer.parseInt(matcherCabecalho.group(1));
		int ano = Integer.parseInt(matcherCabecalho.group(2));

		// Processa as disciplinas do semestre
		Pattern patternDisciplina = Pattern.compile("(\\w{3}\\d{4}) .+? ([\\d*,]+) (\\w{3})[\\s-\\w]+ ([\\d*,]+) \\d+ \\d");
		Matcher matcherDisciplina = patternDisciplina.matcher(conteudo);

		while (matcherDisciplina.find())
		{
			DisciplinaCursada cursada = capturaDisciplinaCursada(numero, ano, historico.getVersao(), matcherDisciplina);
			historico.adicionaDisciplinaCursada(cursada);
		}
	}

	/**
	 * Captura os dados de uma disciplina cursada em um semestre
	 */
	private DisciplinaCursada capturaDisciplinaCursada(int numero, int ano, VersaoCurso versao, Matcher matcherDisciplina) throws Exception 
	{
		String codigoDisciplina = matcherDisciplina.group(1);
		Disciplina disciplina = versao.pegaDisciplinaCodigo(codigoDisciplina);
		
		String statusDisciplina = matcherDisciplina.group(3);
		StatusDisciplina status = StatusDisciplina.get(statusDisciplina);
		
		String sFrequencia = matcherDisciplina.group(2);
		double frequencia = Double.parseDouble(sFrequencia.replace(',', '.'));
		
		String sNota = matcherDisciplina.group(4);
		double nota = 0.0;
				
		if (disciplina == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' nao foi reconhecida pelo sistema.");
		
		if (status == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui um status desconhecido (" + statusDisciplina + ").");
		
		if (status == StatusDisciplina.ADI || status == StatusDisciplina.Dispensado)
		{
			if (sNota.compareTo("116") != 0)
				throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' nao esta com uma nota invalida.");
		}
//		else if (status == StatusDisciplina.ASC || status == StatusDisciplina.Trancado || status == StatusDisciplina.REF)
//		{
//			if (sNota.compareTo("******") != 0)
//				throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' nao esta com uma nota invalida.");
//		}
		else
		{
			nota = Double.parseDouble(sNota.replace(',', '.'));
			
			if (nota < 0.0 || nota > 10.0)
				throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui nota invalida (" + nota + ").");
		}

		DisciplinaCursada cursada = new DisciplinaCursada();
		cursada.setDisciplina(disciplina);
		cursada.setAnoDisciplina(ano);
		cursada.setSemestreDisciplina(numero);
		cursada.setNota(nota);
		cursada.setFrequencia(frequencia);
		cursada.setStatus(status);
		return cursada;
	}
}