## Changelog

* #### Replaced HashedMap with LinkedHashMap so that item order could be preserved
* #### Changed for loop to be a forEach loop since we don't care about item index
* #### Overloaded ShoppingCart constructor for an additional parameter
* #### Output is formatted depending on the additional parameter mentioned above, setting this to true will output the items price first
* #### Total is calculated and is printed at the end of the receipt
* #### Added additional unit tests to test receipt printing with/without priceFirst