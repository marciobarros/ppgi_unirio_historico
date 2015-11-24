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
import br.unirio.ppgi.historico.modelo.StatusCurso;
import br.unirio.ppgi.historico.modelo.VersaoCurso;

/**
 * Classe responsavel por importar o conteudo de um arquivo de historico
 * 
 * @author marciobarros
 */
public class ImportadorHistorico 
{
	/**
	 * Importa o conteudo de um arquivo de historico
	 */
	public Historico importa(String conteudo) throws Exception
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
		String padrao = "(\\d{2}:\\d{2}) Hora: " + 
						".*Universidade Federal do Estado do Rio de Janeiro \\(UNIRIO\\) "+
						"Data: (\\d{2}\\/\\d{2}\\/\\d{4}) " +
						"([\\dMP]+) - .+ - .+ " +
						"([\\dMP]+) Matr.cula: " + 
						"Nome Aluno: (.+) " + 
						"Curso: Vers.o: (\\d{4}\\/\\d)";
		
		Pattern pattern = Pattern.compile(padrao);
		Matcher matcher = pattern.matcher(conteudo);
		
		if (!matcher.find())
			throw new Exception("Nao foi possivel encontrar o cabecalho do arquivo.");

		String hora = matcher.group(1);
		String data = matcher.group(2);
		String idCurso = matcher.group(3);
		String matricula = matcher.group(4);
		String nomeAluno = matcher.group(5);
		String versaoCurso = matcher.group(6);
		
		DateTimeFormatter sdf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
		DateTime dataEmissao = sdf.parseDateTime(data + " " + hora);
		
		historico.setCurso(idCurso);
		historico.setDataEmissao(dataEmissao);
		historico.setMatricula(matricula);
		historico.setNome(nomeAluno);
		historico.setVersaoCurso(versaoCurso);
	}

	/**
	 * Importa os dados de um trecho referente a um semestre
	 */
	private void importaSemestre(Historico historico, String conteudo) throws Exception
	{
		String padraoCabecalhoSemestre = "(\\d).\\. Semestre de (\\d{4}) Per.odo:";
		Pattern patternCabecalho = Pattern.compile(padraoCabecalhoSemestre);
		Matcher matcherCabecalho = patternCabecalho.matcher(conteudo);
		
		if (!matcherCabecalho.find())
			throw new Exception("O cabecalho de um semestre nao foi encontrado.");
		
		Curso curso = FabricaCursos.getInstance().pegaCursoIdentificador(historico.getCurso());
		
		if (curso == null)
			throw new Exception("O curso com identificador '" + historico.getCurso() + "' nao foi encontrado no sistema.");
		
		VersaoCurso versao = curso.pegaVersaoIdentificador(historico.getVersaoCurso());
		
		if (versao == null)
			throw new Exception("O curso '" + historico.getCurso() + "' nao possui uma versao '" + historico.getVersaoCurso() + "'.");

		int numero = Integer.parseInt(matcherCabecalho.group(1));
		int ano = Integer.parseInt(matcherCabecalho.group(2));

		String padraoDisciplina = "([\\dP]{3}) ([\\dM]{4}) .+? (\\d) (\\d{2}) ([\\d,]+) ([\\d,]+) (\\w{3})";
		Pattern patternDisciplina = Pattern.compile(padraoDisciplina);
		Matcher matcherDisciplina = patternDisciplina.matcher(conteudo);

		while (matcherDisciplina.find())
		{
			DisciplinaCursada cursada = capturaDisciplinaCursada(numero, ano, versao, matcherDisciplina);
			historico.adicionaDisciplinaCursada(cursada);
		}
	}

	/**
	 * Captura os dados de uma disciplina cursada dentro de um semestre
	 */
	private DisciplinaCursada capturaDisciplinaCursada(int numero, int ano, VersaoCurso versao, Matcher matcherDisciplina) throws Exception 
	{
		String codigoDisciplina = matcherDisciplina.group(1) + matcherDisciplina.group(2);
		Disciplina disciplina = versao.pegaDisciplinaCodigo(codigoDisciplina);
		
		if (disciplina == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' nao foi reconhecida pelo sistema.");
		
		int creditos = Integer.parseInt(matcherDisciplina.group(3));
		
		if (creditos == 0)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui um numero invalido de creditos (" + creditos + ").");

		int cargaHoraria = Integer.parseInt(matcherDisciplina.group(4));
		
		if (cargaHoraria != creditos * 15)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui carga horaria incompativel com o numero de creditos (" + cargaHoraria + "/" + creditos + ").");

		double nota = Double.parseDouble(matcherDisciplina.group(5).replace(',', '.'));
		
		if (nota < 0.0 || nota > 10.0)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui nota invalida (" + nota + ").");

		double frequencia = Double.parseDouble(matcherDisciplina.group(6).replace(',', '.'));
		
		if (frequencia < 0.0 || frequencia > 100.0)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui frequencia invalida (" + frequencia + ").");
		
		String statusDisciplina = matcherDisciplina.group(7);
		StatusCurso status = StatusCurso.get(statusDisciplina);
		
		if (status == null)
			throw new Exception("A disciplina com codigo '" + codigoDisciplina + "' possui um status desconhecido (" + statusDisciplina + ").");
		
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