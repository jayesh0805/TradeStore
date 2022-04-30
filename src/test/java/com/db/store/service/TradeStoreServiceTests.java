package com.db.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.store.entity.Trade;
import com.db.store.exception.TradeStoreException;
import com.db.store.repo.TradeStoreRepository;
import com.db.store.utils.TradeDTO;

@SpringBootTest
public class TradeStoreServiceTests {

	@Mock
	TradeStoreRepository repo;
	@InjectMocks
	TradeStoreServiceImpl service;
	
	//test for storeTradeMethod of tradeService
	@Test
	public void storeTradeTest() throws TradeStoreException {
	//setting up the input
	TradeDTO tradeDTO=new TradeDTO();
	tradeDTO.setTradeId("T1");
	tradeDTO.setVersion(2);
	tradeDTO.setCounterPartyId("CP-2");
	tradeDTO.setBookId("B1");
	tradeDTO.setMaturityDate(LocalDate.of(2021,05,8));
	tradeDTO.setCreatedDate(LocalDate.now());
	tradeDTO.setExpired("N");
	
	//output for mockito method of repo findById
	Trade trade=new Trade();
	trade.setTradeId("T1");
	trade.setVersion(2);
	trade.setCounterPartyId("CP-1");
	trade.setBookId("B1");
	trade.setMaturityDate(LocalDate.of(2022,05,8));
	trade.setCreatedDate(LocalDate.of(2021, 03,7));
	tradeDTO.setExpired("N");
	
	//scenario-> invalid maturity date
	Mockito.when(repo.findById("T1")).thenReturn(Optional.of(trade));
    Assertions.assertThrows(TradeStoreException.class, ()->service.storeTrade(tradeDTO));
    
    //scenario->invalid version
    tradeDTO.setVersion(1);
    tradeDTO.setMaturityDate(LocalDate.of(2022,05,8));
    Assertions.assertThrows(TradeStoreException.class, ()->service.storeTrade(tradeDTO));
    
    //scenario->valid trade (updation)
    tradeDTO.setVersion(2);
    Assertions.assertEquals("Trade with Id: " + tradeDTO.getTradeId() + " has been updated",service.storeTrade(tradeDTO));
    
    //scenario-> valid trade (new trade)
    tradeDTO.setTradeId("T4");
    Assertions.assertEquals("Trade with Id: " + tradeDTO.getTradeId() + " has been added to the store",service.storeTrade(tradeDTO));
	}
	
	@Test
	public void getTradeByIdTest() throws TradeStoreException
	{
		//output for mockito method of repo findById
		Trade trade=new Trade();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setCounterPartyId("CP-1");
		trade.setBookId("B1");
		trade.setMaturityDate(LocalDate.of(2022,05,8));
		trade.setCreatedDate(LocalDate.of(2021, 03,7));
		trade.setExpired("N");
		
		//scenario->trade present in store
		Mockito.when(repo.findById("T1")).thenReturn(Optional.of(trade));
		Assertions.assertNotNull(service.getTradeById("T1"));
		
		//scenario->trade which is not present in store
		Mockito.when(repo.findById("T1")).thenReturn(Optional.ofNullable(null));
		Assertions.assertThrows(TradeStoreException.class, ()->service.getTradeById("T1"));
	}
	
	@Test
	public void getTrades_updateExpiryTest()
	{   
		//output for mockito method of repo findAll
		List<Trade> tradeList = new ArrayList<>();
		
		Trade trade=new Trade();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setCounterPartyId("CP-1");
		trade.setBookId("B1");
		trade.setMaturityDate(LocalDate.of(2022,05,8));
		trade.setCreatedDate(LocalDate.of(2022, 04,30));
		trade.setExpired("N");
		
		Trade trade2=new Trade();
		trade2.setTradeId("T1");
		trade2.setVersion(2);
		trade2.setCounterPartyId("CP-1");
		trade2.setBookId("B1");
		trade2.setMaturityDate(LocalDate.of(2021,05,8));
		trade2.setCreatedDate(LocalDate.of(2022, 04,30));
		trade2.setExpired("N");
		
		tradeList.add(trade);
		tradeList.add(trade2);
		
	    //will return above list when findAll method of repo is used
		Mockito.when(repo.findAll()).thenReturn(tradeList);
		
		//scenario->gets all trades 
		Assertions.assertNotNull(service.getTrades());
		
		//scenario->changes trade 2 expired to "Y"
		service.updateExpiry();
		
		//scenario->maturity date is before but expiry is already "Y"
		trade2.setExpired("Y");
		service.updateExpiry();
		
		//scenario->maturity date is after current date
		trade2.setMaturityDate(LocalDate.of(2022,05,8));
		service.updateExpiry();
		
	}
}
