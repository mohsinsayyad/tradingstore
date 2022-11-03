package com.trading.store.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.trading.store.model.TradingStore;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradingStoreDao implements ITradingStoreDao {

	private static final Logger logger = Logger.getLogger(TradingStoreDao.class.getName());

	private static List<TradingStore> tradingStores = new ArrayList<>();

	static {
		tradingStores = Stream
				.of(new TradingStore("T1", 1, "CP-1", "B1", LocalDate.of(2020, 5, 20), LocalDate.now(), "N"),
						new TradingStore("T2", 2, "CP-2", "B1", LocalDate.of(2021, 5, 20), LocalDate.now(), "N"),
						new TradingStore("T3", 1, "CP-1", "B1", LocalDate.of(2021, 5, 20), LocalDate.of(2015, 3, 14),
								"N"),
						new TradingStore("T4", 3, "CP-3", "B2", LocalDate.of(2014, 5, 20), LocalDate.now(), "Y"))
				.collect(Collectors.toList());

		logger.info("Trading Store :: " + tradingStores);
	}

	/**
	 * This method adds trade only if version & maturity date is valid.
	 */
	@Override
	public boolean addTradingStore(TradingStore store) throws Exception {
		boolean validateTradingStoreVersion = validateTradingStoreVersion(store);
		boolean validateTradingStoreMaturityDate = validateTradingStoreMaturityDate(store);
		if (validateTradingStoreVersion && validateTradingStoreMaturityDate) {
			logger.info("Trading Store Added :: " + store);
			return tradingStores.add(store);
		}
		logger.info("Trading Store aready exists :: " + store);
		return false;
	}

	/**
	 * This method validates version. if lower version received then exception is
	 * throwned.
	 */
	@Override
	public boolean validateTradingStoreVersion(TradingStore store) throws Exception {
		Optional<TradingStore> tradingStore = tradingStores.stream()
				.filter(st -> st.getTradeId().equals(store.getTradeId())).collect(Collectors.toList()).stream()
				.max(Comparator.comparing(TradingStore::getVersion));
		if (tradingStore.isPresent() && store.getVersion() < tradingStore.get().getVersion()) {
			logger.info("Lower version is being received :: " + store.getVersion() + " Existing version :: "
					+ tradingStore.get().getVersion());
			throw new Exception();
		}
		return true;
	}

	/**
	 * This method validates maturity date. if not valid then throw exception.
	 */
	@Override
	public boolean validateTradingStoreMaturityDate(TradingStore store) {
		if (store.getMaturityDate().isBefore(LocalDate.now())) {
			logger.info("Maturity Date is less than todays date :: " + store.getMaturityDate());
			return false;
		}
		return true;
	}

	/**
	 * This method will update the Trade. True if updated else it returns false.
	 */
	@Override
	public boolean updateTradingStore(TradingStore store) throws Exception {
		Optional<TradingStore> tradingStore = tradingStores.stream()
				.filter(st -> st.getTradeId().equals(store.getTradeId())).collect(Collectors.toList()).stream()
				.max(Comparator.comparing(TradingStore::getVersion));
		
		if (tradingStore.isPresent()) {
			int index = tradingStores.indexOf(tradingStore.get());
			if (index >= 0 && validateTradingStoreVersion(store)) {
				tradingStores.set(index, store);
				logger.info("Update Trading Store with Trade Id :: " + store.getTradeId());
				logger.info("Updated Trading Store :: " + tradingStores);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to update Expiry Flag.
	 */
	@Override
	public boolean updateExpiryFlag() {
		List<TradingStore> tradingStore = tradingStores.stream()
				.filter(ts -> ts.getMaturityDate().isBefore(LocalDate.now())).map(ts -> {
					ts.setExpired("Y");
					return ts;
				}).collect(Collectors.toList());

		logger.info("Maturity date updated for Trades :: " + tradingStore);

		return !tradingStore.isEmpty();
	}
}
