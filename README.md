# TransferWise Home Assignment


# Karma Co.

## Context
You work for the reward points team at Karma Co. The company's primary business is offering rewards based off of card transactions done with cards issued by your partner Banks. 

Following are the names of banks you have partnered up with are 
* Archaic Big Bank
* Unknown Bank
* Shady Bank
* Challenger Bank
* Archaic Small Time Bank
* Cobol Systems Bank

Since most banks have an archaic infrastructure they send out a list of Transactions as a CSV to Karma Co. to track the rewards per customer in retrospect. Different banks send out CSVs in different formats to your partnership team. Your partnership team aggregates all the CSVs and generates 1 big CSV file in a generic format out of all the Transactions after stripping out all the sensitive information from the file. 

## Problem

Your job is to write a some code which takes a URL or the file itself as an input pointing to the CSV containing a billions of transactions and processing it to calculate the reward points per customer based off of the following rules.

### Transaction Amount Rules
For Transactions of type: ECOM

| Amount(EUR)  | Reward Points  |
|--------------|----------------|
|     0-2.49   |       0        |
| 2.50-4.99    |       1        |
| 5.00-9.99    |       2        |
| 10.00-99.99  |       3        |
|100.00-299.99 |7% of Transaction Amount|
|300.00-999.99 |10% of Transaction Amount|
|1000+         |15% of Transaction Amount|

For all other Transactions

| Amount(EUR)  | Reward Points  |
|--------------|----------------|
|     0-2.49   |       1        |
| 2.50-4.99    |       2        |
| 5.00-9.99    |       3        |
| 10.00-99.99  |       4        |
|100.00-299.99 |9% of Transaction Amount|
|300.00-999.99 |11% of Transaction Amount|
|1000+         |13% of Transaction Amount|

Transactions of negative amount lead to deduction of points as per the above rules.

### Merchant Rules
Transactions at merchants of Type `Airlines` get 2x the points.
Negative amount transactions for merchants of type `Airlines` lead to 2x the deduction of points.

### Reward Point Rules
* Customer cannot have negative reward Points. The minimum number of reward points possible is 0.
* There is no maximum limit to the number of reward points a customer can have
* The reward points can only be of type unsigned integers. Floor the points to the nearest integer if the entitled reward points is not a whole number.  

### Transaction CSV Sample Input
`transactions.csv`
```
Transaction ID,Customer ID,Customer Name,Bank Name,Transaction Amount (EUR),Merchant Name,Merchant Type,Transaction Country,Transaction Type
79bb2593-0ccd-4ea6-a409-5b2b67fda997,f411d086-85fd-4904-8c33-e32a008abf0a,Jane Doe,Archaic Big Bank,12.2,McDonalds,Fast Food,Germany,CHIP AND PIN
9bafbebd-d97f-4714-a4a6-021bcc6f61b6,a234509b-88a9-4451-9d92-21d8475e8def,John Smith,Shady Bank,2.5,Starbucks,Fast Food,Latvia,CONTACTLESS
a11de030-d245-40be-a2db-33ffc50c388d,f411d086-85fd-4904-8c33-e32a008abf0a,Jane Doe,Archaic Big Bank,730,Amazon Shopping,Book Stores,Estonia,ECOM
90e84d6b-40fb-4185-962e-e12e341d9c38,a234509b-88a9-4451-9d92-21d8475e8def,John Smith,Shady Bank,2.5,Starbucks,Fast Food,Germany,CONTACTLESS
fdf65a10-f3a5-479a-aef2-6abef133a705,f411d086-85fd-4904-8c33-e32a008abf0a,Jane Doe,Archaic Big Bank,11.99,Netflix,Online Services,France,ECOM
347b7e04-3eeb-4f88-b48d-4d34a551520c,d3224620-1298-49cc-9c7c-7a29dd2d3f3d,Uvuvwevwevwe ønyetenyevwe,Challenger Bank,289,Waitrose,Supermarket,Spain,CHIP AND PIN
50b0a99f-6b5d-43fe-9a00-3c454ec107ac,f411d086-85fd-4904-8c33-e32a008abf0a,Jane Doe,Archaic Big Bank,7,Tesco,Supermarket,Portugal,CONTACTLESS
5289fc0b-0f6b-4cb2-90b7-8206820f96ae,d3224620-1298-49cc-9c7c-7a29dd2d3f3d,Uvuvwevwevwe ønyetenyevwe,Challenger Bank,300,BRITISHAIRWAYS,Airlines,Spain,ECOM
cfcf3080-d1a4-4747-9ccd-334f343dc759,f411d086-85fd-4904-8c33-e32a008abf0a,Jane Doe,Archaic Big Bank,-7,Tesco,Supermarket,Portugal,CHIP AND PIN
d3ccbb2d-b6f5-4ec3-92fe-fe3b23d8f1bf,a234509b-88a9-4451-9d92-21d8475e8def,John Smith,Shady Bank,10.5,Pizza Hut,Fast Food,Belgium,CONTACTLESS
```


### Output Expected

| Customer ID                        | Customer ID                      | Reward Points  |
|------------------------------------|----------------------------------|----------------|
|f411d086-85fd-4904-8c33-e32a008abf0a|Jane Doe                          |       80       |
|a234509b-88a9-4451-9d92-21d8475e8def|John Smith                        |       8        |
|d3224620-1298-49cc-9c7c-7a29dd2d3f3d|Uvuvwevwevwe ønyetenyevwe         |       86       |

### More examples

Checkout the file

`moreExamples.csv`

### Solution Constraints 
**Do not make your submission public nor copy the entire solution from some other source, there is a 100% chance we will find out about it.**

Overall you have almost all the freedom with your tech stack, how you decide to ingest the CSV file, how you decide to present the output, etc, we want you to be completely in your comfort zone while designing and writing a solution for the above problem.  

* The solution can be anything, it can be a command line app, integration test based, a restful service, an RPC based service, a function as a service etc, it is completely upto you how you want to structure it.
* You can use any language, framework, database, tooling, infrastructure etc to build your solution, with the exception of [esoteric](https://esolangs.org/wiki/Language_list) languages like BrainFuck etc.
* You have absolute freedom to host the solution anywhere if you would like to.
* You can use any source control mechanism you want, if you want to use source control
* Your submission should be production ready
* It should work with a file having 1 Billion plus rows
* The runtime system configuration will be a quad core CPU and 4Gb of memory
* Feel free to make assumptions about the product, and using your instinct on what aspects you think might require extensibility in the future.

### What we look for
We look at the following aspects while evaluating a submission. As with all things related to code a lot of things are subjective,
so if the codebase you submitted clears a very basic decent ad-hoc looking criteria, we will pair with you on to understand the codebase, understanding your thought process behind certain choices, and discussing various cross functional requirements, and maybe even pairing on implementing a small feature together.
  
* Code Quality
    * Correct
    * Readable
    * Maintainable
    * Extensible
    * Well Tested
    * Scalable
    * Functional or Object Oriented Modeling
    * Use of Data-structures
* The reasoning behind the choice tooling, language and frameworks
* Ease of building, running, testing the code
* Tests presence or absence there of.
* Overall architecture and it's scalability
* The reasoning behind the assumptions you made
* Build tooling
* Infrastructure setup if any
* **Performance** 



