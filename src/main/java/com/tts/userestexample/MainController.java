package com.tts.userestexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {
	
	@Autowired
	ApiRequestHandler requestHandler;
	
	@GetMapping(path="/")
	String getForm(Model model) {
		model.addAttribute("formData", new FormData(""));
		return "form";
	}
	
	@PostMapping(path="/")
	String processForm(FormData formData, Model model) {	
		//We are going to grab the search field from
		//formData and submit an API request to 
		//Scryfall.
		
		String search=formData.getSearch();
		GetCardResponse body = requestHandler.searchForCard(search);
		
		if ((body != null) && (body.total_cards >= 1)) {				
			model.addAttribute("url", body.data.get(0).image_uris.large);			
		} 				
		return "result";		
	}

}
