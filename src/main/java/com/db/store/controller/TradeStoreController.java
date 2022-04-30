package com.db.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.store.exception.TradeStoreException;
import com.db.store.service.TradeStoreService;
import com.db.store.utils.TradeDTO;

@RestController
public class TradeStoreController {

	@Autowired
	TradeStoreService service;

	// post method to validate and store trade or update existing trade
	@PostMapping("/trade")
	public ResponseEntity<String> storeTrade(@RequestBody TradeDTO trade) throws TradeStoreException {
		return new ResponseEntity<String>(service.storeTrade(trade), HttpStatus.OK);
	}

	// get method to retrieve trade details based on trade id
	@GetMapping("/trade/{tradeId}")
	public ResponseEntity<TradeDTO> getTradeById(@PathVariable String tradeId) throws TradeStoreException {
		return new ResponseEntity<>(service.getTradeById(tradeId), HttpStatus.OK);
	}

	// get method to retrieve details of all the trades in the trade store
	@GetMapping("/trades")
	public ResponseEntity<List<TradeDTO>> getTrades() {
		return new ResponseEntity<>(service.getTrades(), HttpStatus.OK);
	}
}
