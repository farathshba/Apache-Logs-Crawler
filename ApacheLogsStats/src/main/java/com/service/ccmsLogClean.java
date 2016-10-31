package com.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.service.browserSigParse;

import eu.bitwalker.useragentutils.UserAgent;

public class ccmsLogClean 
{
//	Delimiters
	private Scanner fileRdr;
	private PrintWriter fileWrt;
	private String inputFile, outputFile;
	
	//Scanner fileRdr = new Scanner(new File("Catalog.txt"));
	
	public ccmsLogClean(String inputFile, String outputFile)
	{
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	private void writeIntoCSV(PrintWriter fileWrt, String inputs)
	{
		try
		{
			fileWrt.print("\""+inputs+"\"");
		}
		catch(Exception e)
		{
			System.err.println("Error with writeIntoCSV!");
		}
	}
	
	public void startConversion()
	{
		String tempLine = null;
		int tempNum = 1;
		try
		{
			fileRdr = new Scanner(new File(inputFile));
			fileWrt = new PrintWriter(new File(outputFile));
			
			//	Create headers
			writeIntoCSV(fileWrt, "NEHR USER ID");
			fileWrt.print(",");
			writeIntoCSV(fileWrt, "BROWSER TYPE");
			fileWrt.print(",");
			writeIntoCSV(fileWrt, "COMPATIBLE MODE");
			fileWrt.println();
			
			while(fileRdr.hasNextLine())
			{
				System.out.println("----------------" + tempNum + "--------------------");
				tempLine = fileRdr.nextLine();
				String[] tokens = null;
				
				if(tempLine.contains(" \"-\" \""))
				{
					tokens = tempLine.split(" \"-\" \"");
				}
				else if(tempLine.contains(".aspx\" \""))
				{
					tokens = tempLine.split(".aspx\" \"");
				}
				else if(tempLine.contains("sg/\" "))
				{
					tokens = tempLine.split("sg/\" ");
				}
				else if(tempLine.contains("caremanagement\" \""))
				{
					tokens = tempLine.split("caremanagement\" \"");
				}
				else if(tempLine.contains("Intranet\" \""))
				{
					tokens = tempLine.split("Intranet\" \"");
				}
				
				//writeIntoCSV(fileWrt, firstSubTokens[0]);
				//fileWrt.print(",");
				//	String[] secondSubTokens = tokens[1].split("\" \"");
				String msie = "";
				String trid = "";
				
				//	If Internet Explorer is detected
				UserAgent userAgent = UserAgent.parseUserAgentString(tokens[1]);
				System.out.println(userAgent);
				//	If it's an Internet Explorer
				if(userAgent.getBrowser().toString().contains("IE"))
				{
					//	System.out.println("Browser >> IE");
					if(tokens[1].contains("MSIE"))
					{
						msie = tokens[1].substring(tokens[1].indexOf("MSIE"), tokens[1].indexOf("MSIE")+8);
					}
					if(tokens[1].contains("Trident"))
					{
						trid = tokens[1].substring(tokens[1].indexOf("Trident"), tokens[1].indexOf("Trident")+11);
					}
					browserSigParse uaparser = new browserSigParse(msie, trid);
					uaparser.getUserAgentDetails();
					System.out.println("Browser >> " + uaparser.getBrowser());
					System.out.println("Compatilibility >> " + uaparser.getCompatibility());
					writeIntoCSV(fileWrt, uaparser.getBrowser());
					fileWrt.print(",");
					writeIntoCSV(fileWrt, uaparser.getCompatibility());
				}
				else
				{
					System.out.println("Browser >> " + userAgent.getBrowser());
					//System.out.println(userAgent.getBrowser() + userAgent.getBrowserVersion().getVersion());
					writeIntoCSV(fileWrt, userAgent.getBrowser().toString());
				}
				fileWrt.print(",");
				fileWrt.print(tokens[1]);
				//System.out.println("----------------------------------------------------------------");
				fileWrt.println();
				tempNum++;
			}
			JOptionPane.showMessageDialog(null, "CSV Created!");
		}
		catch(Exception e)
		{
			System.err.println("Error occured in com.log.crawler.modal.cleaner.class");
			System.err.println(e.getMessage());
		}
		finally
		{
			fileWrt.close();
			fileRdr.close();
		}
	}
}
