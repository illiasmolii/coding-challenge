package ua.smolii.coding_challenge;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

	Set<Actor> actors;
	int nominations;
	int year;
}
