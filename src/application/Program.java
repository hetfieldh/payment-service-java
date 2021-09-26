package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.enums.BankName;
import model.exceptions.DomainException;
import model.services.ContractService;
import model.services.compoundInterestService;
import model.services.simpleInterestService;

public class Program {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			Locale.setDefault(Locale.US);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("Enter contract data");
			System.out.print("Number: ");
			String number = sc.nextLine();
			System.out.print("Date (dd/MM/yyyy): ");
			sdf.setLenient(false);
			Date date = sdf.parse(sc.next());
			System.out.print("Contract value: ");
			Double totalValue = sc.nextDouble();

			Contract contract = new Contract(number, date, totalValue);

			System.out.print("Enter number of installments: ");
			Integer N = sc.nextInt();

			System.out.println("Select interest type:");
			System.out.println("1 - Simple");
			System.out.println("2 - Compound");
			System.out.print("Option: ");
			int opt = sc.nextInt();

			if (opt == 1) {
				ContractService cs = new ContractService(new simpleInterestService());
				cs.processSimpleInterestContract(contract, N);
			} else if (opt == 2) {
				ContractService cs = new ContractService(new compoundInterestService());
				cs.processSimpleInterestContract(contract, N);
			} else {
				throw new DomainException("Invalid type of contract");
			}

			System.out.println("Select a bank");
			System.out.println("1 - PAYPAL");
			System.out.println("2 - GOOGLE PAY");
			System.out.println("3 - SANTANDER");
			System.out.println("4 - CITIBANK");
			System.out.println("5 - BRADEsCO");
			System.out.print("Option: ");
			int opt2 = sc.nextInt();

			String bankName = "";
			
			switch (opt2) {
				case 1: 
					bankName = BankName.PAYPAL.getName();
					break;
				case 2:
					bankName = BankName.GOOGLE_PAY.getName();
					break;
				case 3:
					bankName = BankName.SANTANDER.getName();
					break;
				case 4: 
					bankName = BankName.CITIBANK.getName();
					break;
				case 5:
					bankName = BankName.BRADESCO.getName();
					break;
				default:
					throw new DomainException("Invalid bank option...");
			}

			double fullValue = 0.0;

			System.out.println();
			System.out.println("Bank Name: " + bankName);
			System.out.println("Contract date: " + sdf.format(date));
			System.out.println("Contract number: " + number);
			System.out.println("Installments: " + N);
			System.out.println("LIST OF INSTALLMENTS: ");
			System.out.println("----------------------------------------------");
			for (Installment it : contract.getInstallments()) {
				fullValue += it.getAmount();
				System.out.println(it);
			}
			System.out.println("----------------------------------------------");
			System.out.println("SUMMARY: ");
			System.out.println("----------------------------------------------");
			System.out.println("Contracted value: " + String.format("%.2f", totalValue));
			System.out.println("Interest and fee: " + String.format("%.2f", fullValue - totalValue));
			System.out.println("Total amount to pay: " + String.format("%.2f", fullValue));

		}catch(

	ParseException e)
	{
		System.out.println("*** ERROR: Invalid date! " + e.getMessage());
	}catch(
	InputMismatchException e)
	{
		System.out.println("*** ERROR: Only numbers are accepted.");
	}catch(
	DomainException e)
	{
		System.out.println("*** CONTRACT ERROR: " + e.getMessage());
	}
}}