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
import br.unirio.ppgi.historico.suporte.XmlUtils;

/**
 * Classe responsavel por exportar um historico para formato XML
 * 
 * @author marciobarros
 */
public class ExportadorHistorico 
{
	/**
	 * Exporta os dados de um histórico
	 */
	public String exporta(Historico historico) throws ParserConfigurationException, TransformerException
	{
		// Cria o documento Xml
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
 
		// Cria o elemento raiz
		Element rootElement = doc.createElement("historico");
		doc.appendChild(rootElement);
 
		// Publica as informacoes do historico
		publicaCabecalho(historico, rootElement, doc);
		publicaDisciplinasCursadas(historico, rootElement, doc);

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
	 * Publica as caracteristicas basicas do historico
	 */
	private void publicaCabecalho(Historico historico, Element rootElement, Document document)
	{
		XmlUtils.createElement(rootElement, document, "curso", historico.getCurso().getId());
		XmlUtils.createElement(rootElement, document, "versao", historico.getVersao().getId());
		XmlUtils.createElement(rootElement, document, "emissao", historico.getDataEmissao());
		XmlUtils.createElement(rootElement, document, "matricula", historico.getMatricula());
		XmlUtils.createElement(rootElement, document, "nome", historico.getNome());
	}

	/**
	 * Publica os disciplinas cursadas
	 */
	private void publicaDisciplinasCursadas(Historico historico, Element rootElement, Document document)
	{
		Element xmlDisciplinas = XmlUtils.createElement(rootElement, document, "disciplinas");

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