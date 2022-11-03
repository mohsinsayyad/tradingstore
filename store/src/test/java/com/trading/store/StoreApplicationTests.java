package com.trading.store;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.trading.store.dao.ITradingStoreDao;
import com.trading.store.model.TradingStore;

@SpringBootTest
class StoreApplicationTests {

	@Autowired
	ITradingStoreDao tradingStoreDao;
	
	@Test
	public void validateTradingStoreVersion_Success() throws Exception {
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean isValidVersion = tradingStoreDao.validateTradingStoreVersion(store);
		Assert.state(isValidVersion, "Valid Version");
	}
	
	@Test
	public void validateTradingStoreVersion_Failure() throws Exception {
		TradingStore store = new TradingStore("T2",1,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean isValidVersion = tradingStoreDao.validateTradingStoreVersion(store);
		Assert.state(isValidVersion, "Not Valid Version");
	}

	@Test
	public void validateTradingStoreMaturityDate() {
		
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean isValidDate = tradingStoreDao.validateTradingStoreMaturityDate(store);
		Assert.state(isValidDate, "Valid Maturity Date");
	}
	
	@Test
	public void validateTradingStoreMaturityDate_Invalid() {
		
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2021, 5, 20), LocalDate.now(), "N");
		boolean isValidDate = tradingStoreDao.validateTradingStoreMaturityDate(store);
		Assert.state(isValidDate, "Valid Maturity Date");
	}
	
	@Test
	public void addTradingStore() throws Exception {
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean tradeAdded = tradingStoreDao.addTradingStore(store);
		Assert.state(tradeAdded, "Trade added successfully");
	}
	
	@Test
	public void addTradingStore_Failure() throws Exception {
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean tradeAdded = tradingStoreDao.addTradingStore(store);
		Assert.state(tradeAdded, "Trade not added");
	}
	
	@Test
	public void updateTradingStore() throws Exception {
		TradingStore store = new TradingStore("T1",2,"CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");
		boolean tradeUpdated = tradingStoreDao.updateTradingStore(store);
		Assert.state(tradeUpdated, "Trade updated successfully");
	}
	

}
