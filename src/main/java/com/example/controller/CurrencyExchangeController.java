package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.CurrencyExchangeEntity;
import com.example.model.CurrencyExchangeModel;
import com.example.repository.CurrencyExchangeRepository;
import com.example.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private CurrencyExchangeService service;
	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepository repository;

	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchangeEntity retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		System.out.println("asdfasdf");
		System.out.println("asdfasdasdf");
		CurrencyExchangeEntity currencyExchange = repository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			System.out.println("asdfasdf");
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);

		}

		String port = environment.getProperty("local.server.port");

		currencyExchange.setEnvironment(port);

		return currencyExchange;

	}

	// Endpoint to add a currency exchange record
	@PostMapping("/add")
	public ResponseEntity<CurrencyExchangeModel> addCurrencyExchange(@RequestBody CurrencyExchangeModel model) {
		CurrencyExchangeModel savedModel = service.addCurrencyExchange(model);
		return ResponseEntity.ok(savedModel);
	}

	// Endpoint to get all currency exchange records
	@GetMapping("/all")
	public ResponseEntity<List<CurrencyExchangeModel>> getAllCurrencyExchanges() {
		List<CurrencyExchangeModel> exchanges = service.getAllCurrencyExchanges();
		return ResponseEntity.ok(exchanges);
	}

	// Endpoint to get a specific currency exchange record by ID
	@GetMapping("/{id}")
	public ResponseEntity<CurrencyExchangeModel> getCurrencyExchangeById(@PathVariable Long id) {
		return service.getCurrencyExchangeById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}