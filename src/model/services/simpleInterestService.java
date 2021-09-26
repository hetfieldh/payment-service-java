package model.services;

public class simpleInterestService implements OnlinePaymentService {

	// Simple interest
	private Double paymentFee;
	private Double interest;

	public simpleInterestService(Double paymentFee, Double interest) {
		this.paymentFee = paymentFee;
		this.interest = interest;
	}

	@Override
	public Double paymentFee(Double amount) {
		return amount * paymentFee;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		return amount * (1 + interest * months);
	}
}