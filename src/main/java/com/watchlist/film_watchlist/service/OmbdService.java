package com.watchlist.film_watchlist.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchlist.film_watchlist.model.Film;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class OmbdService {
    private final ObjectMapper objectMapper;
    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    private final ObjectMapper mapper = new ObjectMapper();

    public OmbdService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Film> searchFilms(String title) {
        List<Film> results = new ArrayList<>();

        try {
            String urlString = apiUrl + "?apikey=" + apiKey + "&s=" + title.replace(" ", "+") + "&type=movie";

            String response = sendGetRequest(urlString);
            JsonNode root = objectMapper.readTree(response);

            if ("True".equals(root.path("Response").asText())) {
                JsonNode searchResults = root.path("Search");

                for (JsonNode node : searchResults) {
                    Film film = new Film();
                    film.setImdbID(node.get("imdbID").asText());
                    film.setTitle(node.get("Title").asText());
                    film.setYear(node.get("Year").asText());
                    film.setPoster(node.get("Poster").asText());
                    film.setGenre("");
                    film.setDirector("");
                    film.setPlot("");
                    film.setRuntime("");
                    film.setImdbRating("");
                    results.add(film);
                }
            }
        }catch (Exception e) {
            System.err.println("Greska pri pretrazi filmova: " + e.getMessage());
        }

        return results;
    }

    public Film getFilmById(String imdbId) {
        try{
            String urlString = apiUrl + "?apikey=" + apiKey + "&i=" + imdbId + "&plot=short";

            String response = sendGetRequest(urlString);
            JsonNode root = objectMapper.readTree(response);

            if ("True".equals(root.path("Response").asText())) {
                Film film = new Film();
                film.setImdbID(root.path("imdbID").asText());
                film.setTitle(root.path("Title").asText());
                film.setYear(root.path("Year").asText());
                film.setGenre(root.path("Genre").asText());
                film.setDirector(root.path("Director").asText());
                film.setPlot(root.path("Plot").asText());
                film.setPoster(root.path("Poster").asText());
                film.setRuntime(root.path("Runtime").asText());
                film.setImdbRating(root.path("imdbRating").asText());
                return film;
            }

        } catch (Exception e) {
            System.err.println("Greska pri dohvatanju filma: " + e.getMessage());
        }

        return null;
    }

    private String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
