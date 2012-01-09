-- MySQL dump 10.13  Distrib 5.5.15, for osx10.6 (i386)
--
-- Host: localhost    Database: cubedata
-- ------------------------------------------------------
-- Server version	5.5.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AnonymousUser`
--

DROP TABLE IF EXISTS `AnonymousUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnonymousUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_city` varchar(255) DEFAULT NULL,
  `company_country` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `job_year` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnonymousUser`
--

LOCK TABLES `AnonymousUser` WRITE;
/*!40000 ALTER TABLE `AnonymousUser` DISABLE KEYS */;
INSERT INTO `AnonymousUser` VALUES (1,'Bangalore','115','true','Developer','2011',NULL,NULL),(3,'Bangalore','115','false','QA','2008',NULL,NULL),(4,'Bangalore','115','true','Dev','2011',NULL,1);
/*!40000 ALTER TABLE `AnonymousUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnonymousUser_CubeReview`
--

DROP TABLE IF EXISTS `AnonymousUser_CubeReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnonymousUser_CubeReview` (
  `AnonymousUser_id` bigint(20) NOT NULL,
  `cubeReviews_id` bigint(20) NOT NULL,
  UNIQUE KEY `cubeReviews_id` (`cubeReviews_id`),
  KEY `FK1141D4F4D01C3E36` (`AnonymousUser_id`),
  KEY `FK1141D4F4B31CBFC5` (`cubeReviews_id`),
  CONSTRAINT `FK1141D4F4B31CBFC5` FOREIGN KEY (`cubeReviews_id`) REFERENCES `CubeReview` (`id`),
  CONSTRAINT `FK1141D4F4D01C3E36` FOREIGN KEY (`AnonymousUser_id`) REFERENCES `AnonymousUser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnonymousUser_CubeReview`
--

LOCK TABLES `AnonymousUser_CubeReview` WRITE;
/*!40000 ALTER TABLE `AnonymousUser_CubeReview` DISABLE KEYS */;
/*!40000 ALTER TABLE `AnonymousUser_CubeReview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Article`
--

DROP TABLE IF EXISTS `Article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) DEFAULT NULL,
  `article_view` int(11) NOT NULL,
  `content` longtext,
  `submit_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `articleImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D6FA266C1E` (`category_id`),
  KEY `FK379164D6A7CD013E` (`author_id`),
  CONSTRAINT `FK379164D6A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK379164D6FA266C1E` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Article`
--

LOCK TABLES `Article` WRITE;
/*!40000 ALTER TABLE `Article` DISABLE KEYS */;
INSERT INTO `Article` VALUES (2,'',2,'this is test article','2011-12-11 18:11:30','Test Article for testing',1,1,NULL),(4,'',31,'As location gradually becomes more important to the on-the-go consumer, what with daily deals, check-in coupons, and local promotions, so that consumer’s fine location becomes more important to merchants. GPS and wi-fi can only get you so far, though, and inside a mall or airport it’s much more difficult and inefficient to narrow down a user’s location to anything approaching usability.\r\n\r\nThere are systems for tracking people and devices indoors, but Scottish startup Sensewhere (formerly Satsis) says they’ve leapfrogged existing solutions. Their new “self-correcting” location-sensing network will allow for quick and low-power situation of devices to within 5m by forming a sort of constantly updated mesh of self-aware devices.\r\n\r\nThe company, which split off from research at the University of Edinburgh and got its launch money through awards and loans, received £1.2 million in funding from private equity firms just this last August. They’re making their public launch nowish (there have been a few news items over the last week or so) and have made an app available on iOS and Android.\r\n\r\nTheir technology is similar to existing indoor-tracking systems, but the company says theirs is superior due to its self-updating nature:\r\nBy cross-referencing this information from different sources, at different times, sensewhere improves the accuracy of indoor location over time, autonomously mapping RF reference points in a way that is self-correcting, updated by every device that determines its own position, reliable, and more accurate than other solutions.\r\n\r\nIf you cut through the marketing there, what you basically have is a self-updating network with devices pinging a central server with the networks they see (wi-fi, Bluetooth, RF, etc.) and their position as best they can determine, and that central server continually collates this data and updates the map, using mobile nodes as reference as well as stationary ones.\r\n\r\nAssuming the system works, it could be a great add-on for megastructures like department stores and airports, with tons of device traffic and square footage. While I’m sure it falls short of the idea right now, one imagines the endpoint: get a promo deal when you walk into a store that expires when you walk out – that sort of thing.\r\n\r\nRight now it’s available as an app, but it seems unlikely that they’ll see uptake in that form. They’ll have to go white-label and market themselves as a customizable solution for individual locations – that or talk with mapping or deal companies and get themselves integrated at a lower level. No hardware is required for setup at your local mall, but places will still have to tie in and do setup – there’s a lot of low-level work to be done there interfacing with small stores, corporate offices, and so on. Retail is a nightmare but they are always up for new marketing opportunities.\r\n\r\nThe Sensewhere site is pretty spartan at the moment, but you can get more info on the apps into which the service is integrated through them. The system is described in more detail in their press release and at Crowdsourcing.','2011-12-13 00:00:00','Scottish Startup Sensewhere Promises Accurate Indoor Location Tracking',1,1,'null|null');
/*!40000 ALTER TABLE `Article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Tech','2011-12-11 18:07:00','');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` longtext,
  `created_on` datetime DEFAULT NULL,
  `userAlias` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BDE863FEB73CE56` (`article_id`),
  CONSTRAINT `FK9BDE863FEB73CE56` FOREIGN KEY (`article_id`) REFERENCES `Article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Company`
--

DROP TABLE IF EXISTS `Company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `overview` longtext,
  `updated_on` datetime DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `industry_type_id` bigint(20) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BDFD45DD0FB023B` (`industry_type_id`),
  CONSTRAINT `FK9BDFD45DD0FB023B` FOREIGN KEY (`industry_type_id`) REFERENCES `IndustryType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Company`
--

LOCK TABLES `Company` WRITE;
/*!40000 ALTER TABLE `Company` DISABLE KEYS */;
INSERT INTO `Company` VALUES (1,'','2011-12-10 00:00:00','ThoughtWorks','ThoughtWorks was founded by Roy Singham in a Chicago basement in 1993. Roy\'s formula for success from the start was \"Attitude, Aptitude, and Integrity\" – he built ThoughtWorks upon the firm belief that communities of talented, driven, and principled people could move mountains.  Today we employ over 1700 people across the globe. With a reputation as leaders and experts in the industry, we’ve become a destination employer for like-minded people with a passion to improve how businesses design, build and evolve software. We are very selective, hiring only 1 of every 100 aspirants (1 of 200 in some areas). This means we have equally capable people wherever we are located.  Whether we\'re building a new custom system for a client, fixing a project that\'s tied up in knots, or helping to make a software development organization more productive, ThoughtWorks delivers.  Our customers start seeing benefits in weeks, not months or years, a radical change for some clients and partners caught up in the inertia of working as they\'ve always worked.  Software development with ThoughtWorks feels and looks different: it is fast, evolutionary, even fun. There are no secrets – you can walk into a ThoughtWorks project room and see exactly where we are at a single glance. We take good ideas – best practices like collaboration, feedback, transparency, and testing – and we build them into the fabric of the way we work. We\'ve spent years, and many early mistakes, building up the set of ThoughtWorks’ core capabilities - reusable approaches that we believe to be world class. Of course, every problem is different, and our commitment to satisfying our clients far outstrips any affinity to a set of practices.  We are based in the United States, UK, Australia, Brazil, Canada, Germany, India and China and we work with clients from each location.',NULL,'www.thoughworks.com',11,NULL),(2,'\0','2011-12-19 00:00:00','Infosys Technologies','Infosys Limited (NASDAQ: INFY) was started in 1981 by seven people with US$ 250. Today, we are a global leader in the \"next generation\" of IT and consulting with revenues of US$ 6.604 billion (LTM Q2-FY12). Infosys defines, designs and delivers technology-enabled business solutions for Global 2000 companies. Infosys also provides a complete range of services by leveraging our domain and business expertise and strategic alliances with leading technology providers. Our offerings span business and technology consulting, application services, systems integration, product engineering, custom software development, maintenance, re-engineering, independent testing and validation services, IT infrastructure services and business process outsourcing. Infosys pioneered the Global Delivery Model (GDM), which emerged as a disruptive force in the industry leading to the rise of offshore outsourcing. The GDM is based on the principle of taking work to the location where the best talent is available, where it makes the best economic sense, with the least amount of acceptable risk. Infosys has a global footprint with 64 offices and 65 development centers in US, India, China, Australia, Japan, Middle East, UK, Germany, France, Switzerland, Netherlands, Poland, Canada and many other countries. Infosys and its subsidiaries have 141,822 employees as on September 30, 2011. Infosys takes pride in building strategic long-term client relationships. 98.5% of our revenues come from existing customers (Q2 FY 12).',NULL,'www.infosys.com',11,NULL),(3,'','2011-12-19 00:00:00','Wipro Technologies','In the year of 1945, in pre–independent India, a vision was born, which would eventually stand out as a brand name synonymous with innovation and integrity. Starting off with consumer products business, Wipro then diversified into newer areas including IT hardware and IT services. Such has been the dynamic power of the organization that over the past 50 years, Wipro has evolved into a leading global IT company, a company which has pioneered many an innovation in the IT services, BPO and R&D services space.  Headquartered at Bangalore, India, we at Wipro implement the philosophy of \'Applying Thought\', thereby helping clients to \"Do Business Better\". Our path breaking innovations and ideas have culminated into the `Wipro Way\' – a process which directly impacts customer benefits by improving time-to-market, enhancing predictability and reliability, and cutting costs.','2011-12-19 00:00:00','www.wipro.com',11,'null|null'),(4,'','2011-12-19 00:00:00','Tata Consultancy Services','Tata Consultancy Services Limited (TCS) is an Indian software services and consulting company. It is one of the world\'s largest providers of information technology and business process outsourcing services. As of 2007, it is Asia\'s largest information technology firm and has the largest number of employees among Indian IT companies with strength of over 111,000 employees in 47 countries. The company generated consolidated revenues of US $5.7 billion for fiscal year ended 31 March 2008 and is listed on the National Stock Exchange and Bombay Stock Exchange in India. TCS is part of one of Asia\'s largest conglomerates, the Tata Group, which has interests in areas such as energy, telecommunications, financial services, manufacturing, chemicals, engineering and materials. ',NULL,'www.tcs.com',11,NULL),(5,'\0','2011-12-19 00:00:00','IBM','International Business Machines Corporation (NYSE: IBM) or IBM is a multinational technology and consulting corporation headquartered in Armonk, New York, United States. IBM manufactures and sells computer hardware and software, and it offers infrastructure, hosting and consulting services in areas ranging from mainframe computers to nanotechnology.[2] As of September 2011, IBM is the second-largest publicly traded technology company in the world by market capitalization.[3]  The company was founded in 1911 as the Computing Tabulating Recording Corporation through a merger of three companies: the Tabulating Machine Company, the International Time Recording Company, and the Computing Scale Corporation.[4][5] CTR adopted the name International Business Machines in 1924, using a name previously designated to CTR\'s subsidiary in Canada and later South America. Its distinctive culture and product branding has given it the nickname Big Blue.',NULL,'www.ibm.com',11,NULL),(6,'','2011-12-19 00:00:00','Accenture','Accenture is a global management consulting, technology services and outsourcing company, with more than 244,000 people serving clients in more than 120 countries. Combining unparalleled experience, comprehensive capabilities across all industries and business functions, and extensive research on the world’s most successful companies, Accenture collaborates with clients to help them become high-performance businesses and governments. The company generated net revenues of US$25.5 billion for the fiscal year ended Aug. 31, 2011. ',NULL,'www.accenture.com',11,NULL),(7,'','2011-12-19 00:00:00','Cognizant','Cognizant Technology Solutions (NASDAQ: CTSH) is an information technology services company with headquarters in Teaneck, New Jersey U.S.A. and with significant operations in Chennai, India.',NULL,'www.cognizant.com',11,NULL),(8,'','2011-12-19 00:00:00','HCL Technologies','HCL Technologies is a leading global IT services company, working with clients in the areas that impact and redefine the core of their businesses. Since its inception into the global landscape after its IPO in 1999, HCL focuses on \'transformational outsourcing\', underlined by innovation and value creation, and offers integrated portfolio of services including software-led IT solutions, remote infrastructure management, engineering and R&D services and BPO. HCL leverages its extensive global offshore infrastructure and network of offices in 26 countries to provide holistic, multi-service delivery in key industry verticals including Financial Services, Manufacturing, Consumer Services, Public Services and Healthcare. HCL takes pride in its philosophy of \'Employees First, Customers Second\' which empowers our 80,520 transformers to create a real value for the customers. HCL Technologies, along with its subsidiaries, has reported consolidated revenues of US$ 3.7 billion (Rs. 16,977 crores), as on 30th September (on LTM basis) 2011.',NULL,'www.hcltech.com',11,NULL),(9,'','2011-12-19 00:00:00','Hewlett Packard','Hewlett-Packard Company (NYSE: HPQ) or HP is an American multinational information technology corporation headquartered in Palo Alto, California, USA that provides products, technologies, software, solutions and services to consumers, small- and medium-sized businesses (SMBs) and large enterprises, including customers in the government, health and education sectors. The company was founded in a one-car garage in Palo Alto by William (Bill) Redington Hewlett and Dave Packard. Currently, HP is the world\'s leading PC manufacturer, operating in nearly every country. It specializes in developing and manufacturing computing, data storage, and networking hardware, designing software and delivering services. Major product lines include personal computing devices, enterprise, and industry standard servers, related storage devices, networking products, software and a diverse range of printers, and other imaging products. HP markets its products to households, small- to medium-sized businesses and enterprises directly as well as via online distribution, consumer-electronics and office-supply retailers, software partners and major technology vendors. HP also has strong services and consulting business around its products and partner products.',NULL,'www.hp.com',11,NULL),(10,'','2011-12-19 00:00:00','Tech Mahindra','Tech Mahindra is part of the US $14.4 billion Mahindra Group, in partnership with British Telecommunications plc (BT), one of the world’s leading communications service providers. Focused primarily on the telecommunications industry, Tech Mahindra is a leading global systems integrator and business transformation consulting organization. Tech Mahindra has recently expanded its IT portfolio by acquiring the leading global business and information technology services company, Mahindra Satyam (earlier known as Satyam Computer Services).  Tech Mahindra’s capabilities spread across a broad spectrum, including Business Support Systems (BSS), Operations Support Systems (OSS), Network Design & Engineering, Next Generation Networks, Mobility Solutions, Security consulting and Testing. The solutions portfolio includes Consulting, Application Development & Management, Network Services, Solution Integration, Product Engineering, Infrastructure Managed Services, Remote Infrastructure Management and BSG (comprises BPO, Services and Consulting). With an array of service offerings for TSPs, TEMs and ISVs, Tech Mahindra is a chosen transformation partner for several leading wireline, wireless and broadband operators in Europe, Asia-Pacific and North America.',NULL,'www.techmahindra.com',11,NULL),(11,'','2011-12-19 00:00:00','Sapient','For two decades Sapient has helped its clients anticipate, navigate and leverage change. Our deeply engrained technology heritage, running through all of our businesses like a foundational thread, has allowed us to utilize technology to its fullest business application and creative expression, giving our clients something that, in today\'s business climate, they simply can\'t succeed without: the ability to quickly and confidently adapt to ever-changing conditions.     Throughout our history, Sapient has demonstrated to companies across a wide spectrum of industries and geographies, that success doesn\'t rely solely on how you manage your business, but also, on how you manage changes that affect your business. And, at no other time has that been more applicable, more profoundly relevant, than now.',NULL,'www.sapient.com',11,NULL),(12,'','2011-12-19 00:00:00','Capgemini','Capgemini is headquartered in Paris, France and operates in 40 countries. We are, above all, a people company: around 115,000 people in North America, Europe, South America and the Asia Pacific region.',NULL,'www.capgemini.com',11,NULL),(13,'','2011-12-19 00:00:00','Computer Science Corporation','Computer Sciences Corporation (CSC) (NYSE: CSC) is an American information technology (IT) and business services company headquartered in Falls Church, Virginia, USA. CSC predominantly provides IT services in the following areas: systems integration and professional services; enterprise application development and management; application software for the financial services industry; business process outsourcing; managed hosting services; and application and IT infrastructure outsourcing. CSC\'s consulting and professional services include advising clients on the acquisition and utilization of IT and on business strategy, security, modeling, simulation, engineering, operations, change management and business process reengineering. CSC serves Fortune Global 1000 companies in fifteen industries and national and local governments. CSC employs about 93,000 people in 90 countries and ranks among the largest outsourcing companies in the world.',NULL,'www.csc.com',11,NULL),(14,'','2011-12-19 00:00:00','Patni Computer Systems','iGATE Patni heralds the beginning of a new era by bringing together two companies – iGATE and Patni. Between the two, we  provide full-spectrum consulting, technology and business process outsourcing, and product engineering services. In a crowded and intensely competitive marketplace for such offerings, we have built a reputation and core differentiating attribute around our unique Business Outcomes-based model. Armed with over three decades of IT Services experience and our distinctive philosophy of ‘Accountable for Clients’ Business’ powered by the iTOPS (Integrated Technology and Operations) platform, our multi-location global organization consistently delivers effective solutions to over 360 Fortune 1000 clients.  Our customer footprint spans across verticals like:  banking & financial services; insurance & healthcare; life sciences; manufacturing, retail, distribution & logistics; media, entertainment, leisure & travel; communication, energy & utilities; public sector; and independent software vendors.  A diversified, well-trained and motivated talent pool of 26000 people works cohesively to deliver solutions based around a mature global delivery model to clients across the Americas, Europe- Middle East-Africa (EMEA) and Asia-Pacific. Despite the reach and global-spanning capabilities of our delivery centers, we are large enough to be resourceful and small enough to be flexible, making us one of the most dynamic and highly adaptable IT service giants.  Our comprehensive portfolio of services includes application development and management, verification & validation, enterprise application solutions, infrastructure management, customer interaction services & business process outsourcing, product engineering services, and business & technology consulting.  iGATE Patni capitalizes on the strength of our numerous combined synergies and core capabilities including: deep domain and delivery expertise; focus on micro-verticals; suites of IP-led solutions, methodologies and frameworks; technology alliances and service partnerships; secure & scalable delivery infrastructure across geographies; and mature quality management based on ISO, SEI-CMMi, Six Sigma, ITIL and COPC standards; to be an effective and reliable transformation partner to our varied client base.',NULL,'www.patni.com',11,NULL),(15,'','2011-12-19 00:00:00','MindTree','MindTree was started in August 1999 by a diverse team of 10 professionals who came from three different nations and had already scripted successful careers. Their vision to build an institution that is among the most admired companies globally is shared with every MindTree Mind and is reflected in the way we do business.',NULL,'www.mindtree.com',11,NULL),(16,'','2011-12-19 00:00:00','Keane','Keane, an NTT DATA Company, is an IT services firm headquartered in the US with more than 12,500 professionals worldwide.  For 45 years, Keane has been an Application Services specialist with distinguished project management credentials. Today, we offer a flagship suite of Application Services, as well as Infrastructure and Business Processing Outsourcing solutions delivered through onsite, nearshore, and offshore resources.  Our projects, managed services, and outsourcing engagements deliver value for a range of businesses and government agencies.  Our clients find we’re right-sized, agile, and easy to work with. Find out how Keane’s size and approach makes us better able to ensure your IT is optimized to deliver more.',NULL,'www.keane.com',11,'null|null'),(17,'','2011-12-19 00:00:00','L&T Infotech','L&T Infotech is a global IT services and solutions provider. We provide the winning edge to our clients by leveraging our Business-to-IT Connect and deeply committed people. Our clients include industry leaders like Chevron, Freescale, Hitachi, Sanyo and Lafarge, among others. They have found in us a right-size partner who combines scale, stability and customer-centricity  Our parent company is Larsen & Toubro Ltd. (L&T), a technology, engineering, manufacturing and construction conglomerate, with global operations. This rich corporate heritage has given us many inherent advantages that we translate into tangible benefits for our clients. ',NULL,'www.lntinfotech.com',11,NULL),(18,'','2011-12-19 00:00:00','Aditi Technologies','Founded in 1994, Aditi pioneered the idea that great software products can be built out of India. Today, Aditi represents the best Microsoft software & applications development capability in the world. Strengthened by innovative IP solutions, leading global enterprises and software product companies rely on Aditi to predictably deliver quality products and applications. ',NULL,'www.aditi.com',11,NULL),(19,'','2011-12-19 00:00:00','Symphony Services','Symphony Services is a software innovation company. We help our clients ideate, develop, deploy and manage innovative software and software-enabled products. Our solutions address traditional and new software product engineering challenges created by faster release cadences, the need for mobile cross-platform user experiences and the shift to a cloud delivery model.  Symphony’s unique Outcome CertaintyTM engagement model enables our clients to accelerate their product experiences while tackling a wide range of new development complexity and cost challenges with guaranteed results. Our global client base includes 8 of the 10 largest software manufacturers, along with leading healthcare, retail, financial, telecom and clean-tech companies. ',NULL,'www.symphonysv.com',11,NULL),(20,'','2011-12-19 00:00:00','Global Logic','GlobalLogic is an offshore software R&D services company that was founded in 2000 by Rajul Garg, Sanjay Singh, Manoj Agarwala and Tarun Upadhyay and has headquarters in McLean, Virginia and San Jose, California. It is a privately held company funded by New Enterprise Associates, Sequoia Capital, Westbridge Capital, Goldman Sachs, and New Atlantic Ventures. GlobalLogic provides software product design, product development, quality assurance, product support and consulting services. The company is also involved in telecommunications, wireless products and mobile devices. Some companies use GlobalLogic to outsource the production of their own primary products.',NULL,'www.globallogic.com',11,NULL),(21,'','2011-12-19 00:00:00','NIIT Technologies','NIIT Technologies is a leading IT solutions organization, servicing customers in North America, Europe, Middle East, Asia and Australia. The Company offers services in Application Development and Maintenance, Managed Services, Cloud Computing and Business Process Outsourcing to organizations in the Financial Services, Insurance, Travel, Transportation and Logistics, Manufacturing and Distribution and Government sectors.  The Company’s deep domain knowledge and new approaches to customer experience management with robust outsourcing capabilities, and a dual shore delivery model, have made NIIT Technologies a preferred IT partner for global majors in these chosen industries. Profound and enduring customer engagements have become a hallmark of NIIT Technologies.  NIIT Technologies vision is to be the “First Choice” of services for the focused segments serviced. The Company has a simple strategy - to focus and differentiate. It competes on the strength of its specialization.  Over the years the Company has forged extremely rewarding relationships with global majors, a testimony to mutual commitment and its ability to retain marquee clients, drawing repeat business from them. Whether it is global banking and insurance major, ING, leading Asset Management solutions provider, SEI, the Number Two cement manufacturer, Holcim or travel big-wigs, British Airways and Sabre, NIIT Technologies has been able to scale its interactions with these marquee clients into extremely meaningful, multi-year \"collaborations.\"',NULL,'www.niit-tech.com',11,NULL),(22,'','2011-12-19 00:00:00','Steria','Steria delivers IT enabled business services which help organisations in the public and private sectors operate more efficiently and profitably. By combining in depth understanding of our clients\' businesses with expertise in IT and business process outsourcing, we take on our clients\' challenges and develop innovative solutions to address them.  Through our highly collaborative consulting style, we work with our clients to transform their business, enabling them to focus on what they do best. Our 20,000 people, working across 16 countries, support the systems, services and processes that make today\'s world turn, touching the lives of millions around the globe each day.  Founded in 1969, Steria has offices in Europe, India, North Africa and SE Asia and a 2010 revenue of €1.69 billion. 20%(*) of Steria\'s capital is owned by its employees. Headquartered in Paris, Steria is listed on the Euronext Paris market.',NULL,'www.steria.com',11,NULL),(23,'','2011-12-19 00:00:00','Headstrong','Headstrong (A Genpact Company) is a global IT consulting company with presence across North America, Europe and Asia-Pacific, operating in more than 14 locations. It was formed in 2000 when Arjun Malhotra led Techspan was merged with James Martin Associates (by Dr. James Martin) who today sits as Headstrong\'s chairman emeritus. Arjun Malhotra became Chairman & CEO of Headstrong in 2004. Beginning from the start of 2010 Sandeep Sahai, the president of the company, became the CEO. April 6, 2011, NEW YORK--(BUSINESS WIRE)--Genpact Limited (NYSE: G), a global leader in business process and technology management, today announced that it has signed a definitive agreement to acquire Headstrong Corporation, a global provider of comprehensive consulting and IT services with a specialized focus in capital markets and healthcare, for cash consideration of $550 million. Genpact expects the transaction to be accretive to earnings per share on a GAAP basis in 2011. The transaction is being funded by a combination of existing cash and acquisition financing, and is expected to close by May 31, 2011, subject to customary regulatory and other conditions In 2006, Headstrong had 2,000-employees worldwide. The company experiences more than 30% revenue growth year-on-year since 2006.',NULL,'www.headstrong.com',11,NULL),(24,'','2011-12-19 00:00:00','CMC Limited','CMC Limited is a leading systems engineering and integration company in India, offering application design, development, testing services and asset-based solutions in niche segments through turnkey projects of national importance. CMC has also been expanding its service presence in international markets offering off-shoring advantages and delivering value through service level-based and project scope-based deliveries.  Since its inception on December 26, 1975, CMC has been a frontrunner in providing IT solutions and services. CMC was the first ever enterprise in India to set up a countrywide data network called INDONET - a computer network providing access to major cities in India, way back in 1985.  A subsidiary of Tata Consultancy Services Limited (TCS Ltd), one of the world\'s leading information technology consulting, services and business process outsourcing organisations, CMC Limited is a part of the US$70 billion Tata Group, India\'s best known business conglomerate.  Today, CMC Limited, an ISO 9001:2000, certified and CMMI Level V accredited organisation, is positioned as a premier IT solutions provider in the fast growing and competitive IT market. We execute large and complex turnkey projects, and have built, managed and supported our customers\' IT systems across the value chain infrastructure, applications and business processes.',NULL,'www.cmcltd.com',11,NULL),(25,'','2011-12-19 00:00:00','Ness Technologies','The company provides information technology and business planning services in North America, Europe, the Middle-East, and the Asia Pacific. It operates in three sectors: software product engineering, system integration, application development and consulting, and software distribution. Ness Software Product Labs (SPL) operates approximately 50 product labs for independent software vendors, high-tech companies, and global organizations that build or rely on commercial-grade software to generate core revenue. In the sphere of system integration, application development and consulting, the company offers an array of high-value outsourced information technology services. NessPro, markets and sells enterprise software licenses of approximately 30 third-party software vendors to corporate clients. It provides implementation, customization, and support services related to such licenses.',NULL,'www.ness.com',11,NULL),(26,'','2011-12-19 00:00:00','Persistent Systems','Persistent Systems (BSE: 533179, NSE: PERSISTENT) is a business based in Pune, India, that sells outsourced software development. It was established in 1990. Persistent has over 6,800 employees and 300 customers spread across North America, Europe, and Asia. Persistent Systems was founded in 1990. In 2008 the company won a NASSCOM award for innovation. An award for corporate governance from the Institute of Company Secretaries of India was won in December 2010.',NULL,'www.persistentsys.com',11,NULL),(27,'','2011-12-19 00:00:00','Perot Systems','Perot Systems was an information technology services provider founded in 1988 by a group of investors led by Ross Perot and based in Plano, Texas, United States. A Fortune 1000 corporation with offices in more than 25 countries, Perot Systems employed more than 23,000 people and had an annual revenue of $2.8 billion before its acquisition in 2009 by Dell, Inc.',NULL,'www.perotsystems.com',11,NULL),(28,'','2011-12-19 00:00:00','Logica','Logica is a business and technology service company, employing 41,000 people. It provides business consulting, systems integration and outsourcing to clients around the world, including many of Europe\'s largest businesses. Logica creates value for clients by successfully integrating people, business and technology. It is committed to long term collaboration, applying insight to create innovative answers to clients’ business needs.',NULL,'www.logica.com',11,NULL),(29,'','2011-12-19 00:00:00','Zensar Technologies','Zensar Technologies (Zensar) is a globally renowned software services company that specializes in providing a complete range of Software Services and Solutions. Zensar is ranked amongst India\'s Top 20 Software Services Companies by National Association of Software and Services Companies (NASSCOM) and is also recognized by the Department of Scientific and Industrial Research (DSIR) for its robust in-house Research and Development practices and an acknowledged leader in Innovation. The Software services range from the traditional to the transformational – Enterprise Product Implementation and Hosting, Business Intelligence and Data Warehousing, Collaboration and Knowledge Management Services, Business Process Outsourcing and Optimization, Remote Infrastructure Management and Testing, and the entire range of Software Application Planning, Portfolio Building, Development, Migration and Support. Zensar is the world\'s first enterprise-wide SEI CMM Level 5 Company and enjoys a strong presence in the United States, Europe, Africa, Middle East and Asia-Pacific regions.To service Global Customers, Zensar has Delivery Centers in Pune and Hyderabad in India, China, Japan and the UK. With 6000+ associates, 400+ customers and 14 nationalities operating in more than 20 global locations, Zensar helps transform Global Corporations. ',NULL,'www.zensar.com',11,NULL),(30,'','2011-12-19 00:00:00','Tavant Technologies','Tavant Technologies is an IT solutions and services provider that employs cutting-edge and emerging technologies to provide game-changing results for its customers. It prides itself on its traditions of engineering and process excellence coupled with high employee and customer satisfaction levels. Its unique best-shore delivery model provides close onsite interaction with customers and a strong process oriented offshore team. At all levels, its employees continually interact to provide a superior outsourcing experience to customers. Tavant specializes in building solutions and providing end-to-end services in the domain of Service Operations, Consumer Lending, eBusiness and Trading & Securities. Tavant\'s suite of products and services are routinely rated high by the industry & media and deployed by leading business names, like Electronic Arts, TiVo, MLB AM, New York Times, Ingersoll Rand, Bobcat, Federal Signal and many more. Tavant is an ISO 27001 compliant and SEI-CMMI level 4 organization.',NULL,'www.tavant.com',11,NULL);
/*!40000 ALTER TABLE `Company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompanyAddress`
--

DROP TABLE IF EXISTS `CompanyAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompanyAddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` longtext,
  `address_type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70072CD71366CCF6` (`company_id`),
  CONSTRAINT `FK70072CD71366CCF6` FOREIGN KEY (`company_id`) REFERENCES `Company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompanyAddress`
--

LOCK TABLES `CompanyAddress` WRITE;
/*!40000 ALTER TABLE `CompanyAddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `CompanyAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CubeQuestion`
--

DROP TABLE IF EXISTS `CubeQuestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CubeQuestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CubeQuestion`
--

LOCK TABLES `CubeQuestion` WRITE;
/*!40000 ALTER TABLE `CubeQuestion` DISABLE KEYS */;
INSERT INTO `CubeQuestion` VALUES (1,'2011-12-11 01:45:29',NULL,'Your opportunities for professional growth at %s?'),(2,'2011-12-11 01:45:29',NULL,'Your opportunities for career advancement within %s?'),(3,'2011-12-11 01:45:29',NULL,'Your compensation compared with similar jobs elsewhere?'),(4,'2011-12-11 01:45:29',NULL,'Your benefits package compared with similar employers?'),(5,'2011-12-11 01:45:29',NULL,'Information and knowledge sharing within %s?'),(6,'2011-12-11 01:45:29',NULL,'Communications from management about important issues and changes?'),(7,'2011-12-11 01:45:29',NULL,'%s as a place you would recommend to others to work?'),(8,'2011-12-11 01:45:29',NULL,'%s as a place you are proud to work?'),(9,'2011-12-11 01:45:29',NULL,'Feedback you receive about your job performance?'),(10,'2011-12-11 01:45:29',NULL,'Recognition and praise you receive when you do a good job?'),(11,'2011-12-11 01:45:29',NULL,'Leadership abilities of Senior management?'),(12,'2011-12-11 01:45:29',NULL,'Competence of Senior management?'),(13,'2011-12-11 01:45:29',NULL,'Management support in permitting time off when you think it\'s necessary?'),(14,'2011-12-11 01:45:29',NULL,'Employer support in balancing between work life and personal life?'),(15,'2011-12-11 01:45:29',NULL,'Fairness in how promotions are given and people are treated?'),(16,'2011-12-11 01:45:29',NULL,'The level of respect shown by management toward employees?'),(17,'2011-12-11 01:45:31',NULL,'Overall; how satisfied are you with %s as a place to work?');
/*!40000 ALTER TABLE `CubeQuestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CubeRating`
--

DROP TABLE IF EXISTS `CubeRating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CubeRating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `questionID` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `anonymousUser_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE7028252D01C3E36` (`anonymousUser_id`),
  KEY `FKE70282521366CCF6` (`company_id`),
  CONSTRAINT `FKE70282521366CCF6` FOREIGN KEY (`company_id`) REFERENCES `Company` (`id`),
  CONSTRAINT `FKE7028252D01C3E36` FOREIGN KEY (`anonymousUser_id`) REFERENCES `AnonymousUser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CubeRating`
--

LOCK TABLES `CubeRating` WRITE;
/*!40000 ALTER TABLE `CubeRating` DISABLE KEYS */;
INSERT INTO `CubeRating` VALUES (1,1,4,1,1),(2,2,4,1,1),(3,3,4,1,1),(4,4,3,1,1),(5,5,4,1,1),(6,6,3,1,1),(7,7,2,1,1),(8,8,1,1,1),(9,9,1,1,1),(10,10,2,1,1),(11,11,3,1,1),(12,12,4,1,1),(13,13,5,1,1),(14,14,5,1,1),(15,15,3,1,1),(16,16,3,1,1),(17,17,4,1,1),(27,1,2,3,10),(28,2,3,3,10),(29,3,2,3,10),(30,4,2,3,10),(31,5,1,3,10),(32,6,3,3,10),(33,7,1,3,10),(34,8,2,3,10),(35,9,3,3,10),(36,10,4,3,10),(37,11,2,3,10),(38,12,2,3,10),(39,13,3,3,10),(40,14,1,3,10),(41,15,2,3,10),(42,16,2,3,10),(43,17,2,3,10),(44,1,4,3,21),(45,2,4,3,21),(46,3,3,3,21),(47,4,3,3,21),(48,5,2,3,21),(49,6,2,3,21),(50,7,4,3,21),(51,8,3,3,21),(52,9,1,3,21),(53,10,2,3,21),(54,11,1,3,21),(55,12,2,3,21),(56,13,3,3,21),(57,14,3,3,21),(58,15,4,3,21),(59,16,4,3,21),(60,17,4,3,21),(61,1,3,4,1),(62,2,4,4,1),(63,3,5,4,1),(64,4,4,4,1),(65,5,3,4,1),(66,6,2,4,1),(67,7,1,4,1),(68,8,2,4,1),(69,9,1,4,1),(70,10,4,4,1),(71,11,5,4,1),(72,12,3,4,1),(73,13,1,4,1),(74,14,2,4,1),(75,15,3,4,1),(76,16,4,4,1),(77,17,5,4,1);
/*!40000 ALTER TABLE `CubeRating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CubeReview`
--

DROP TABLE IF EXISTS `CubeReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CubeReview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) DEFAULT NULL,
  `company_advice` longtext,
  `company_con` longtext,
  `company_pro` longtext,
  `created_on` datetime DEFAULT NULL,
  `review_heading` varchar(255) DEFAULT NULL,
  `review_type` varchar(255) DEFAULT NULL,
  `anonymousUser_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE73BC80DD01C3E36` (`anonymousUser_id`),
  KEY `FKE73BC80D1366CCF6` (`company_id`),
  CONSTRAINT `FKE73BC80D1366CCF6` FOREIGN KEY (`company_id`) REFERENCES `Company` (`id`),
  CONSTRAINT `FKE73BC80DD01C3E36` FOREIGN KEY (`anonymousUser_id`) REFERENCES `AnonymousUser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CubeReview`
--

LOCK TABLES `CubeReview` WRITE;
/*!40000 ALTER TABLE `CubeReview` DISABLE KEYS */;
INSERT INTO `CubeReview` VALUES (1,'','focus on performers','opinionated people\r\n','open culture\r\nwork life balance','2011-12-11 18:16:03','Good place to learn','companyReview',1,1),(3,'','some advice','all cons','no pros','2012-01-06 02:01:56','Bad place to work','companyReview',3,10),(4,'','pay attention to top performers','very hierarchical organization','you can choose your domain','2012-01-06 14:47:33','pretty OK','companyReview',3,21),(5,'','some advice','some cons','some pros','2012-01-07 04:52:10','some headline','companyReview',4,1);
/*!40000 ALTER TABLE `CubeReview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IndustryType`
--

DROP TABLE IF EXISTS `IndustryType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IndustryType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `industry_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IndustryType`
--

LOCK TABLES `IndustryType` WRITE;
/*!40000 ALTER TABLE `IndustryType` DISABLE KEYS */;
INSERT INTO `IndustryType` VALUES (1,'2011-12-11 01:45:11','','Aerospace & Defense Companies'),(2,'2011-12-11 01:45:11','','Agriculture Companies'),(3,'2011-12-11 01:45:11','','Automotive & Transport Companies'),(4,'2011-12-11 01:45:11','','Banking Companies'),(5,'2011-12-11 01:45:11','','Beverages Companies'),(6,'2011-12-11 01:45:11','','Business Services Companies'),(7,'2011-12-11 01:45:11','','Charitable Organizations Companies'),(8,'2011-12-11 01:45:11','','Chemicals Companies'),(9,'2011-12-11 01:45:11','','Computer Hardware Companies'),(10,'2011-12-11 01:45:11','','Computer Services Companies'),(11,'2011-12-11 01:45:11','','Computer Software Companies'),(12,'2011-12-11 01:45:11','','Construction Companies'),(13,'2011-12-11 01:45:11','','Consumer Products Manufacturers Companies'),(14,'2011-12-11 01:45:11','','Consumer Services Companies'),(15,'2011-12-11 01:45:11','','Cultural Institutions Companies'),(16,'2011-12-11 01:45:11','','Education Companies'),(17,'2011-12-11 01:45:11','','Electronics Companies'),(18,'2011-12-11 01:45:11','','Energy & Utilities Companies'),(19,'2011-12-11 01:45:11','','Environmental Services & Equipment Companies'),(20,'2011-12-11 01:45:11','','Financial Services Companies'),(21,'2011-12-11 01:45:11','','Food Companies'),(22,'2011-12-11 01:45:11','','Foundations Companies'),(23,'2011-12-11 01:45:11','','Government Companies'),(24,'2011-12-11 01:45:11','','Health Care Companies'),(25,'2011-12-11 01:45:11','','Industrial Manufacturing Companies'),(26,'2011-12-11 01:45:11','','Insurance Companies'),(27,'2011-12-11 01:45:11','','Leisure Companies'),(28,'2011-12-11 01:45:11','','Media Companies'),(29,'2011-12-11 01:45:11','','Membership Organizations Companies'),(30,'2011-12-11 01:45:11','','Metals & Mining Companies'),(31,'2011-12-11 01:45:11','','Pharmaceuticals Companies'),(32,'2011-12-11 01:45:11','','Real Estate Companies'),(33,'2011-12-11 01:45:11','','Retail Companies'),(34,'2011-12-11 01:45:11','','Security Products & Services Companies'),(35,'2011-12-11 01:45:11','','Telecommunications Equipment Companies'),(36,'2011-12-11 01:45:11','','Telecommunications Services Companies'),(37,'2011-12-11 01:45:13','','Transportation Services Companies');
/*!40000 ALTER TABLE `IndustryType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registeredDate` datetime DEFAULT NULL,
  `userAlias` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Jasdeep','Madan','password','2011-12-11 18:11:25','JD','jasdeepm@gmail.com','theboss');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-01-08  2:06:16
