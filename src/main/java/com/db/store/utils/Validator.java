package com.db.store.utils;

import java.time.LocalDate;

import com.db.store.exception.TradeStoreException;

public class Validator {
	public static boolean validateVersion(Integer requestVersion, Integer retrievedVersion) throws TradeStoreException {
		/* checks if the version is request is '>=' than the version present in the
		 store */
		if (requestVersion >= retrievedVersion)
			return true;
		else
			throw new TradeStoreException("Invalid version");
	}

	public static boolean validateMaturityDate(LocalDate maturityDate) throws TradeStoreException {
		if (maturityDate.isAfter(LocalDate.now()) || maturityDate.equals(LocalDate.now()))
			return true;
		else
			throw new TradeStoreException("Invalid Maturity Date");
	}
}
