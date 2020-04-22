### Contraintes techniques

* Java 8 
* Springboot jaa
* intellij
* Mysql
* Hibernate
* JUnit 
* AssertJ
* Mock
* themeleaf
* maven
* Postman
* Git

### Database Structure

Clients (id, userid, realname, sex, age, passport)

Accounts (accounted, balance, bankid, card number,password,userid)

Bank(id,name,address)

Transaction_log(id, senderaccountid, receiveaccountid, senderbankid,receivebankid, datetime, money, transfermoney, transfertype)

### General Architecture 

Four dev packages : Bean,DAO,Service,Controller

Threes test packages: ServicesTest, ControllerTest, MockTest

## Le Kata how to realise your conditions

### Minimum Valuable Product

#### User Story 1

> En tant que banque, j'accepte le dépôt d'argent d'un client vers son compte s'il est supérieur à 0,01€

Realise: 

- define balance by bigdecimal(18,2)
- when client want to deposit money, i will compare it with 0.01. if right, i will update account return a json

#### User Story 2

> En tant que banque, j'accepte le retrait d'argent d'un client depuis son compte si le solde ne devient pas négatif

- Compare withdraw monet to your solde, if withdraw money doesn't overpass the solde, update account and return son

#### User Story 3

> En tant que banque, j'offre la possibilité à mon client de consulter le solde de son compte

- Find solde by account id

#### User Story 4

> En tant que banque, j'offre la possibilité à mon client de consulter l'historique des transactions sur son compte

- define a static map to save  deposit : 1, withdraw :2 and transfer : 3

  Transfer

- when account transfer money to another account, i should verify the account is right or not, and then make sure the transfer money doesn't overpass the solde. Then update theses two accounts and record this transaction to table

  Deposit 

  Withdraw 

### Features bonus

Les fonctionnalités suivantes sont optionnelles et non exhaustives.  
Elles n'ont pas de priorité entre elles, vous pouvez implémenter celles qui vous intéressent ou même en proposer d'autres.

#### API REST

- i choose REST and test all of my requests in Postman and by joints too

#### Clients & Comptes

- clients : accounts = 1 : N

* each clients has many accounts, but one account is hold only by one clients. 

* Bank : accounts = 1 : N

  

#### Persistence

* Proposer une solution de persistence des données

I use JPA and hibernate to persistent the data in mysql database

#### UI

* Proposer une interface graphique pour interagir avec les services réalisés dans le MVP

i made a simple interface by themeleaf, this part i didn't avance so much, bur if you need, i can do it quickly



#### Build

* Utiliser Gradle au lieu de Maven

i choose Maven

* Proposer des tests End to End à destination de votre livrable

i did test by junit and mock to test services. Two principals way : integrate and continus .