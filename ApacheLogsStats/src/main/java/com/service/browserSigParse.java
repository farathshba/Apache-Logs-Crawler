package com.service;

public class browserSigParse 
{
	private double msie;
	private double trident;
	private String browser = "N/A";
	private String compatibility = "N/A";
	
	public browserSigParse(String msie, String trident)
	{
		double dblmsie = 0.0;
		double dbltrident = 0.0;
		
		if(!msie.isEmpty())
		{
			String msieSplitArr[] = msie.split(" ");
			dblmsie = Double.parseDouble(msieSplitArr[1]);
		}
		if(!trident.isEmpty())
		{
			String tridentSplitArr[] = trident.split("/");
			try
			{
				dbltrident = Double.parseDouble(tridentSplitArr[1]);
			}
			catch (Exception e)
			{
				dbltrident = Double.parseDouble(tridentSplitArr[1].split("\\)")[0]);
				System.out.println(dbltrident);
			}
		}
		
		this.msie = dblmsie;
		this.trident = dbltrident;
	}
	
	public void getUserAgentDetails()
	{		
		if(msie == 6.0){
			browser = "IE6";
		}
		else if(msie == 7.0)
		{
			if(trident == 1.0)
			{
				browser = "IE7";
				compatibility = "N/A";
			}
			else if(trident == 2.0)
			{
				browser = "IE7";
				compatibility = "N/A";
			}
			else if(trident == 3.0)
			{
				browser = "IE7";
				compatibility = "N/A";	
			}
			else if(trident == 4.0)
			{
				browser = "IE8";
				compatibility = "IE7";
			}
			else if(trident == 5.0)
			{
				browser = "IE9";
				compatibility = "IE7";
			}
			else if(trident == 6.0)
			{
				browser = "IE10";
				compatibility = "IE7";
			}
			else if(trident == 7.0)
			{
				browser = "IE11";
				compatibility = "IE7";
			}
		}
		else if(msie == 8.0)
		{
			if(trident == 1.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 2.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 3.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 4.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 5.0)
			{
				browser = "IE9";
				compatibility = "IE8";
			}
			else if(trident == 6.0)
			{
				browser = "IE10";
				compatibility = "IE8";
			}
			else if(trident == 7.0)
			{
				browser = "IE11";
				compatibility = "IE8";
			}
		}
		else if(msie == 9.0) 
		{
			if(trident == 1.0)
			{
				browser = "IE9";
				compatibility = "N/A";
			}
			else if(trident == 2.0)
			{
				browser = "IE9";
				compatibility = "N/A";
			}
			else if(trident == 3.0)
			{
				browser = "IE9";
				compatibility = "N/A";
			}
			else if(trident == 4.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 5.0)
			{
				browser = "IE9";
				compatibility = "N/A";
			}
			else if(trident == 6.0)
			{
				browser = "IE10";
				compatibility = "IE9";
			}
			else if(trident == 7.0)
			{
				browser = "IE11";
				compatibility = "IE9";
			}
		}
		else if(msie == 10.0) 
		{
			if(trident == 1.0)
			{
				browser = "IE10";
				compatibility = "N/A";
			}
			else if(trident == 2.0)
			{
				browser = "IE10";
				compatibility = "N/A";
			}
			else if(trident == 3.0)
			{
				browser = "IE10";
				compatibility = "N/A";
			}
			else if(trident == 4.0)
			{
				browser = "IE8";
				compatibility = "N/A";
			}
			else if(trident == 5.0)
			{
				browser = "IE9";
				compatibility = "N/A";
			}
			else if(trident == 6.0)
			{
				browser = "IE10";
				compatibility = "N/A";
			}
			else if(trident == 7.0)
			{
				browser = "IE11";
				compatibility = "N/A";
			}
		}
		else if(msie != 7.0 && msie != 8.0 && msie != 10.0)
		{
			if(trident==7.0){
				browser="IE11";
				compatibility = "N/A";
			}
		}
	}
	
	public String getBrowser()
	{
		return browser;
	}
	
	public String getCompatibility()
	{
		return compatibility;
	}
}
