package com.db.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.store.entity.Trade;

public interface TradeStoreRepository extends JpaRepository<Trade, String> {

}
