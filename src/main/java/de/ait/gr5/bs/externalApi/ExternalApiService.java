package de.ait.gr5.bs.externalApi;

import com.fasterxml.jackson.databind.JsonNode;
import de.ait.gr5.bs.models.City;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

  @Value("${API_URL_FOUND_CITY}")
  private String apiUrlFoundCity;
  @Value("${API_KEY_ZIPCODEBASE}")
  private String apiKey;

  @Autowired
  private RestTemplate restTemplate;

  public City getCityByPostalCode(String searchPostalCode) {

    String apiUrl = apiUrlFoundCity + "?apikey=" + apiKey + "&codes=" + searchPostalCode + "&country=" + "DE";

    ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(apiUrl, JsonNode.class);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      JsonNode response = responseEntity.getBody();
      JsonNode resultsNode = response != null ? response.path("results").path(searchPostalCode) : null;

      if (resultsNode != null && resultsNode.size() > 0) {
        JsonNode cityNode = resultsNode.get(0);

        String city = cityNode.path("city").asText();


        if (!city.isEmpty()) {
          return City.builder()
              .postalCode(searchPostalCode)
              .titleCity(city).build();
        }
      }
    }
    return null;
  }
}
