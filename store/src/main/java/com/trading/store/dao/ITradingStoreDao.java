package com.trading.store.dao;

import com.trading.store.model.TradingStore;

public interface ITradingStoreDao {

	public boolean addTradingStore(TradingStore store);
	public boolean updateTradingStore(TradingStore store);
	boolean validateTradingStoreMaturityDate(TradingStore store);
	boolean validateTradingStoreVersion(TradingStore store);
}
