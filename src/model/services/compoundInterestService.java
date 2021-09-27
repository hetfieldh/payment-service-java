package model.services;

public class compoundInterestService implements OnlinePaymentService {

	// Compound interest
	private Double paymentFee;
	private Double interest;

	public compoundInterestService(Double paymentFee, Double interest) {
		this.paymentFee = paymentFee;
		this.interest = interest;
	}

	@Override
	public Double paymentFee(Double amount) {
		return amount * paymentFee;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		//M = P . (1 + i)^n
		return amount * Math.pow((1 + interest), months);
	}
}