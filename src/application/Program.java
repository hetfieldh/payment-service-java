package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.DomainException;
import model.services.ContractService;
import model.services.PaypalService;

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

			ContractService cs = new ContractService(new PaypalService());
			cs.processSimpleInterestContract(contract, N);

			System.out.println();
			System.out.println("INSTALLMENTS: ");
			System.out.println("----------------------------------------------");
			for (Installment it : contract.getInstallments()) {
				System.out.println(it);
			}
		} catch (ParseException e) {
			System.out.println("*** ERROR: Invalid date! " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("*** ERROR: Only numbers are accepted.");
		} catch (DomainException e) {
			System.out.println("*** CONTRACT ERROR: " + e.getMessage());
		}
	}
}