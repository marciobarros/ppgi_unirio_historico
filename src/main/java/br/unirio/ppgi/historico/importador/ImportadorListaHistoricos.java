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
public class ImportadorListaHistoricos 
{
	/**
	 * Importa o conteudo de um arquivo de historico
	 */
	public ListaHistoricos importa(String conteudo) throws Exception
	{
		ListaHistoricos historicos = new ListaHistoricos();
		
		int posicaoAtual = conteudo.indexOf("Data: ");
		
		if (posicaoAtual == -1)
			throw new Exception("");
		
		String sDataEmissao = conteudo.substring(posicaoAtual + 6, posicaoAtual + 16);
		posicaoAtual = posicaoAtual + 16;
		
		while (posicaoAtual < conteudo.length())
		{
			int posicaoFinal = conteudo.indexOf("Data: " + sDataEmissao, posicaoAtual);
			
			if (posicaoFinal == -1)
				posicaoFinal = conteudo.length();
			else
				posicaoFinal = posicaoFinal + 17;
			
			String conteudoHistorico = conteudo.substring(posicaoAtual, posicaoFinal);
			
			System.out.println(conteudoHistorico);
			System.out.println("=========");
			
			Historico historico = importaHistorico(conteudoHistorico);
			historicos.add(historico);
//			System.out.println(historico.getCurso().getId() + " " + historico.getVersao().getId() + " " + historico.getMatricula() + " " + historico.getNome());
			
			posicaoAtual = posicaoFinal;
		}
		
		return historicos;
	}
	
	private Historico importaHistorico(String conteudo) throws Exception
	{
		Historico historico = new Historico();
		
		conteudo = conteudo.replace('\n', ' ');
		importaCabecalho(historico, conteudo);
		
		int posicaoInicio = conteudo.indexOf(" Semestre de ");
		
		while (posicaoInicio > 0)
		{
			int posicaoFinal = conteudo.indexOf(" Semestre de ", posicaoInicio + 13);
			String trecho = (posicaoFinal != -1) ? conteudo.substring(posicaoInicio-3, posicaoFinal-3) : conteudo.substring(posicaoInicio-3);
			importaSemestre(historico, trecho);
			posicaoInicio = posicaoFinal;
		}
		
		return historico;
	}

	/**
	 * Importa o cabecalho do arquivo de historico
	 */
	private void importaCabecalho(Historico historico, String conteudo) throws Exception 
	{
		String padrao = "Vers.o: (\\d{4}\\/\\d) Reconhecimento: " + 
						"([\\dMP]+) - .+ - .+ Curso: " +
						"([\\dMP]+) - (.+) Aluno: ";
		
		Pattern pattern = Pattern.compile(padrao);
		Matcher matcher = pattern.matcher(conteudo);
		
		if (!matcher.find())
			throw new Exception("Nao foi possivel encontrar o cabecalho de um historico.");

		String idCurso = matcher.group(2);
		String matricula = matcher.group(3);
		String nomeAluno = matcher.group(4);
		String idVersaoCurso = matcher.group(1);
		
		Curso curso = FabricaCursos.getInstance().pegaCursoIdentificador(idCurso);
		
		if (curso == null)
			throw new Exception("O curso com identificador '" + idCurso + "' nao foi encontrado no sistema.");
		
		VersaoCurso versao = curso.pegaVersaoIdentificador(idVersaoCurso);
		
		if (versao == null)
			throw new Exception("O curso '" + idCurso + "' nao possui uma versao '" + idVersaoCurso + "'.");

//		DateTimeFormatter sdf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
//		DateTime dataEmissao = sdf.parseDateTime(data + " " + hora);
		
		historico.setCurso(curso);
//		historico.setDataEmissao(dataEmissao);
		historico.setMatricula(matricula);
		historico.setNome(nomeAluno);
		historico.setVersao(versao);
	}

	/**
	 * Importa os dados de um trecho referente a um semestre
	 */
	private void importaSemestre(Historico historico, String conteudo) throws Exception
	{
		String padraoCabecalhoSemestre = "(\\d).\\. Semestre de (\\d{4})";
		Pattern patternCabecalho = Pattern.compile(padraoCabecalhoSemestre);
		Matcher matcherCabecalho = patternCabecalho.matcher(conteudo);
		
		if (!matcherCabecalho.find())
			throw new Exception("O cabecalho de um semestre nao foi encontrado.");
		
		int numero = Integer.parseInt(matcherCabecalho.group(1));
		int ano = Integer.parseInt(matcherCabecalho.group(2));

		String padraoDisciplina = "([\\dMP]{7}) .+? \\d{2} (\\w{3}) \\d ([\\d,]+)";
		Pattern patternDisciplina = Pattern.compile(padraoDisciplina);
		Matcher matcherDisciplina = patternDisciplina.matcher(conteudo);

		while (matcherDisciplina.find())
		{
			DisciplinaCursada cursada = capturaDisciplinaCursada(numero, ano, historico.getVersao(), matcherDisciplina);
			historico.adicionaDisciplinaCursada(cursada);
		}
	}

	/**
	 * Captura os dados de uma disciplina cursada dentro de um semestre
	 */
	private DisciplinaCursada capturaDisciplinaCursada(int numero, int ano, VersaoCurso versao, Matcher matcherDisciplina) throws Exception 
	{
		String codigoDisciplina = matcherDisciplina.group(1);
		Disciplina disciplina = versao.pegaDisciplinaCodigo(codigoDisciplina);
		
		if (disciplina == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' nao foi reconhecida pelo sistema.");
		
		double nota = Double.parseDouble(matcherDisciplina.group(3).replace(',', '.'));
		
		if (nota < 0.0 || nota > 10.0)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui nota invalida (" + nota + ").");
		
		String statusDisciplina = matcherDisciplina.group(2);
		StatusDisciplina status = StatusDisciplina.get(statusDisciplina);
		
		if (status == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui um status desconhecido (" + statusDisciplina + ").");
		
		DisciplinaCursada cursada = new DisciplinaCursada();
		cursada.setDisciplina(disciplina);
		cursada.setAnoDisciplina(ano);
		cursada.setSemestreDisciplina(numero);
		cursada.setNota(nota);
		cursada.setFrequencia(0);
		cursada.setStatus(status);
		return cursada;
	}
}