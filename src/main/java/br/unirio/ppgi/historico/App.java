package br.unirio.ppgi.historico;

import java.io.File;

import br.unirio.ppgi.historico.exportador.ExportadorHistorico;
import br.unirio.ppgi.historico.importador.ImportadorHistorico;
import br.unirio.ppgi.historico.importador.ImportadorListaHistoricos;
import br.unirio.ppgi.historico.modelo.Historico;
import br.unirio.ppgi.historico.modelo.ListaHistoricos;
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
//		String xml = new ExportadorHistorico().exporta(historicos);
//		FileUtils.saveContent("data/output/historico/Historicos Mestrado.xml", xml);
		
		for (Historico historico : historicos)
			verificaHistorico(historico);
	}
	
	protected static void verificaHistorico(Historico historico)
	{
//		São 2 disciplinas de núcleo básico, 2 disciplinas obrigatória (Metodologia Científica, Estágio Docência), 
//		Pesquisa pra Dissertação (a partir do 2o ano, uma por semestre), o resto até completar o mínimo de créditos é livre.

		
	}

	public static void main(String[] args) throws Exception
    {
		exportaHistoricosMestrado();
		//exportaTodosHistoricos();
    }
}