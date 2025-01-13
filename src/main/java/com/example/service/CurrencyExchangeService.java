
package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CurrencyExchangeEntity;
import com.example.model.CurrencyExchangeModel;
import com.example.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository repository;

	// Add a new currency exchange record
	public CurrencyExchangeModel addCurrencyExchange(CurrencyExchangeModel model) {
		CurrencyExchangeEntity entity = mapToEntity(model);
		CurrencyExchangeEntity savedEntity = repository.save(entity);
		return mapToModel(savedEntity);
	}

	// Get all currency exchange records
	public List<CurrencyExchangeModel> getAllCurrencyExchanges() {
		List<CurrencyExchangeEntity> entities = repository.findAll();
		return entities.stream().map(this::mapToModel).collect(Collectors.toList());
	}

	// Get a currency exchange by ID
	public Optional<CurrencyExchangeModel> getCurrencyExchangeById(Long id) {
		Optional<CurrencyExchangeEntity> entity = repository.findById(id);
		return entity.map(this::mapToModel);
	}

	// Map CurrencyExchangeModel to CurrencyExchangeEntity
	private CurrencyExchangeEntity mapToEntity(CurrencyExchangeModel model) {
		return new CurrencyExchangeEntity(model.getId(), model.getFrom(), model.getTo(), model.getConversionMultiple(),
				model.getEnvironment());
	}

	// Map CurrencyExchangeEntity to CurrencyExchangeModel
	private CurrencyExchangeModel mapToModel(CurrencyExchangeEntity entity) {
		return new CurrencyExchangeModel(entity.getId(), entity.getFrom(), entity.getTo(),
				entity.getConversionMultiple());
	}
}
