package com.db.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.db.store.entity.Trade;
import com.db.store.exception.TradeStoreException;
import com.db.store.repo.TradeStoreRepository;
import com.db.store.utils.TradeDTO;
import com.db.store.utils.Validator;

@Service
public class TradeStoreServiceImpl implements TradeStoreService {

	@Autowired
	TradeStoreRepository repo;

	@Override
	public String storeTrade(TradeDTO trade) throws TradeStoreException {
		// method call to validate the maturity date
		Validator.validateMaturityDate(trade.getMaturityDate());
		Optional<Trade> tradeEntity = repo.findById(trade.getTradeId());
		if (tradeEntity.isPresent()) {
			// method call to validate version
			Validator.validateVersion(trade.getVersion(), tradeEntity.get().getVersion());
			// persisting the updates in to the store
			repo.saveAndFlush(TradeDTO.convertToTrade(trade));
			return "Trade with Id: " + trade.getTradeId() + " has been updated";
		} else {
			// persisting the new trade details into the store
			repo.saveAndFlush(TradeDTO.convertToTrade(trade));
			return "Trade with Id: " + trade.getTradeId() + " has been added to the store";
		}

	}

	@Override
	public TradeDTO getTradeById(String tradeId) throws TradeStoreException {
		if (repo.findById(tradeId).isPresent())
			return Trade.convertToTradeDTO(repo.findById(tradeId).get());
		else
			throw new TradeStoreException("Trade with Id: " + tradeId + " not found !");
	}

	@Override
	public List<TradeDTO> getTrades() {
		List<TradeDTO> tradeList = new ArrayList<>();
		repo.findAll().forEach(trade -> tradeList.add(Trade.convertToTradeDTO(trade)));
		return tradeList;
	}

	/*
	 * scheduling the method to check the maturity date of all trades if a trade has
	 * a maturity date before present date the expiration status will be changes to
	 * 'N'
	 */

	// Scheduled the method call to every 10 mins

	@Scheduled(initialDelay = 1000, fixedRate = 600000)
	public void updateExpiry() {
		repo.findAll().forEach(trade -> {
			if (trade.getMaturityDate().isBefore(LocalDate.now()) && trade.getExpired().equalsIgnoreCase("N")) {
				trade.setExpired("Y");
				repo.saveAndFlush(trade);
			}
		});
	}
}
