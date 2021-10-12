package com.interestComp;

import java.io.*;
import java.util.*;
import java.text.*;

public class Calculation {
	
	public static void main(String[] args) {
		try {
			// config file contains - years, interest rate, amount.
			FileReader fr = new FileReader("config-file");
			Properties props = new Properties();
			props.load(fr);

			int years = Integer.parseInt(props.getProperty("YEARS"));
			double interestRate = Double.parseDouble(props.getProperty("RATE"));
			double principal = Double.parseDouble(props.getProperty("AMOUNT"));
			
			double totalAmountSI = simpleInterest(principal, interestRate / 100, years);
			double totalAmountCI = compoundInterest(principal, interestRate / 100, years);

			getData(principal, interestRate, totalAmountSI, totalAmountCI);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// For simple interest.
	public static double simpleInterest(double principal, double interestRate, double years) {
		return principal + (principal * interestRate * years);
	}

	// For compounding interest.
	public static double compoundInterest(double principal, double interestRate, double years) {
		return principal + (principal * Math.pow((1 + interestRate), years) - principal);
	}

	public static void getData(double principal, double interestRate, double totalAmountSI, double totalAmountCI) {
		
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-YYYY");

		System.out.println("\nOutput :- \n");
		System.out.println("Principal                          : " + "\t" + String.format("%.2f", principal));
		System.out.println("Rate of interest                   : " + "\t" + String.format("%.2f", interestRate) + "%");
		System.out.println("Today’s Date                       : " + "\t" + ft.format(date));
		System.out.println("Amount at maturity                 : " + "\t" + String.format("%.4f", totalAmountSI));
		System.out.println("Amount at maturity (compounding)   : " + "\t" + String.format("%.4f", totalAmountCI));
	}
}