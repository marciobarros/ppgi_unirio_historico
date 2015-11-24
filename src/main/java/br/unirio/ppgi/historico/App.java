package br.unirio.ppgi.historico;

import java.io.File;
import java.util.Arrays;

import br.unirio.ppgi.historico.exportador.ExportadorHistorico;
import br.unirio.ppgi.historico.importador.ImportadorHistorico;
import br.unirio.ppgi.historico.modelo.Historico;
import br.unirio.ppgi.historico.suporte.FileUtils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class App 
{
	public static String converteDocumentoTexto(String nomeArquivo)
	{
		try
		{
			PdfReader reader = new PdfReader(nomeArquivo);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	
			char[] chars = new char[nomeArquivo.length()];
			Arrays.fill(chars, '=');
	
			StringBuffer sb = new StringBuffer();
	
			for (int i = 1; i <= reader.getNumberOfPages(); i++)
			{
				TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				sb.append(strategy.getResultantText());
			}
	
			reader.close();
			return sb.toString();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	protected static void exportaTodosHistoricos() throws Exception
	{
		File diretorio = new File("data/input/historico");

		for (File arquivo : diretorio.listFiles()) 
		{
			System.out.println("Processando " + arquivo.getName() + " ...");
			String conteudo = converteDocumentoTexto(arquivo.getAbsolutePath());
			Historico historico = new ImportadorHistorico().importa(conteudo);
			String xml = new ExportadorHistorico().exporta(historico);
			FileUtils.saveContent("data/output/historico/" + arquivo.getName().replace(".pdf", ".xml"), xml);
		}
	}

	protected static void exportaHistoricosMestrado() throws Exception
	{
		String conteudo = converteDocumentoTexto("data/input/historico/Historicos DSc.pdf");
		System.out.println(conteudo);
		Historico historico = new ImportadorHistorico().importa(conteudo);
		String xml = new ExportadorHistorico().exporta(historico);
		FileUtils.saveContent("data/output/historico/Historicos DSc.xml", xml);
	}

	public static void main(String[] args) throws Exception
    {
		exportaHistoricosMestrado();
    }
}