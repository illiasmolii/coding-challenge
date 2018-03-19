package ua.smolii.coding_challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Actor {

	String firstName;
	String lastName;

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
