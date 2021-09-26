package application;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.enums.Banks;
import model.exceptions.DomainException;
import model.services.ContractService;
import model.services.compoundInterestService;
import model.services.simpleInterestService;

public class Program {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			Locale.setDefault(Locale.US);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

			// PART 1 - CHOOSE A BANK
			System.out.println("Welcome to the bank loan system");
			System.out.println();
			System.out.println("---> Choose a bank to generate loan:");
			System.out.println("1 - PAYPAL");
			System.out.println("2 - GOOGLE PAY");
			System.out.println("3 - SANTANDER");
			System.out.println("4 - CITIBANK");
			System.out.println("5 - BRADESCO");
			System.out.print("Option: ");
			int opt2 = sc.nextInt();
			sc.nextLine();

			String bankName;
			Double fee, simpleInterest, compoundInterest;

			switch (opt2) {
			case 1:
				bankName = Banks.PAYPAL.getName();
				fee = Banks.PAYPAL.getFee();
				simpleInterest = Banks.PAYPAL.getSimpleInterest();
				compoundInterest = Banks.PAYPAL.getCompoundInterest();
				break;
			case 2:
				bankName = Banks.GOOGLE_PAY.getName();
				fee = Banks.GOOGLE_PAY.getFee();
				simpleInterest = Banks.GOOGLE_PAY.getSimpleInterest();
				compoundInterest = Banks.GOOGLE_PAY.getCompoundInterest();
				break;
			case 3:
				bankName = Banks.SANTANDER.getName();
				fee = Banks.SANTANDER.getFee();
				simpleInterest = Banks.SANTANDER.getSimpleInterest();
				compoundInterest = Banks.SANTANDER.getCompoundInterest();
				break;
			case 4:
				bankName = Banks.CITIBANK.getName();
				fee = Banks.CITIBANK.getFee();
				simpleInterest = Banks.CITIBANK.getSimpleInterest();
				compoundInterest = Banks.CITIBANK.getCompoundInterest();
				break;
			case 5:
				bankName = Banks.BRADESCO.getName();
				fee = Banks.BRADESCO.getFee();
				simpleInterest = Banks.BRADESCO.getSimpleInterest();
				compoundInterest = Banks.BRADESCO.getCompoundInterest();
				break;
			default:
				throw new DomainException("Invalid bank option... Try again!");
			}

			// PART 2 - CONTRACT DATA
			System.out.println();
			System.out.println("---> Enter contract data of: " + bankName);
			System.out.print("Number: ");
			String number = sc.nextLine();
			System.out.print("Date (dd/MM/yyyy): ");
			sdf.setLenient(false);
			Date date = sdf.parse(sc.next());
			System.out.print("Value: ");
			Double totalValue = sc.nextDouble();

			Contract contract = new Contract(number, date, totalValue);

			// PART 3 - INSTALLMENTS
			System.out.print("Number of installments: ");
			Integer N = sc.nextInt();

			// PART 4 - INTEREST TYPE
			System.out.println();
			System.out.println("---> Interest type:");
			System.out.println("1 - Simple");
			System.out.println("2 - Compound");
			System.out.print("Option: ");
			int opt = sc.nextInt();

			if (opt == 1) {
				ContractService cs = new ContractService(new simpleInterestService(fee, simpleInterest));
				cs.processSimpleInterestContract(contract, N);
			} else if (opt == 2) {
				ContractService cs = new ContractService(new compoundInterestService(fee, compoundInterest));
				cs.processCompoundInterestContract(contract, N);
			} else {
				throw new DomainException("Invalid type of contract");
			}

			// PART 5 - SUMMARY
			Double fullValue = 0.0;

			System.out.println();
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
			System.out.println("Contracted value: " + numberFormat.format(totalValue));
			System.out.println("Interest and fee: " + numberFormat.format(fullValue - totalValue));
			System.out.println("Total amount to pay: " + numberFormat.format(fullValue));

		} catch (ParseException e) {
			System.out.println("*** ERROR: Invalid date! " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("*** ERROR: Only numbers are accepted.");
		} catch (DomainException e) {
			System.out.println("*** CONTRACT ERROR: " + e.getMessage());
		}
	}
}