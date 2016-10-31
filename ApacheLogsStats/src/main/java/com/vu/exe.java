package com.vu;

import java.util.Scanner;

import javax.swing.JFileChooser;
import com.service.concertoLogClean;
import com.utils.helpers;

public class exe 
{
	public static void main(String[] args) throws InterruptedException 
	{
		String input;
		System.out.println("This program is produced by Farath for MOHH-NEHR.");
		System.out.println("Version 0.6");
		concertoLogClean cleaner = null;
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);

		//	CCMSLogCleaner cleaner = null;
		String logFile = chooser.getSelectedFile().getName();
		
		if(logFile != null)
		{
			cleaner = new concertoLogClean(logFile, logFile.replaceAll(".log", ".csv"));
			//	cleaner = new CCMSLogCleaner(logFile, logFile.replaceAll(".log", ".csv"));
		}
		cleaner.startConversion();
		
//		System.out.println("Please choose the options carefully.");
//		System.out.println("1 : CCMS Browser Stats");
//		System.out.println("2 : Concerto Browser Stats");
		
//		input = helpers.readNumber();
//		switch(input)
//		{
//			case 1:	CCMS(); break;
//			case 2:	CONC(); break;
//			default: System.out.println("Please enter proper choice...\n..Ending program."); break;
//		}
		
		
	}
	
//	public static void CCMS()
//	{
//		System.out.println("Premium Feature of this project. Pay for usage. Ending...");
//	}
//	
//	public static void CONC()
//	{
//		concertoLogClean cleaner = null;
//		String logFile = fileChooser();
//		
//		if(logFile != null)
//		{
//			cleaner = new concertoLogClean(logFile, logFile.replaceAll(".log", ".csv"));
//		}
//		cleaner.startConversion();
//	}
	
	public static String fileChooser()
	{
		System.out.println("Please keep your log file(s) in the same directory of execution of this running program.\nKey in the exact name of the log file.\n >> ");
		String inp = helpers.readString();
		
		return inp;
	}
}
