package com.trading.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trading.store.dao.ITradingStoreDao;
import com.trading.store.model.TradingStore;

@RestController
public class TradingStoreController {
	
	@Autowired
	ITradingStoreDao tradingStoreDao;

	// Added for testing
	@PostMapping("/addtrade")
	public ResponseEntity<String> addTradingStore(@RequestBody TradingStore store) throws Exception {
		
		tradingStoreDao.addTradingStore(store);
		return new ResponseEntity<String>("Trade added successfully!.", HttpStatus.OK);
		
	}
	
}
