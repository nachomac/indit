package com.inditex;

import com.inditex.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InditexApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	@TestConfiguration
	static class RestTemplateTestConfig {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Test
	public void pricesShouldBeInDB() throws Exception {

		insertPrices();

		// Now perform the GET request and assert the response
		ResponseEntity<Price[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/prices?brandId=1&productId=35455&dateAppl=2020-06-14-10.00.00", Price[].class);
		List<Price> prices = Arrays.asList(responseEntity.getBody());
		assertThat(prices.size()>0);
		responseEntity = restTemplate.getForEntity("http://localhost:8080/prices?brandId=1&productId=35455&dateAppl=2020-06-14-16.00.00", Price[].class);
		prices = Arrays.asList(responseEntity.getBody());
		assertThat(prices.size()>0);
		responseEntity = restTemplate.getForEntity("http://localhost:8080/prices?brandId=1&productId=35455&dateAppl=2020-06-14-21.00.00", Price[].class);
		prices = Arrays.asList(responseEntity.getBody());
		assertThat(prices.size()>0);
		responseEntity = restTemplate.getForEntity("http://localhost:8080/prices?brandId=1&productId=35455&dateAppl=2020-06-15-10.00.00", Price[].class);
		prices = Arrays.asList(responseEntity.getBody());
		assertThat(prices.size()>0);
		responseEntity = restTemplate.getForEntity("http://localhost:8080/prices?brandId=1&productId=35455&dateAppl=2020-06-15-21.00.00", Price[].class);
		prices = Arrays.asList(responseEntity.getBody());
		assertThat(prices.size()>0);
		System.out.println("tests passed");
	}

	void insertPrices() {

		insertPrice("{\n" +
				"    \"brandId\": \"1\",\n" +
				"    \"startDate\": \"2020-06-14-00.00.00\",\n" +
				"    \"endDate\": \"2020-12-31-23.59.59\",\n" +
				"    \"priceList\": \"1\",\n" +
				"    \"productId\": \"35455\",\n" +
				"    \"priority\": \"0\",\n" +
				"    \"price\": \"35.50\",\n" +
				"    \"curr\": \"EUR\"\n" +
				"}");

		insertPrice("{\n" +
				"    \"brandId\": \"1\",\n" +
				"    \"startDate\": \"2020-06-14-15.00.00\",\n" +
				"    \"endDate\": \"2020-06-14-18.30.00\",\n" +
				"    \"priceList\": \"2\",\n" +
				"    \"productId\": \"35455\",\n" +
				"    \"priority\": \"1\",\n" +
				"    \"price\": \"25.45\",\n" +
				"    \"curr\": \"EUR\"\n" +
				"}");

		insertPrice("{\n" +
				"    \"brandId\": \"1\",\n" +
				"    \"startDate\": \"2020-06-15-00.00.00\",\n" +
				"    \"endDate\": \"2020-06-15-11.00.00\",\n" +
				"    \"priceList\": \"3\",\n" +
				"    \"productId\": \"35455\",\n" +
				"    \"priority\": \"1\",\n" +
				"    \"price\": \"30.50\",\n" +
				"    \"curr\": \"EUR\"\n" +
				"}");
		insertPrice("{\n" +
				"    \"brandId\": \"1\",\n" +
				"    \"startDate\": \"2020-06-15-16.00.00\",\n" +
				"    \"endDate\": \"2020-12-31-23.59.59\",\n" +
				"    \"priceList\": \"4\",\n" +
				"    \"productId\": \"35455\",\n" +
				"    \"priority\": \"1\",\n" +
				"    \"price\": \"38.95\",\n" +
				"    \"curr\": \"EUR\"\n" +
				"}");
	}
	void insertPrice(String jsonPayload) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/price", HttpMethod.POST, request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
