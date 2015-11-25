package br.unirio.ppgi.historico.exportador;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.unirio.ppgi.historico.modelo.DisciplinaCursada;
import br.unirio.ppgi.historico.modelo.Historico;
import br.unirio.ppgi.historico.modelo.ListaHistoricos;
import br.unirio.ppgi.historico.suporte.XmlUtils;

/**
 * Classe responsavel por exportar historicos em formato XML
 * 
 * @author marciobarros
 */
public class ExportadorHistorico 
{
	/**
	 * Exporta os dados de um historico
	 */
	public String exporta(Historico historico) throws ParserConfigurationException, TransformerException
	{
		// Cria o documento Xml
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
 
		// Cria o elemento raiz
		Element rootElement = doc.createElement("historicos");
		doc.appendChild(rootElement);
 
		// Publica as informacoes do historico
		publicaHistorico(historico, rootElement, doc);

		// Salva as informacoes em formato Xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// Salva as informacoes em formato texto 
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString();
 	}

	/**
	 * Exporta os dados de uma lista de historicos
	 */
	public String exporta(ListaHistoricos historicos) throws ParserConfigurationException, TransformerException
	{
		// Cria o documento Xml
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
 
		// Cria o elemento raiz
		Element rootElement = doc.createElement("historicos");
		doc.appendChild(rootElement);
 
		// Publica as informacoes do historico
		for (Historico historico : historicos)
			publicaHistorico(historico, rootElement, doc);

		// Salva as informacoes em formato Xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// Salva as informacoes em formato texto 
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString();
 	}

	/**
	 * Publica os dados de um historico
	 */
	private void publicaHistorico(Historico historico, Element rootElement, Document document)
	{
		Element xmlHistorico = XmlUtils.createElement(rootElement, document, "historico");
		XmlUtils.createElement(xmlHistorico, document, "curso", historico.getCurso().getId());
		XmlUtils.createElement(xmlHistorico, document, "versao", historico.getVersao().getId());
		XmlUtils.createElement(xmlHistorico, document, "emissao", historico.getDataEmissao());
		XmlUtils.createElement(xmlHistorico, document, "matricula", historico.getMatricula());
		XmlUtils.createElement(xmlHistorico, document, "nome", historico.getNome());
		
		Element xmlDisciplinas = XmlUtils.createElement(xmlHistorico, document, "disciplinas");

		for (DisciplinaCursada disciplinaCursada : historico.getDisciplinasCursadas())
			publicaDisciplinaCursada(disciplinaCursada, xmlDisciplinas, document);
	}

	/**
	 * Publica os dados de uma disciplina cursada
	 */
	private void publicaDisciplinaCursada(DisciplinaCursada disciplina, Element rootElement, Document document)
	{
		Element xmlDisciplina = XmlUtils.createElement(rootElement, document, "disciplina");
		XmlUtils.createElement(xmlDisciplina, document, "semestre", disciplina.getSemestreDisciplina());
		XmlUtils.createElement(xmlDisciplina, document, "ano", disciplina.getAnoDisciplina());
		XmlUtils.createElement(xmlDisciplina, document, "codigo", disciplina.getDisciplina().getCodigo());
		XmlUtils.createElement(xmlDisciplina, document, "nota", disciplina.getNota());
		XmlUtils.createElement(xmlDisciplina, document, "frequencia", disciplina.getFrequencia());
		XmlUtils.createElement(xmlDisciplina, document, "status", disciplina.getStatus().getCodigo());
	}
}