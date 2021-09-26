package model.enums;

public enum BankName {

	PAYPAL("Paypal"), 
	GOOGLE_PAY("Google Pay"), 
	SANTANDER("Santander"), 
	CITIBANK("CitiBank"), 
	BRADESCO("Bradesco");

	private String name;

	BankName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
