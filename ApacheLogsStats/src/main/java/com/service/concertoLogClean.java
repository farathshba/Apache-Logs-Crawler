package com.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.service.*;

import eu.bitwalker.useragentutils.UserAgent;

public class concertoLogClean 
{
	//	Delimiters
	private Scanner fileRdr;
	private PrintWriter fileWrt;
	private String inputFile, outputFile;
	
	//Scanner fileRdr = new Scanner(new File("Catalog.txt"));
	
	public concertoLogClean(String inputFile, String outputFile)
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
				String[] tokens = tempLine.split("userID=");
				String[] firstSubTokens = null;
				
				if(tokens[1].matches("(.*)&applicat(.*)"))
				{
					firstSubTokens = tokens[1].split("&");
				}
				else if(tokens[1].matches("(.*)HTT(.*)"))
				{
					firstSubTokens = tokens[1].split(" HTTP/1.1\"");
				}

				writeIntoCSV(fileWrt, firstSubTokens[0]);
				fileWrt.print(",");
				String[] secondSubTokens = tokens[1].split("\" \"");
				String msie = "";
				String trid = "";
				
				//	If Internet Explorer is detected
				UserAgent userAgent = UserAgent.parseUserAgentString(secondSubTokens[1]);
				System.out.println(userAgent);
				//	If it's an Internet Explorer
				if(userAgent.getBrowser().toString().contains("IE"))
				{
					if(secondSubTokens[1].contains("MSIE"))
					{
						msie = secondSubTokens[1].substring(secondSubTokens[1].indexOf("MSIE"), secondSubTokens[1].indexOf("MSIE")+8);
					}
					if(secondSubTokens[1].contains("Trident"))
					{
						trid = secondSubTokens[1].substring(secondSubTokens[1].indexOf("Trident"), secondSubTokens[1].indexOf("Trident")+11);
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
					
					if(userAgent.getBrowser().toString().toLowerCase().contains("chrome"))
					{
						writeIntoCSV(fileWrt, "Chrome");
					}
					else if(userAgent.getBrowser().toString().toLowerCase().contains("firefox"))
					{
						writeIntoCSV(fileWrt, "Firefox");
					}
					else if(userAgent.getBrowser().toString().toLowerCase().contains("opera"))
					{
						writeIntoCSV(fileWrt, "Opera");
					}
					else if(userAgent.getBrowser().toString().toLowerCase().contains("safari"))
					{
						writeIntoCSV(fileWrt, "Safari");
					}
					else if(userAgent.getBrowser().toString().toLowerCase().contains("edge"))
					{
						writeIntoCSV(fileWrt, "Edge");
					}
					
					//writeIntoCSV(fileWrt, userAgent.getBrowser().toString());
				}
				fileWrt.print(",");
				//fileWrt.print(secondSubTokens[1]);
				//System.out.println("----------------------------------------------------------------");
				fileWrt.println();
				tempNum++;
			}
			JOptionPane.showMessageDialog(null, "CSV Created!");
			fileWrt.close();
			fileRdr.close();
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
