package br.unirio.ppgi.historico;

import java.io.File;

import br.unirio.ppgi.historico.exportador.ExportadorHistorico;
import br.unirio.ppgi.historico.importador.ImportadorHistorico;
import br.unirio.ppgi.historico.importador.ImportadorListaHistoricos;
import br.unirio.ppgi.historico.importador.ImportadorListaHistoricosGraduacao;
import br.unirio.ppgi.historico.modelo.Disciplina;
import br.unirio.ppgi.historico.modelo.DisciplinaCursada;
import br.unirio.ppgi.historico.modelo.Historico;
import br.unirio.ppgi.historico.modelo.ListaHistoricos;
import br.unirio.ppgi.historico.modelo.StatusAluno;
import br.unirio.ppgi.historico.modelo.StatusDisciplina;
import br.unirio.ppgi.historico.modelo.TipoDisciplina;
import br.unirio.ppgi.historico.suporte.DocumentUtils;
import br.unirio.ppgi.historico.suporte.FileUtils;

public class App 
{
	protected static void exportaTodosHistoricos() throws Exception
	{
		File diretorio = new File("data/input/historico");

		for (File arquivo : diretorio.listFiles()) 
		{
			if (!arquivo.getAbsolutePath().contains("Historico"))
			{
				System.out.println("Processando " + arquivo.getName() + " ...");
				String conteudo = DocumentUtils.converteDocumentoTexto(arquivo.getAbsolutePath());
				Historico historico = new ImportadorHistorico().importa(conteudo);
				String xml = new ExportadorHistorico().exporta(historico);
				FileUtils.saveContent("data/output/historico/" + arquivo.getName().replace(".pdf", ".xml"), xml);
			}
		}
	}

	protected static void exportaHistoricosMestrado() throws Exception
	{
		String conteudo = DocumentUtils.converteDocumentoTexto("data/input/historico/Historicos Mestrado.pdf");
		ListaHistoricos historicos = new ImportadorListaHistoricos().importa(conteudo);
		String xml = new ExportadorHistorico().exporta(historicos);
		FileUtils.saveContent("data/output/historico/Historicos Mestrado.xml", xml);
		
		for (Historico historico : historicos)
			verificaHistorico(historico);
	}
	
	protected static void verificaHistorico(Historico historico)
	{
		boolean ok = true;
		
		if (historico.getStatus() == StatusAluno.Jubilado)
			return;
		
		int numeroNucleoBasico = 0;

		for (DisciplinaCursada disciplina : historico.getDisciplinasCursadas())
			if (historico.getVersao().verificaNucleoBasico(disciplina.getDisciplina().getCodigo()))
				numeroNucleoBasico++;
			
		if (numeroNucleoBasico < 2)
		{
			System.out.println(historico.getMatricula() + " " + historico.getNome() + ": menos de 2 disciplinas do nucleo basico (" + numeroNucleoBasico + ")");
			ok = false;
		}

		for (Disciplina disciplina : historico.getVersao().getDisciplinas())
			if (disciplina.getTipo() == TipoDisciplina.Obrigatoria && !historico.verificaDisciplinaCursada(disciplina.getCodigo()))
			{
				System.out.println(historico.getMatricula() + " " + historico.getNome() + ": nao cursou a " + disciplina.getNome() + " (obrigatoria)");
				ok = false;
			}
		
		int numeroReprovacoes = 0;

		for (DisciplinaCursada disciplina : historico.getDisciplinasCursadas())
			if (disciplina.getStatus() == StatusDisciplina.Reprovado)
				numeroReprovacoes++;
		
		if (numeroReprovacoes > 2)
		{
			System.out.println(historico.getMatricula() + " " + historico.getNome() + ": mais de duas reprovacoes (" + numeroReprovacoes + ")");
			ok = false;
		}
		
		int numeroCreditos = 0;

		for (DisciplinaCursada disciplina : historico.getDisciplinasCursadas())
			if (disciplina.getStatus() != StatusDisciplina.Reprovado)
				numeroCreditos += disciplina.getDisciplina().getCreditos();
		
		if (numeroCreditos < 34)
		{
			System.out.println(historico.getMatricula() + " " + historico.getNome() + ": menos de 34 creditos (" + numeroCreditos + ")");
			ok = false;
		}

		if (ok)
			System.out.println(historico.getMatricula() + " " + historico.getNome() + ": OK");
	}

	protected static void exportaHistoricosGraduacao() throws Exception
	{
		String conteudo = DocumentUtils.converteDocumentoTexto("data/input/graduacao/2015.2A.pdf");
		ListaHistoricos historicos = new ImportadorListaHistoricosGraduacao().importa(conteudo);
		String xml = new ExportadorHistorico().exporta(historicos);
		FileUtils.saveContent("data/output/graduacao/2015.2A.xml", xml);
		
		for (Historico historico : historicos)
			verificaHistorico(historico);
	}

	public static void main(String[] args) throws Exception
    {
		exportaHistoricosGraduacao();
		//exportaHistoricosMestrado();
		//exportaTodosHistoricos();
    }
}