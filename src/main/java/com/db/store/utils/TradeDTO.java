package com.db.store.utils;

import java.time.LocalDate;

import com.db.store.entity.Trade;

import lombok.Getter;
import lombok.Setter;

//Using Lombok to generate getter and setter
@Setter
@Getter
public class TradeDTO {
	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private String Expired;

	public static Trade convertToTrade(TradeDTO tradeDTO) {
		Trade trade = new Trade();
		trade.setTradeId(tradeDTO.getTradeId());
		trade.setVersion(tradeDTO.getVersion());
		trade.setCounterPartyId(tradeDTO.getCounterPartyId());
		trade.setBookId(tradeDTO.getBookId());
		trade.setMaturityDate(tradeDTO.getMaturityDate());
		trade.setCreatedDate(LocalDate.now());
		trade.setExpired(tradeDTO.getExpired());
		return trade;
	}
}
