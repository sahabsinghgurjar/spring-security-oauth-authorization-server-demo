
CREATE TABLE `authority` (
  `ID` varchar(200) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `authority` (`ID`, `NAME`) VALUES ('1','USER_CREATE');


CREATE TABLE `oauth_access_token` (
  `token_id` varchar(200) NOT NULL,
  `token` blob,
  `authentication_id` varchar(500) DEFAULT NULL,
  `user_name` varchar(500) DEFAULT NULL,
  `client_id` varchar(500) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `oauth_client_details` (
  `CLIENT_ID` varchar(200) NOT NULL,
  `RESOURCE_IDS` varchar(200) DEFAULT NULL,
  `CLIENT_SECRET` varchar(200) DEFAULT NULL,
  `SCOPE` varchar(200) DEFAULT NULL,
  `AUTHORIZED_GRANT_TYPES` varchar(200) DEFAULT NULL,
  `AUTHORITIES` varchar(200) DEFAULT NULL,
  `ACCESS_TOKEN_VALIDITY` varchar(200) DEFAULT NULL,
  `REFRESH_TOKEN_VALIDITY` varchar(200) DEFAULT NULL,
  `oauth_client_detailscol` varchar(45) DEFAULT NULL,
  `web_server_redirect_uri` varchar(200) DEFAULT NULL,
  `additional_information` varchar(200) DEFAULT NULL,
  `autoapprove` tinyint DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `oauth_client_details` (`CLIENT_ID`, `RESOURCE_IDS`, `CLIENT_SECRET`, `SCOPE`, `AUTHORIZED_GRANT_TYPES`, `AUTHORITIES`, `ACCESS_TOKEN_VALIDITY`, `REFRESH_TOKEN_VALIDITY`, `oauth_client_detailscol`, `web_server_redirect_uri`, `additional_information`, `autoapprove`) VALUES ('user-app','resource-server-rest-api','Gdu.a5hvfY4W','read','password,authorization_code,refresh_token,implicit','USER','10800','2592000',NULL,NULL,NULL,NULL);



CREATE TABLE `oauth_code` (
  `code` varchar(200) NOT NULL,
  `authentication` blob,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(200) NOT NULL,
  `token` blob,
  `authentication` blob,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_` (
  `ID` varchar(200) NOT NULL,
  `USER_NAME` varchar(200) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `ACCOUNT_EXPIRED` tinyint DEFAULT NULL,
  `ACCOUNT_LOCKED` tinyint DEFAULT NULL,
  `CREDENTIALS_EXPIRED` tinyint DEFAULT NULL,
  `ENABLED` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_` (`ID`, `USER_NAME`, `PASSWORD`, `ACCOUNT_EXPIRED`, `ACCOUNT_LOCKED`, `CREDENTIALS_EXPIRED`, `ENABLED`) VALUES ('1','admin','zge4j035ha',0,0,0,1);


CREATE TABLE `users_authorities` (
  `USER_ID` varchar(200) DEFAULT NULL,
  `AUTHORITY_ID` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `users_authorities` (`USER_ID`, `AUTHORITY_ID`) VALUES ('1','1');
