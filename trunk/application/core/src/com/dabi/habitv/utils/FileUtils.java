package com.dabi.habitv.utils;

import java.io.InputStream;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.dabi.habitv.api.plugin.exception.TechnicalException;

public final class FileUtils {

	private FileUtils() {

	}

	/**
	 * replace illegal characters in a filename with "_" illegal characters : :
	 * \ / * ? | < >
	 * 
	 * @param name
	 * @return
	 */
	public static String sanitizeFilename(final String name) {
		return removeNonASCII(name.replaceAll("[\\s,:\\\\/*?!|<>&«»()\"\']", "_").replaceAll("__", "_"));
	}

	private static String removeNonASCII(final String string) {
		//return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		return string.replaceAll("[^\\x00-\\x7F]", "");
	}

	public static void setValidation(final Unmarshaller unmarshaller, final String xsdFile) {
		final Schema schema = buildSchema(xsdFile);
		unmarshaller.setSchema(schema);
	}

	public static void setValidation(final Marshaller unmarshaller, final String xsdFile) {
		final Schema schema = buildSchema(xsdFile);
		unmarshaller.setSchema(schema);
	}

	private static Schema buildSchema(final String xsdFile) {
		final SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		final Schema schema;
		try {
			schema = schemaFactory.newSchema(new StreamSource(getInputFileInClasspath(xsdFile)));
		} catch (final SAXException e) {
			throw new TechnicalException(e);
		}
		return schema;
	}

	private static InputStream getInputFileInClasspath(final String file) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
	}
}
