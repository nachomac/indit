###### IntelliJ IDEA

Clone the project from https://github.com/nachomac/indit

Start the main class InditexApplicationTests

it is listening on http://localhost:8080/price/

You can create new Price records doing a POST to http://localhost:8080/price/
for example with this Json:

{  
"brandId": "1",  
"startDate": "2020-06-14-00.00.00",  
"endDate": "2020-12-31-23.59.59",  
"priceList": "1",
"productId":"35455",
"priority":"0",
"price":"35.50",
"curr":"EUR"
}

###### Run tests

execute test pricesShouldBeInDB in InditexApplicationTests

#### Without IntelliJ

**_Important: Required to refresh the app before spring-boot_**

- Open terminal

- Change to `indit` directory

- Run `mvn clean install`

#### `mvn spring-boot:run`

This will start the app in development mode.

- Open terminal

- Change to `indit` directory

- Run `mvn spring-boot:run`

Open [http://localhost:8080](http://localhost:8080/prices) to view it in the browser.