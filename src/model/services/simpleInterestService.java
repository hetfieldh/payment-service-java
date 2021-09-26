package model.services;

public class simpleInterestService implements OnlinePaymentService {

	// Simple interest
	private static final Double PAYMENT_FEE = 0.00;
	private static final Double INTEREST = 0.025;

	@Override
	public Double paymentFee(Double amount) {
		return amount * PAYMENT_FEE;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		return amount * (1 + INTEREST * months);
	}
}