//$Id: XMLCheck.java,v 1.1 2005/04/06 23:49:53 jn1936 Exp $

package com.sbc.eia.bis.facades.testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author jn1936
 *
 */
public class XMLCheck
{
	private static final String TEMP_FILE_NAME = "\\xmlTemp.xml";
	
	public XMLCheck()
	{
	}
	
	// Error handler that provides callback methods to the parser
	private final static class Handler extends DefaultHandler
	{
		public boolean hasError = false;
		public void error(SAXParseException e) throws SAXException
		{
			System.out.println(
				"ERROR: Encountered "
					+ e.getClass().getName()
					+ ": "
					+ e.getMessage());
			hasError = true;
		}
		public void fatalError(SAXParseException e) throws SAXException
		{
			System.out.println(
				"FATAL ERROR: Encountered "
					+ e.getClass().getName()
					+ ": "
					+ e.getMessage());
			hasError = true;
		}
		public void warning(SAXParseException e) throws SAXException
		{
			System.out.println(
				"WARNING: Encountered "
					+ e.getClass().getName()
					+ ": "
					+ e.getMessage());
		}
	}

	public boolean validate(
		boolean isNameSpace,
		boolean isFullSchema,
		String schemaLocation,
		String schemaFileName,
		String xmlMsg)
	{
		boolean isValid = false;
		try
		{

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			Handler handler = new Handler();
			XMLReader reader = parser.getXMLReader();

			// Set parser features
			try
			{
				reader.setFeature(
					"http://xml.org/sax/features/validation",
					true);
				reader.setFeature(
					"http://apache.org/xml/features/validation/schema",
					true);
				if (isNameSpace)
				{
					reader.setFeature(
						"http://xml.org/sax/features/namespaces",
						true);
					reader.setProperty(
						"http://apache.org/xml/properties/schema/external-schemaLocation",
						schemaLocation + schemaFileName);
				}
				else
				{
					reader.setFeature(
						"http://xml.org/sax/features/namespaces",
						false);
					reader.setProperty(
						"http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",
						schemaLocation + schemaFileName);
				}
				if (isFullSchema)
				{
					reader.setFeature(
						"http://apache.org/xml/features/validation/schema-full-checking",
						true);
				}
				else
				{
					reader.setFeature(
						"http://apache.org/xml/features/validation/schema-full-checking",
						false);
				}
			}
			catch (SAXException e)
			{
				System.out.println(
					"Warning: Parser does not support schema validation");
			}

			try
			{
				BufferedWriter out =
					new BufferedWriter(new FileWriter(schemaLocation + TEMP_FILE_NAME));
				out.write(xmlMsg);
				out.close();
			}
			catch (IOException e)
			{
			}

			parser.parse(schemaLocation + TEMP_FILE_NAME, handler);
			isValid = !handler.hasError;
		}
		catch (Exception e)
		{
			System.out.println(
				"Encountered "
					+ e.getClass().getName()
					+ ": "
					+ e.getMessage());
		}
		finally {
			File file = new File(schemaLocation + TEMP_FILE_NAME);	
			try
			{
				file.delete();
			}
			catch (Exception e1)
			{
				System.out.println("Could not delete " + schemaLocation + TEMP_FILE_NAME + ".");
				e1.printStackTrace();
			}
		}
		return isValid;
	}

}
