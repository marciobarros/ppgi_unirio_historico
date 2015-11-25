package br.unirio.ppgi.historico.suporte;

import java.util.Arrays;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

/**
 * Utility class for PDF file handling
 * 
 * @author marcio.barros
 */
public class DocumentUtils 
{
	/**
	 * Converts a PDF file to its textual representation
	 */
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
}