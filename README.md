# TradeStore
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.

**Solution**

**Project Type**: Spring Boot Project

**Java Version**: Java 8

**Junit Version**: Jupiter (Junit 5)

**Database**: H2

**H2-Console-Path**: /h2console/

**Queries-File-Path**: src/main/resources/queries.txt (create table queries and 2 input queries)

**Coverage-Report-Path (using Jacoco)**: target/site/jacoco/index.html

**Paths**:

   **Path**: /trade/{tradeId}
	 
   **MethodType**: GET
	 
   **Desc**: Will retrieve a Trade detail based on Trade_Id
	 
   **Example**: /trade/T1
   
   
   
   
   
   **Path**: /trades
	 
   **MethodType**: GET
	 
   **Desc**: Will retrieve all Trades details based present int the store
	 
   **Example**: /trades
   
   
   
   
   **Path**: /trade
	 
   **MethodType**: POST
	 
   **Desc**: Will store a Trade detail based on above mentioned validations
	 
   **RequestBody**:
   {
    "tradeId": "val",
    "version": val,
    "counterPartyId": "val",
    "bookId": "val",
    "maturityDate": "val",
    "expired": "val"
   } 
	 
  **Example**:
  {
    "tradeId": "T5",
    "version": 1,
    "counterPartyId": "CP-4",
    "bookId": "B4",
    "maturityDate": "2022-05-21",
    "expired": "N"
  }
