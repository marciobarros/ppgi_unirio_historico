package br.unirio.ppgi.historico.suporte;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Class that supports generating XML files
 * 
 * @author marcio.barros
 */
public class XmlUtils
{
	/**
	 * Returns a single node identified by a tag
	 */
	public static Element getSingleElement(Element element, String tagName)
	{
		Node node = element.getFirstChild();
		
		while (node != null)
		{
			if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().compareTo(tagName) == 0)
				return (Element) node;
			
			node = node.getNextSibling();
		}
		
		return null;
	}
	
	/**
	 * Returns all node with a given tag
	 */
	public static List<Element> getElements(Element element, String tagName)
	{
		Node node = element.getFirstChild();
		List<Element> elements = new ArrayList<Element>();
		
		while (node != null)
		{
			if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().compareTo(tagName) == 0)
				elements.add((Element) node);
			
			node = node.getNextSibling();
		}
		
		return elements;
	}
	
	/**
	 * Loads an optional string attribute from a XML element
	 */
	public static String getStringAttribute(Element element, String name)
	{
		String value = element.getAttribute(name);
		return (value != null) ? value : "";
	}
	
	/**
	 * Loads an optional integer attribute from a XML element
	 */
	public static int getIntAttribute(Element element, String name) 
	{
		String value = element.getAttribute(name);
		
		if (value == null)
			return 0;
		
		if (value.length() == 0)
			return 0;
		
		return Integer.parseInt(value);
	}

	/**
	 * Loads an optional double attribute from a XML element
	 */
	public static double getDoubleAttribute(Element element, String name) 
	{
		String value = element.getAttribute(name);
		
		if (value == null)
			return 0.0;
		
		if (value.length() == 0)
			return 0.0;
		
		return Double.parseDouble(value);
	}

	/**
	 * Loads an optional datetime attribute from a XML element
	 */
	public static DateTime getDateAttribute(Element element, String name)
	{
		String value = element.getAttribute(name);
		
		if (value == null)
			return null;
		
		if (value.length() == 0)
			return null;
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
		return dtf.parseDateTime(value);
	}

	/**
	 * Loads an optional string element value from a XML element
	 */
	public static String getStringNode(Element element, String name)
	{
		return getStringNode(element, name, "");
	}

	/**
	 * Loads an optional string element value from a XML element
	 */
	public static String getStringNode(Element element, String name, String _default)
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return _default;
		
		String value = child.getTextContent();
		
		if (value == null)
			return _default;
		
		return value;
	}
	
	/**
	 * Loads an optional integer element value from a XML element
	 */
	public static int getIntNode(Element element, String name) 
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return 0;
		
		String value = child.getTextContent();
		
		if (value == null)
			return 0;
		
		if (value.length() == 0)
			return 0;
		
		return Integer.parseInt(value);
	}

	/**
	 * Loads an optional Boolean element value from a XML element
	 */
	public static boolean getBooleanNode(Element element, String name, boolean _default)
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return _default;
		
		String value = child.getTextContent();
		
		if (value == null)
			return _default;
		
		if (value.compareTo("S") == 0)
			return true;
		
		if (value.compareTo("N") == 0)
			return false;
		
		return _default;
	}

	/**
	 * Loads an optional double element value from a XML element
	 */
	public static double getDoubleNode(Element element, String name) 
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return 0.0;
		
		String value = child.getTextContent();
		
		if (value == null)
			return 0.0;
		
		if (value.length() == 0)
			return 0.0;
		
		return Double.parseDouble(value);
	}

	/**
	 * Loads an optional datetime element value from a XML element
	 */
	public static DateTime getDateNode(Element element, String name)
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return null;
		
		String value = child.getTextContent();
		
		if (value == null)
			return null;
		
		if (value.length() == 0)
			return null;
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
		return dtf.parseLocalDate(value).toDateTimeAtStartOfDay();
	}

	/**
	 * Loads an optional datetime element value from a XML element
	 */
	public static DateTime getDateTimeNode(Element element, String name)
	{
		Element child = getSingleElement(element, name);
		
		if (child == null)
			return null;
		
		String value = child.getTextContent();
		
		if (value == null)
			return null;
		
		if (value.length() == 0)
			return null;
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		return dtf.parseDateTime(value);
	}

	/**
	 * Creates an element without text content
	 */
	public static Element createElement(Element parent, Document document, String name)
	{
		Element element = document.createElement(name);
		parent.appendChild(element);
		return element;
	}

	/**
	 * Creates an element with a given text content
	 */
	public static Element createElement(Element parent, Document document, String name, String value)
	{
		Element element = createElement(parent, document, name);
		element.setTextContent(value);
		return element;
	}

	/**
	 * Creates an element with content in the form of an integer
	 */
	public static Element createElement(Element parent, Document document, String name, int value)
	{
		return createElement(parent, document, name, Integer.toString(value));
	}

	/**
	 * Creates an element with content in the form of a Boolean
	 */
	public static Element createElement(Element parent, Document document, String name, boolean value)
	{
		return createElement(parent, document, name, value ? "S" : "N");
	}

	/**
	 * Creates an element with content in the form of a double
	 */
	public static Element createElement(Element parent, Document document, String name, double value)
	{
		return createElement(parent, document, name, Double.toString(value));
	}

	/**
	 * Creates an element with content in the form of a date
	 */
	public static Element createElement(Element parent, Document document, String name, DateTime value)
	{
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
		return createElement(parent, document, name, dtf.print(value));
	}

	/**
	 * Creates an element with content in the form of a date/time
	 */
	public static Element createDateTimeElement(Element parent, Document document, String name, DateTime value)
	{
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		return createElement(parent, document, name, dtf.print(value));
	}
}