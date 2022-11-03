package com.trading.store.dao;

import com.trading.store.model.TradingStore;

public interface ITradingStoreDao {

	public boolean addTradingStore(TradingStore store) throws Exception;
	public boolean updateTradingStore(TradingStore store) throws Exception;
	boolean validateTradingStoreMaturityDate(TradingStore store);
	boolean validateTradingStoreVersion(TradingStore store) throws Exception;
}
