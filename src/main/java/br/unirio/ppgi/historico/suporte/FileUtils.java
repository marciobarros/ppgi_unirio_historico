package br.unirio.ppgi.historico.suporte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Utility class for file handling
 * 
 * @author marcio.barros
 */
public class FileUtils
{
	/**
	 * Loads all contents of a file to a string
	 */
	private static String readAllText(String nomeArquivo, String lineSeparator, String encoding)
	{
		StringBuffer buffer = new StringBuffer();

		try
		{
			FileInputStream fis = new FileInputStream(nomeArquivo);
			InputStreamReader reader = new InputStreamReader(fis, encoding);
			
			//FileReader reader = new FileReader(nomeArquivo);
			BufferedReader breader = new BufferedReader(reader);
			String line;
			
			while ((line = breader.readLine()) != null)
				buffer.append(line + lineSeparator);
			
			breader.close();
			reader.close();
			fis.close();
			
		} catch(FileNotFoundException fnf)
		{
			return null;
		} catch (IOException e)
		{
			return null;
		}
		
		return buffer.toString();
	}

	/**
	 * Loads all contents of a UTF8 file to a string
	 */
	public static String readAllTextUTF8(String nomeArquivo)
	{
		return readAllText(nomeArquivo, " ", "UTF8");
	}

	/**
	 * Loads all contents of a file to a string
	 */
	public static String readAllText(String nomeArquivo)
	{
		String encoding = System.getProperty("file.encoding");
		return readAllText(nomeArquivo, " ", encoding);
	}

	/**
	 * Loads all contents of a file to a string
	 */
	public static String readAllLines(String nomeArquivo)
	{
		String encoding = System.getProperty("file.encoding");
		return readAllText(nomeArquivo, "\n", encoding);
	}

	/**
	 * Loads all contents of a file to a string
	 */
	public static String readAllLinesUTF8(String nomeArquivo)
	{
		return readAllText(nomeArquivo, "\n", "UTF8");
	}

	/**
	 * Saves the content of a string in a file
	 */
	public static void saveContent(String nomeArquivo, String conteudo) throws IOException
	{
		BufferedWriter writer = null;
		File logFile = new File(nomeArquivo);
		writer = new BufferedWriter(new FileWriter(logFile));
		writer.write(conteudo);
		writer.close();
	}

	/**
	 * Saves the content of a string in a file
	 */
	public static void saveContentUTF8(String nomeArquivo, String conteudo) throws IOException
	{
		BufferedWriter writer = null;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), "UTF-8"));
		writer.write(conteudo);
		writer.close();
	}
	
	/**
	 * Creates all directories required to save a file
	 */
	public static void createDirectories(String filename)
	{
		int separator = filename.lastIndexOf("/");
		
		if (separator != -1)
		{
			String diretorios = filename.substring(0, separator);
			new File(diretorios).mkdirs();
		}
	}

	/**
	 * Copy a file from one directory to another
	 */
    public static void copyToDirectory(String sourceLocation, String targetLocation) throws IOException 
    {
    	File sourceFile = new File(sourceLocation);
    	
    	if (!sourceFile.exists())
    		return;
    	
        File targetFolder = new File(targetLocation);

        InputStream in = new FileInputStream(sourceLocation);
        OutputStream out = new FileOutputStream(targetFolder + "\\"+ sourceFile.getName(), true);

        byte[] buf = new byte[1024];
        int len;
        
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        
        in.close();
        out.close();
    }
}