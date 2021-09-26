package model.services;

public class compoundInterestService implements OnlinePaymentService {

	// Compound interest
	private static final Double PAYMENT_FEE = 0.02;
	private static final Double INTEREST = 0.025;
	private static final String NAME = "Brazil Loan Service";
	
	public static String getName() {
		return NAME;
	}
	
	@Override
	public Double paymentFee(Double amount) {
		return amount * PAYMENT_FEE;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		double financingCoefficient = INTEREST / (1 - Math.pow(1 + INTEREST, -months));
		return amount * financingCoefficient ;
	}
}