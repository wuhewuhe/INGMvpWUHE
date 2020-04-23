### Introduction of my MVP project
how could you test and see my MVP project, you can build and run my projet by maven

then go to this url http://localhost:8080/ING/, you will see all my api in this page

**may be the interface is so ugly, s'im sorry, i did this just easy for you to test**

### Contraintes techniques

* Java 8 
* Springboot jpa
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

Clients (id, clientid, realname, sex, age)

Accounts (accountid, balance, bankid, card number,password, clientid)

Bank(bankid,name,address)

Transaction_log(id, senderaccountid, receiveaccountid, senderbankid,receivebankid, datetime, money, transfermoney, transfertype)

**i dump my mysql schema to the diretocry of Dump20200423**

### General Architecture 

front : html, thymelaf 

backend : spring boot + jpa + hibernate 

database : mysql, innodb5

test : postman, mock, assetj (test unitaire and integrate)

### Step to test

- create bank, client and client 

Condition: 

1 bank name can not duplicate, bankid is primary key 

2 client has a client id which is unique 

3 account has relationship with bank and client by clientid and bankid, if they are not exist, they will return a bad request. **one client can hold many cards of differents bank, bank each account has a unqiue card number and corresponds to one bank

- deposit money 

condition : money shoud super than 0.01 and account id should exist

- withdraw money 

condition : money should not overpass solde and account id should exist 

- Transfer money

condition : sender account id and receiver account id must exist and transfer money should not overpass the solde of send account deposit 

- transaction 

there are three types of transaction : deposit, withdraw and transfer 

you can see the histories of transcation by sender id, receiver id and transfer type

