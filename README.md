# StockApp

This project is a code demo used to fetch data from https://www.alphavantage.co/documentation/. 
The data being fetched is exchange rates between Cryptocurrencies and Forex. 


I have found after testing the API that some network requests take a while to call or not call at all,
the demo has it set up to return a "Out of time" toast if it takes too long. 
Many of the times empty {} are returned as the json.

The task asked that were handled
1) Demonstrate data retrieval from https://www.alphavantage.co/. 
Example request https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=LTC&apikey=GJLF3CX2MZ6ATP09
2) Display some exchange rates in a list contained inside a fragment
3) Swipe down refresh in details fragment
4) Exchange rate retrieval via menu 
5) Search exchange rate in current list via menu/toolbar
6) Details view done in separate fragment

Some important things that I know I didn't handle:
1) Checking for internet connection
2) I did not use persistent storage to store the data. I would've liked to use SQLite but it would require more hours and for a simplistic app such as this,
I decided to use a static array list to hold the data which means that if the app was destroyed, the data would be lost. More importantly,
because I did not use SQLite, it created several messy issues such as public static variables that shouldn't be.
3) There was no API request to retrieve multiple exchange rates, so I had to manually make calls to add several items to the initial list.
4) I wanted to demonstrate the modularity of MVP and different uses of styles but didn't.
