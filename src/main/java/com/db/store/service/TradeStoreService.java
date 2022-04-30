package com.db.store.service;

import java.util.List;

import com.db.store.exception.TradeStoreException;
import com.db.store.utils.TradeDTO;

public interface TradeStoreService {
	public String storeTrade(TradeDTO trade) throws TradeStoreException;

	public TradeDTO getTradeById(String tradeId) throws TradeStoreException;

	public List<TradeDTO> getTrades();
}
