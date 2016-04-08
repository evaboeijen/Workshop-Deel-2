DROP DATABASE workshopdeel2;

CREATE DATABASE workshopdeel2;

USE workshopdeel2;

-- weer teruggezet 08/04/16 AU
CREATE TABLE `adrestype` (		
  `adrestype_id` bigint(20) NOT NULL,
 `adrestype` varchar(45) NOT NULL,
 PRIMARY KEY (`adrestype_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
-- weer teruggezet 08/04/16 AU
 CREATE TABLE `betaalwijze` (
   `betaalwijze_id` bigint(20) NOT NULL,
  `betaalwijze` varchar(45) NOT NULL,
  PRIMARY KEY (`betaalwijze_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `artikel` (
  `artikel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artikel_naam` varchar(20) NOT NULL,
  `artikel_prijs` double NOT NULL,
  `artikel_nummer` varchar(45) DEFAULT NULL,
  `omschrijving` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`artikel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `klant` (
  `klant_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(50) NOT NULL,
  `achternaam` varchar(51) NOT NULL,
  `tussenvoegsel` varchar(10) DEFAULT NULL,
  `email` varchar(320) NOT NULL,
  -- `bestellingset` bigint(20) DEFAULT NULL,
  -- `factuurset` bigint(20) DEFAULT NULL,
  -- `accountset` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`klant_id`),
  UNIQUE KEY `klant_constraint` (`voornaam`,`email`,`achternaam`),
  UNIQUE KEY `klant_id_UNIQUE` (`klant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;


CREATE TABLE `account` (
  `account_id` bigint(20) NOT NULL,
  `accountnaam` varchar(45) NOT NULL,
  `klant_id` bigint(20) DEFAULT NULL,
  `datecreated` date DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  UNIQUE KEY `accountnaam_UNIQUE` (`accountnaam`),
  KEY `klant_id` (`klant_id`),
  CONSTRAINT `klant_id` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `adres` (
  `adres_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `straatnaam` varchar(26) DEFAULT NULL,
  `postcode` varchar(6) DEFAULT NULL,
  `toevoeging` varchar(6) DEFAULT NULL,
  `huisnummer` varchar(6) DEFAULT NULL,
  `woonplaats` varchar(26) DEFAULT NULL,
  `adrestype_id` bigint(20) DEFAULT NULL, -- weer teruggezet 08/04/16 AU
  PRIMARY KEY (`adres_id`)
 --  KEY `adrestype_fk` (`adrestype_id`),
 -- CONSTRAINT `adrestype_fk` FOREIGN KEY (`adrestype_id`) REFERENCES `adrestype` (`adrestype_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `bestelling` (
  `bestelling_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bestelnummer` varchar(45) NOT NULL,
  `besteldatum` date DEFAULT NULL,
  -- `bestelartikel_id` bigint(20) DEFAULT NULL,
  -- `factuur_id` bigint(20) DEFAULT NULL,
  `klant_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bestelling_id`),
  UNIQUE KEY `bestelling_id` (`bestelling_id`),
  KEY `fk_klant_id` (`klant_id`),
  CONSTRAINT `fk_klant_id` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `bestelartikel` (
  `bestelartikel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artikel_id` bigint(20) NOT NULL,
  `aantal` int(11) NOT NULL,
  `bestelling_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bestelartikel_id`),
  UNIQUE KEY `bestelling_id_UNIQUE` (`bestelartikel_id`),
  KEY `artikel_id_fk` (`artikel_id`),
  KEY `bestelling_id_fk` (`bestelling_id`),
  CONSTRAINT `artikel_id_fk` FOREIGN KEY (`artikel_id`) REFERENCES `artikel` (`artikel_id`),
  CONSTRAINT `bestelling_id_fk` FOREIGN KEY (`bestelling_id`) REFERENCES `bestelling` (`bestelling_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `factuur` (
  `factuur_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factuurnummer` varchar(45) NOT NULL,
  `factuurdatum` date NOT NULL,
  -- `betaling_id` bigint(20) DEFAULT NULL,
  `bestelling_id` bigint(20) NOT NULL,
  PRIMARY KEY (`factuur_id`),
  UNIQUE KEY `factuur_id_UNIQUE` (`factuur_id`),
  KEY `bestelling_id_fk_` (`bestelling_id`),
  CONSTRAINT `bestelling_id_fk_` FOREIGN KEY (`bestelling_id`) REFERENCES `bestelling` (`bestelling_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `betaling` (
  `betaling_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `betaaldatum` date NOT NULL,
  -- `betaalwijze_id` bigint(20) NOT NULL,
  `klant_id` bigint(20) NOT NULL,
  `factuur_id` bigint(20) DEFAULT NULL,
  `betalingsgegevens` varchar(50) DEFAULT NULL,
  `betaalwijze` varchar(45) NOT NULL,
  PRIMARY KEY (`betaling_id`),
  UNIQUE KEY `betaling_id_UNIQUE` (`betaling_id`),
  KEY `factuur_id_fk` (`factuur_id`),
  KEY `klant_id_fk_` (`klant_id`),
 -- KEY `betaalwijze_if_fk` (`betaalwijze_id`),
 -- CONSTRAINT `betaalwijze_if_fk` FOREIGN KEY (`betaalwijze_id`) REFERENCES `betaalwijze` (`betaalwijze_id`),
  CONSTRAINT `factuur_id_fk` FOREIGN KEY (`factuur_id`) REFERENCES `factuur` (`factuur_id`),
  CONSTRAINT `klant_id_fk_` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `klant_adres` (
  `klant_id` bigint(20) NOT NULL,
  `adres_id` bigint(20) NOT NULL,
  `adrestype` varchar(45) NOT NULL,
  PRIMARY KEY (`klant_id`,`adres_id`),
  KEY `adres_id_fk` (`adres_id`),
  KEY `klant_ka_id_fk` (`klant_id`),
  CONSTRAINT `fk_adres_id` FOREIGN KEY (`adres_id`) REFERENCES `adres` (`adres_id`),
  CONSTRAINT `klant_id_fk` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- weer teruggezet 08/04/16 AU
 INSERT INTO `betaalwijze` (
 `betaalwijze_id`, `betaalwijze`) VALUES ('1', 'Contant'), ('2', 'Pinbetaling'), ('3', 'IDeal'),('4', 'Creditcard');

 INSERT INTO `adrestype` (
 `adrestype_id`, `adrestype`) VALUES ('1', 'Postadres'), ('2', 'Factuuradres'), ('3', 'Bezoekadres');


-- insert statements voor testdata toegevoegd 08/04/16 AU
 INSERT INTO `klant` (
`klant_id`, `voornaam`, `achternaam`, `tussenvoegsel`, `email`) VALUES ('1', 'testvoornaam', 'testachternaam', 'test', 'test@test.com'),
																		('2', 'test2voornaam', 'test2achternaam', 'test2', 'test2@test.com');

INSERT INTO `account` (
`account_id`, `accountnaam`, `klant_id`, `datecreated` ) VALUES ('1', 'testaccountnaam', '1', null),
																('2', 'test2accountnaam', '2', null);

INSERT INTO `adres` (
`adres_id`, `straatnaam`, `postcode`, `toevoeging`, `huisnummer`, `woonplaats`) VALUES ('1', 'teststraat', '90210', 'E', '69', 'Lutjebroek');

INSERT INTO `artikel` (
`artikel_id`, `artikel_naam`, `artikel_prijs`, `artikel_nummer`, `omschrijving`) VALUES ('1', 'testartikel', '12.99', '12345AB', 'mooi test artikel');

INSERT INTO `klant_adres` (
`klant_id`, `adres_id`, `adrestype`) VALUES ('1', '1','Postadres'),
											('2', '1','Factuuradres')