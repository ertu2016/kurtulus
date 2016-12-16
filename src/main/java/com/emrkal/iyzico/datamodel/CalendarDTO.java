package com.emrkal.iyzico.datamodel;

/**
 * Arayüzdeki takvim componentine ait dto sınıfıdır.
 * <p>
 * 
 * @see CalendarDTO
 * @version 1.0
 * @author Emrullah KALKAN
 */

public class CalendarDTO {

	public Long id;

	public String title;

	public String start;

	public String end;

	public String color;

	private String tooltip;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

}