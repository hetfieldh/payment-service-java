package model.enums;

public enum Banks {
	// PARAMETER ORDER: NAME, FEE, SIMPLE_INTEREST, COMPOUND_INTEREST
	PAYPAL("Paypal", 0.020, 0.049, 0.030),
	GOOGLE_PAY("Google Pay", 0.020, 0.050, 0.033),
	SANTANDER("Santander", 0.020, 0.055, 0.035), 
	CITIBANK("CitiBank", 0.020, 0.045, 0.040),
	BRADESCO("Bradesco", 0.020, 0.054, 0.040);

	private String name;
	private Double fee;
	private Double simpleInterest;
	private Double compoundInterest;

	Banks(String name, Double fee, Double simpleInterest, Double compoundInterest) {
		this.name = name;
		this.fee = fee;
		this.simpleInterest = simpleInterest;
		this.compoundInterest = compoundInterest;
	}

	public String getName() {
		return name;
	}

	public Double getFee() {
		return fee;
	}

	public Double getSimpleInterest() {
		return simpleInterest;
	}

	public Double getCompoundInterest() {
		return compoundInterest;
	}
}