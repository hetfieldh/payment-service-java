package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.DomainException;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processSimpleInterestContract(Contract contract, Integer months) {
		if (months <= 1) {
			throw new DomainException("The number of installments must be greater than 1.");
		}

		Double quota = onlinePaymentService.interest(contract.getTotalValue(), months) / months;

		for (int i = 1; i <= months; i++) {
			Double fullQuota = quota + onlinePaymentService.paymentFee(quota);

			Date dueDate = addMonths(contract.getDate(), i);

			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}

	public void processCompoundInterestContract(Contract contract, Integer months) {
		if (months <= 1) {
			throw new DomainException("The number of installments must be greater than 1.");
		}

		Double quota = onlinePaymentService.interest(contract.getTotalValue(), months);

		for (int i = 1; i <= months; i++) {
			Double fullQuota = quota + onlinePaymentService.paymentFee(quota);

			Date dueDate = addMonths(contract.getDate(), i);

			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}

	public Date addMonths(Date date, int M) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, M);
		return cal.getTime();
	}
}
