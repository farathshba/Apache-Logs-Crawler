package com.utils;

import java.util.Scanner;

public class helpers 
{
	private static Scanner scanner;
	public static String readString()
	{
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public static int readNumber()
	{
		scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	
	public static int readChar()
	{
		scanner = new Scanner(System.in);
		return scanner.next().charAt(0);
	}
}
