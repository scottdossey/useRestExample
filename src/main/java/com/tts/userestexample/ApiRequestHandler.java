package com.tts.userestexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiRequestHandler {

	public GetCardResponse searchForCard(String search) {
		//In order to do in API request in SpringBoot
		//We use the RestTemplate object.
				
		//First we create a RestTemplate
		RestTemplate request = new RestTemplate();
				
		String url = "https://api.scryfall.com/cards/search/?q=";
		url += search;
		
		ResponseEntity<GetCardResponse> response;
		try {
			response = request.getForEntity(url, GetCardResponse.class);		
			GetCardResponse body = response.getBody();	
					
			if(response.getStatusCode()==HttpStatus.OK) {
				return body;
			}
		} catch(HttpClientErrorException e) {			
		}
		return null;					
	}
}
