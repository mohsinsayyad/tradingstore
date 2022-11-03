package com.trading.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String addTradingStore(@RequestBody TradingStore store) throws Exception {
		
		tradingStoreDao.addTradingStore(store);
		return "Success";
		
	}
	
}
