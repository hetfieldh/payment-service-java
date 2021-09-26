package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.exceptions.DomainException;

public class Contract {
	private String number;
	private Date date;
	private Double totalValue;

	private List<Installment> installments = new ArrayList<Installment>();

	public Contract() {
	}

	public Contract(String number, Date date, Double totalValue) {
		if (totalValue <= 0) {
			throw new DomainException("Contract value must be greater than zero");
		}
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}

	public String getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public List<Installment> getInstallments() {
		return installments;
	}
}