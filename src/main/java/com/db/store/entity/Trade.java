package com.db.store.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.db.store.utils.TradeDTO;

import lombok.Getter;
import lombok.Setter;

//Using Lombok to generate getter and setter
@Entity
@Setter
@Getter
public class Trade {
	@Id
	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private String Expired;

	public static TradeDTO convertToTradeDTO(Trade trade) {
		TradeDTO tradeDTO = new TradeDTO();
		tradeDTO.setTradeId(trade.getTradeId());
		tradeDTO.setVersion(trade.getVersion());
		tradeDTO.setCounterPartyId(trade.getCounterPartyId());
		tradeDTO.setBookId(trade.getBookId());
		tradeDTO.setMaturityDate(trade.getMaturityDate());
		tradeDTO.setCreatedDate(trade.getCreatedDate());
		tradeDTO.setExpired(trade.getExpired());
		return tradeDTO;
	}
}
