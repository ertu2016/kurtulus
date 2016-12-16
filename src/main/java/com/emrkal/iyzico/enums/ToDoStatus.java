package com.emrkal.iyzico.enums;

import java.util.Arrays;
import java.util.List;

/**
 * ToDoStatus Enumration sınıfıdır.
 * <p>
 * 
 * @see ToDoStatus
 * @version 1.0
 * @author Emrullah KALKAN
 */

public enum ToDoStatus {

	TODO(0, "grey"), INPROGRESS(1, "blue"), DONE(2, "green");
	private final Integer value;
	private final String colour;

	ToDoStatus(Integer value, String colour) {
		this.value = value;
		this.colour = colour;
	}

	public static ToDoStatus fromValue(Integer value) {
		if (value != null) {
			List<ToDoStatus> directions = Arrays.asList(ToDoStatus.values());
			return directions.stream().filter(status -> status.value.equals(value)).findFirst().orElse(null);
		}
		return getDefault();

	}

	public Integer toValue() {
		return value;
	}

	public String getColour() {
		return colour;
	}

	public static ToDoStatus getDefault() {
		return null;
	}

}
