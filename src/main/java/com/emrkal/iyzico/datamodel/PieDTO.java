package com.emrkal.iyzico.datamodel;

/**
 * Arayüzdeki pie-chart componentine ait dto sınıfıdır.
 * <p>
 * 
 * @see PieDTO
 * @version 1.0
 * @author Emrullah KALKAN
 */
public class PieDTO {
	private Long value;
	private String color;

	public PieDTO(Long value, String color) {
		super();
		this.value = value;
		this.color = color;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
