package ua.smolii.coding_challenge;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class Main {

	private static final List<Movie> movies;

	static {
		movies = asList(
				new Movie(new HashSet<>(asList(
						new Actor("John", "Doe"),
						new Actor("Jane", "Robson"))),
						10, 2017),
				new Movie(new HashSet<>(singletonList(
						new Actor("John", "Doe"))),
						1, 2017),
				new Movie(new HashSet<>(asList(
						new Actor("John", "Doe"),
						new Actor("Stew", "Johnson"))),
						2, 2010),
				new Movie(new HashSet<>(asList(
						new Actor("Robert", "Clay"),
						new Actor("Chris", "Rock"),
						new Actor("Ann", "Stewards"))),
						0, 1995)
		);
	}

	public static void main(String[] args) {
		// Print next

		//1. Number of movies per actor
		printNumberOfMoviesPerActor();

		//2. Actors who played in nominated movie
		printActorsWhoPlayedInNominatedMovies();

		//3. Number of nominated movies per actor
		printNumberOfNominatedMoviesPerActor();

		//4. Actors with >10 movies
		printActorsWithMoreThanTenMovies();

		//5. Number of nominated movies per year
		printNumberOfNominatedMoviesPerYear();

		//6. Percent of nominated movies
		printPercentOfNominatedMovies();

		//7. Most played actor in 1 year -> Should find actor, number of movies played, year
		printMostPlayedActorInOneYear();
	}

	//1. Number of movies per actor
	private static void printNumberOfMoviesPerActor() {
		System.out.println("1. Number of movies per actor");
		Map<Actor, Integer> moviesPerActor = new HashMap<>();

		fillMoviesPerYear(moviesPerActor);

		System.out.println(moviesPerActor);
	}

	//2. Actors who played in nominated movie
	private static void printActorsWhoPlayedInNominatedMovies() {
		System.out.println("\n2. Actors who played in nominated movie");
		Set<Actor> actors = new HashSet<>();

		for (Movie movie: movies) {
			if (isNominated(movie)) {
				actors.addAll(movie.getActors());
			}
		}

		System.out.println(actors);
	}

	//3. Number of nominated movies per actor
	private static void printNumberOfNominatedMoviesPerActor() {
		System.out.println("\n3. Number of nominated movies per actor");
		Map<Actor, Integer> moviesPerActor = new HashMap<>();

		for (Movie movie: movies) {
			if (isNominated(movie)) {
				for (Actor actor: movie.getActors()) {
					if (moviesPerActor.containsKey(actor)) {
						moviesPerActor.put(actor, moviesPerActor.get(actor) + 1);
					} else {
						moviesPerActor.put(actor, 1);
					}
				}
			}
		}

		System.out.println(moviesPerActor);
	}

	//4. Actors with >10 movies
	private static void printActorsWithMoreThanTenMovies() {
		System.out.println("\n4. Actors with >10 movies");
		Map<Actor, Integer> moviesPerActor = new HashMap<>();
		Set<Actor> moreThanTen = new HashSet<>();

		for (Movie movie: movies) {
			for (Actor actor: movie.getActors()) {
				if (moviesPerActor.containsKey(actor)) {
					moviesPerActor.put(actor, moviesPerActor.get(actor) + 1);
				} else {
					moviesPerActor.put(actor, 1);
				}

				if (moviesPerActor.containsKey(actor) && moviesPerActor.get(actor) >= 2) {
					moreThanTen.add(actor);
				}
			}
		}

		System.out.println(moreThanTen);
	}

	//5. Number of nominated movies per year
	private static void printNumberOfNominatedMoviesPerYear() {
		System.out.println("\n5. Number of nominated movies per year");
		Map<Integer, Integer> nominatedPerYear = new HashMap<>();

		for (Movie movie: movies) {
			if (isNominated(movie)) {
				if (nominatedPerYear.containsKey(movie.getYear())) {
					nominatedPerYear.put(movie.getYear(), nominatedPerYear.get(movie.getYear()) + 1);
				} else {
					nominatedPerYear.put(movie.getYear(), 1);
				}
			}
		}
		System.out.println(nominatedPerYear);
	}

	//6. Percent of nominated movies
	private static void printPercentOfNominatedMovies() {
		System.out.println("\n6. Percent of nominated movies");
		int nominated = 0;

		for (Movie movie: movies) {
			if (isNominated(movie)) nominated++;
		}

		double percentOfNominated = nominated / (movies.size() + 0.0) * 100;

		System.out.println(percentOfNominated);
	}

	//7. Most played actor in 1 year -> Should find actor, number of movies played, year
	private static void printMostPlayedActorInOneYear() {
		System.out.println("\n7. Most played actor in 1 year -> Should find actor, number of movies played, year");
		Map<ActorInYear, Integer> actorsInYear = new HashMap<>();

		ActorInYear mostPlayed = null;
		int moviesPlayed = 0;

		for (Movie movie: movies) {
			for (Actor actor: movie.getActors()) {
				ActorInYear actorInYear = new ActorInYear(actor, movie.getYear());

				if (actorsInYear.containsKey(actorInYear)) {
					actorsInYear.put(actorInYear, actorsInYear.get(actorInYear) + 1);
				} else {
					actorsInYear.put(actorInYear, 1);
				}

				if (mostPlayed == null || moviesPlayed < actorsInYear.get(actorInYear)) {
					mostPlayed = actorInYear;
					moviesPlayed = actorsInYear.get(actorInYear);
				}
			}
		}

		if (mostPlayed != null) {
			System.out.println(mostPlayed.getActor() + " " +  mostPlayed.getYear() + " " + moviesPlayed);
		}
	}

	private static void fillMoviesPerYear(Map<Actor, Integer> moviesPerActor) {
		for (Movie movie: movies) {
			for (Actor actor: movie.getActors()) {
				if (moviesPerActor.containsKey(actor)) {
					moviesPerActor.put(actor, moviesPerActor.get(actor) + 1);
				} else {
					moviesPerActor.put(actor, 1);
				}
			}
		}
	}

	private static boolean isNominated(Movie movie) {
		return movie.getNominations() > 0;
	}

	@Data
	@AllArgsConstructor
	@EqualsAndHashCode
	private static class ActorInYear {

		private Actor actor;
		private Integer year;
	}
}
