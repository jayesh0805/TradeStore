package com.db.store.utils;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.db.store.entity.Trade;

import lombok.Getter;
import lombok.Setter;

//Using Lombok to generate getter and setter
@Setter
@Getter
public class TradeDTO {
	@NotNull(message = "tradeId must not be null")
	private String tradeId;
	@NotNull
	private Integer version;
	@NotNull
	private String counterPartyId;
	@NotNull
	private String bookId;
	@NotNull
	private LocalDate maturityDate;
	private LocalDate createdDate;
	@NotNull
	private String Expired;

	public static Trade convertToTrade(TradeDTO tradeDTO) {
		Trade trade = new Trade();
		trade.setTradeId(tradeDTO.getTradeId());
		trade.setVersion(tradeDTO.getVersion());
		trade.setCounterPartyId(tradeDTO.getCounterPartyId());
		trade.setBookId(tradeDTO.getBookId());
		trade.setMaturityDate(tradeDTO.getMaturityDate());
		trade.setCreatedDate(LocalDate.now());
		trade.setExpired("N");
		return trade;
	}
}
