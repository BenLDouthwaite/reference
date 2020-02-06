# SQL

## CONCAT and GROUP_CONCAT

We can use CONCAT command to join several string together, often to allow us to generate other scripts. 
e.g

## COUNT IF 

Can use count if to get percentages of a total 

```
SELECT profession_id,
       COUNT(IF(country LIKE "%UK%", 1, NULL)) AS countryLikeUK,
       COUNT(country) AS totalCupplierCountries,
       COUNT(IF(country LIKE "%UK%", 1, NULL)) * 100.0 / COUNT(country) AS percentage
FROM users
GROUP BY profession_id;
```

## Make database

