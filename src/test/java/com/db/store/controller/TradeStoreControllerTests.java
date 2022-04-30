package com.db.store.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.store.exception.TradeStoreException;
import com.db.store.service.TradeStoreService;
import com.db.store.utils.TradeDTO;

@SpringBootTest
public class TradeStoreControllerTests {

	@Mock
	TradeStoreService service;
	@InjectMocks
	TradeStoreController controller;

	@Test
	public void storeTradeTest() throws TradeStoreException {
		TradeDTO tradeDTO = new TradeDTO();
		tradeDTO.setTradeId("T1");
		tradeDTO.setVersion(2);
		tradeDTO.setCounterPartyId("CP-2");
		tradeDTO.setBookId("B1");
		tradeDTO.setMaturityDate(LocalDate.of(2021, 05, 8));
		tradeDTO.setCreatedDate(LocalDate.now());
		tradeDTO.setExpired("N");
		Mockito.when(service.storeTrade(tradeDTO))
				.thenReturn("Trade with Id: " + tradeDTO.getTradeId() + " has been added to the store");
		Assertions.assertEquals("Trade with Id: T1 has been added to the store",
				controller.storeTrade(tradeDTO).getBody());
	}

	@Test
	public void getTradeByIdTest() throws TradeStoreException {
		TradeDTO tradeDTO = new TradeDTO();
		tradeDTO.setTradeId("T1");
		tradeDTO.setVersion(2);
		tradeDTO.setCounterPartyId("CP-2");
		tradeDTO.setBookId("B1");
		tradeDTO.setMaturityDate(LocalDate.of(2021, 05, 8));
		tradeDTO.setCreatedDate(LocalDate.now());
		tradeDTO.setExpired("N");
		Mockito.when(service.getTradeById("T1")).thenReturn(tradeDTO);
		Assertions.assertNotNull(controller.getTradeById("T1").getBody());
	}

	@Test
	public void getTrades() {
		// output for mockito method of repo findAll
		List<TradeDTO> tradeList = new ArrayList<>();

		TradeDTO trade = new TradeDTO();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setCounterPartyId("CP-1");
		trade.setBookId("B1");
		trade.setMaturityDate(LocalDate.of(2022, 05, 8));
		trade.setCreatedDate(LocalDate.of(2022, 04, 30));
		trade.setExpired("N");

		TradeDTO trade2 = new TradeDTO();
		trade2.setTradeId("T1");
		trade2.setVersion(2);
		trade2.setCounterPartyId("CP-1");
		trade2.setBookId("B1");
		trade2.setMaturityDate(LocalDate.of(2021, 05, 8));
		trade2.setCreatedDate(LocalDate.of(2022, 04, 30));
		trade2.setExpired("N");

		tradeList.add(trade);
		tradeList.add(trade2);
		Mockito.when(service.getTrades()).thenReturn(tradeList);
		Assertions.assertNotNull(controller.getTrades().getBody());
	}
}
