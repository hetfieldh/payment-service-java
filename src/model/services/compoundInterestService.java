package model.services;

public class compoundInterestService implements OnlinePaymentService {

	// Compound interest
	private static final Double PAYMENT_FEE = 0.00;
	private static final Double INTEREST = 0.04;

	@Override
	public Double paymentFee(Double amount) {
		return amount * PAYMENT_FEE;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		return amount * (INTEREST / (1 - Math.pow(1 + INTEREST, -months)));
	}
}