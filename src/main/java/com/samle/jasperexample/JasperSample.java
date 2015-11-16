package com.samle.jasperexample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class JasperSample 
{
	public static void main( String[] args )
	{
		try {
			JasperReport report = JasperCompileManager.compileReport("samplejson.jrxml");
			JRDataSource ds = new JsonDataSource("Customers.json", "Customers");
			JasperPrint printedReport = JasperFillManager.fillReport(report, new HashMap<String, Object>(), ds);
			String filenamePrefix = "SampleJson" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH-mm-SS"));
			JasperExportManager.exportReportToPdfFile(printedReport, filenamePrefix + ".pdf");
			JasperExportManager.exportReportToHtmlFile(printedReport, filenamePrefix + ".html");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
