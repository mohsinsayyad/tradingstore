package com.trading.store.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.trading.store.model.TradingStore;

@Service
public class TradingStoreDao implements ITradingStoreDao{

	private static List<TradingStore> tradingStores = new ArrayList<>();
	
	static {
		tradingStores = Stream.of(new TradingStore("T1",1,"CP-1", "B1", LocalDate.of(2020, 5, 20), LocalDate.now(), "N"),
				new TradingStore("T2",2,"CP-2", "B1", LocalDate.of(2021, 5, 20), LocalDate.now(), "N"),
				new TradingStore("T3",1,"CP-1", "B1", LocalDate.of(2021, 5, 20), LocalDate.of(2015, 3, 14), "N"),
				new TradingStore("T4",3,"CP-3", "B2", LocalDate.of(2014, 5, 20), LocalDate.now(), "Y")).collect(Collectors.toList());
	}

	@Override
	public boolean addTradingStore(TradingStore store) {
		if(validateTradingStoreVersion(store) && validateTradingStoreMaturityDate(store)) {
			return tradingStores.add(store);
		} 
		return false;
	}

	@Override
	public boolean validateTradingStoreVersion(TradingStore store) {
		TradingStore tradingStore = tradingStores.stream()
				.filter(st -> st.getTradeId().equals(store.getTradeId()))
				.collect(Collectors.toList())
				.stream()
				.max(Comparator.comparing(TradingStore::getVersion))
				.get();
		if(store.getVersion() < tradingStore.getVersion()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean validateTradingStoreMaturityDate(TradingStore store) {
		/*
		 * TradingStore tradingStore = tradingStores.stream() .filter(st ->
		 * st.getTradeId().equals(store.getTradeId())) .collect(Collectors.toList())
		 * .stream() .max(Comparator.comparing(TradingStore::getMaturityDate)) .get();
		 */
		if(store.getMaturityDate().isBefore(LocalDate.now())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTradingStore(TradingStore store) {
		//int index = IntStream.range(0, tradingStores.size()).filter(i -> tradingStores.get(i).equals(store)).findFirst().getAsInt();
		int index = tradingStores.indexOf(store);
		
		tradingStores.set(index, store);
		return true;
	}
	
	
}
