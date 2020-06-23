CREATE DATABASE  IF NOT EXISTS `cookbook` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cookbook`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: cookbook
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (290),(290),(290),(290),(290),(290);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `ingredient_id` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `main_product` bit(1) DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`),
  KEY `FK34jbb91wprx6ys2443vf43cxj` (`product_id`),
  KEY `FK7p08vcn6wf7fd6qp79yy2jrwg` (`recipe_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (200,150.00,_binary '\0','g',59,50),(201,100.00,_binary '','g',66,50),(202,2.00,_binary '\0','x',51,50),(203,2.00,_binary '\0','x',69,50),(204,30.00,_binary '\0','g',52,50),(205,2.00,_binary '\0','g',70,50),(206,2.00,_binary '\0','g',71,50),(207,2.00,_binary '\0','tbsp',72,50),(208,400.00,_binary '','g',67,51),(209,300.00,_binary '\0','g',83,51),(210,2.00,_binary '\0','tbsp',65,51),(211,1.00,_binary '\0','tbsp',60,51),(212,0.50,_binary '\0','glass',73,51),(213,0.30,_binary '\0','tbsp',74,51),(214,100.00,_binary '\0','g',53,51),(215,2.00,_binary '\0','tbsp',75,51),(216,2.00,_binary '\0','tbsp',76,51),(217,100.00,_binary '\0','g',53,51),(218,0.50,_binary '\0','glass',54,51),(219,800.00,_binary '\0','g',88,51),(220,4.00,_binary '\0','tsp',55,51),(221,200.00,_binary '','g',68,52),(222,125.00,_binary '\0','g',61,52),(223,1.00,_binary '\0','tbsp',65,52),(224,0.30,_binary '\0','x',84,52),(225,0.50,_binary '\0','tbsp',55,52),(226,0.50,_binary '\0','tsp',77,52),(227,2.00,_binary '\0','x',78,52),(228,6.00,_binary '\0','x',85,52),(229,2.00,_binary '\0','tbsp',79,52),(230,0.50,_binary '\0','glass',54,52),(231,250.00,_binary '','g',67,53),(232,100.00,_binary '\0','g',62,53),(233,1.00,_binary '\0','x',84,53),(234,2.00,_binary '\0','tsp',63,53),(235,250.00,_binary '\0','ml',64,53),(236,0.50,_binary '\0','glass',73,53),(237,0.30,_binary '\0','tbsp',74,53),(238,150.00,_binary '\0','g',83,53),(239,5.00,_binary '\0','x',86,53),(240,70.00,_binary '\0','g',87,53),(241,10.00,_binary '\0','x',80,53),(242,400.00,_binary '','g',67,54),(243,1.00,_binary '\0','glass',56,54),(244,0.50,_binary '\0','glass',60,54),(245,1.00,_binary '\0','x',51,54),(246,2.00,_binary '\0','g',70,54),(247,2.00,_binary '\0','g',71,54),(248,1.50,_binary '\0','tsp',81,54),(249,0.50,_binary '\0','tsp',82,54),(250,2.50,_binary '\0','tsp',50,54),(251,4.00,_binary '\0','tsp',65,54),(252,1.00,_binary '\0','x',69,54),(253,200.00,_binary '\0','g',57,54),(254,2.00,_binary '\0','tsp',58,54);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_groups`
--

DROP TABLE IF EXISTS `product_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_groups` (
  `product_group_id` bigint(20) NOT NULL,
  `product_group_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`product_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_groups`
--

LOCK TABLES `product_groups` WRITE;
/*!40000 ALTER TABLE `product_groups` DISABLE KEYS */;
INSERT INTO `product_groups` VALUES (1,'LIQUIDS'),(2,'MEAT'),(40,'GRAINS, PASTA, SIDES'),(41,'DAIRY, EGGS, CHEESE'),(42,'SPICES, HERBS'),(43,'VEGETABLES'),(44,'SEAFOOD'),(45,'INTERNATIONAL CUISINE'),(46,'CEREAL');
/*!40000 ALTER TABLE `product_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL,
  `product_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKrmlc4hd8nhyq1bsmwbljo76mk` (`product_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (50,'cornflakes',46),(51,'egg',41),(52,'parmesan',41),(53,'gorgonzola',41),(54,'sweet cream',41),(55,'butter',41),(56,'milk',41),(57,'sour cream',41),(58,'mayonnaise',41),(59,'spaghetti pasta',40),(60,'wheat flour',40),(61,'tagliatelle pasta',40),(62,'white rice',40),(63,'green curry paste',45),(64,'coconut milk',45),(65,'olive oil',1),(66,'bacon',2),(67,'chicken breast',2),(68,'shrimps',44),(69,'garlic clove',42),(70,'salt',42),(71,'pepper',42),(72,'parsley leaves',42),(73,'chicken broth',42),(74,'turmeric',42),(75,'dill',42),(76,'chive',42),(77,'oregano',42),(78,'garlic cloves',42),(79,'parsley',42),(80,'coriander leaves',42),(81,'sweet pepper powder',42),(82,'hot pepper powder',42),(83,'zucchini',43),(84,'onion',43),(85,'cherry tomatoes',43),(86,'mushrooms',43),(87,'spinach',43),(88,'potatoes',43),(258,'product',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_categories`
--

DROP TABLE IF EXISTS `recipe_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_categories` (
  `recipe_category_id` bigint(20) NOT NULL,
  `recipe_category_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`recipe_category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_categories`
--

LOCK TABLES `recipe_categories` WRITE;
/*!40000 ALTER TABLE `recipe_categories` DISABLE KEYS */;
INSERT INTO `recipe_categories` VALUES (3,'PASTA'),(50,'THAI CUISINE'),(51,'FAST FOOD'),(52,'FAST DINNER'),(53,'ITALIAN CUISINE');
/*!40000 ALTER TABLE `recipe_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
  `recipe_id` bigint(20) NOT NULL,
  `preparation_time` bigint(20) DEFAULT NULL,
  `RECIPE_DETAILS` varchar(950) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recipe_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recipe_category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `FK2hlck33imc68vqkesmwow2eqv` (`recipe_category_id`),
  KEY `FKlc3x6yty3xsupx80hqbj9ayos` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (50,25,'Cook the pasta al dente in salted water. Scald eggs with boiling water, stick to a deep plate, season with salt and beat with a fork.\nPut the diced bacon into the pan and fry on low heat for a few minutes until lightly browned. Add grated garlic and chopped parsley and fry for a few seconds.\nKeep the pan on low heat and add the pasta. Set aside from heat, add half the cheese and season with freshly ground pepper.\nPour over the beaten eggs and mix. The eggs cannot completely cut off, they are supposed to create a creamy sauce and just thicken a little from hot pasta.\nPut on plates and sprinkle with remaining cheese.','Spaghetti Carbonara',3,1),(51,45,'Clean the chicken of membranes and cubes, cut into smaller fillets: cut the tenderloin - the moving part of the fillet, then cut the fillet lengthwise into 2 thinner parts. Break with a pestle to the same thickness, approx. 1 cm. Season with salt and pepper.\nZucchini cut at the ends and cut into small cubes.\nGently coat the fillets in flour. Heat a frying pan with oil or oil. Insert the chicken fillets, fry for about 2 minutes on each side. Put on a plate.\nAdd zucchini to the same frying pan and fry for about 3-4 minutes. In the meantime, add butter.\nAdd all the meat to the pan and slide it under the zucchini in places. Keeping the dish on the fire, pour in the broth, add turmeric, dill and chives. Stir and boil.\nAfter a while, pour the cream and gently mix the ingredients. Sprinkle with chopped gorgonzola. Season with salt and pepper if necessary and cook for another minute.\nBoil the potatoes, beat and serve with butter.','Chicken in sauce with Zucchini and Gorgonzola',53,1),(52,25,'Defrost shrimps, clean if necessary, tear off the petioles, rinse and dry.\nCook the pasta in salted water according to the instructions on the packaging.\nIn a large frying pan on a tablespoon of olive oil stew diced onion. Includes butter, oregano and grated garlic, fry together for a while.\nShrimp available and fry for approx. 1 stirring constantly, season with salt and pepper.\nIncludes quartered cherry tomatoes and parsley. Mix and fry together for half a minute.\nPour the cream and bring to a boil. Boil for about 1 quickly until the sauce thickens, season with freshly ground pepper and salt as needed.\nStrained noodles available and heat everything together.','Pasta with Shrimps',3,1),(53,30,'Boil rice in salted water. Pour 2 tablespoons of coconut milk into a wok or large deep pan and add the onion, chopped into semi-slices. Fry for about 2 minutes, stirring occasionally with a spatula. Then add green curry paste and fry often, stirring for about 2 minutes.\n\nPour the broth and the rest of the coconut milk, mix and bring to a boil over high heat. Add turmeric and cook for about 10 minutes, stirring constantly.\n\nAdd the diced chicken breast and cook for about 5 minutes, then add the diced zucchini and diced mushrooms. Cook for about 4 minutes, stir constantly.\n\nAdd spinach, mix and boil. Sprinkle with half coriander, mix and serve with rice. Sprinkle with the rest of the coriander.','Green Curry with Chicken',50,1),(54,30,'Cut the fillets into 2.5 cm pieces, season with salt and pepper and mix.\nCrumble the flakes or mix in a mixer or chopper for a while.\nIn a bowl, mix together the milk, flour, egg, salt, pepper and paprika to a homogeneous mass. Put the fillets into the mass and mix.\nPreheat the oven to 200 degrees C. Cover a large baking tray with baking paper.\nRemove piece of meat from the dough and coat in flakes, place on a baking tray. Sprinkle with salt and drizzle with oil or melted butter (using a narrow-tipped container or brush with a brush). \nBake for 15 minutes.','Chicken Nuggets',51,1);
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `e_mail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `logged` bit(1) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'mac.ziel@gmail.com',_binary '','Maciek Zieliński','password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cookbook'
--

--
-- Dumping routines for database 'cookbook'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 19:49:57
