
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `role_authority` (
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `fk_role_has_authority_authority1_idx` (`authority_id`),
  KEY `fk_role_has_authority_role_idx` (`role_id`),
  CONSTRAINT `fk_role_has_authority_authority1` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_authority_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL,
  `parent_shop_id` int(11) DEFAULT NULL,
  `shop_code` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `status` varchar(256) DEFAULT NULL,
  `shop_type` varchar(256) DEFAULT NULL,
  `decription` varchar(256) DEFAULT NULL,
  `shop_path` varchar(256) DEFAULT NULL,
  `province` varchar(256) DEFAULT NULL,
  `district` varchar(256) DEFAULT NULL,
  `precinct` varchar(256) DEFAULT NULL,
  `area_code` varchar(256) DEFAULT NULL,
  `street_block` varchar(256) DEFAULT NULL,
  `street` varchar(256) DEFAULT NULL,
  `tel` varchar(256) DEFAULT NULL,
  `fax` varchar(256) DEFAULT NULL,
  `channel_type_id` int(11) DEFAULT NULL,
  `email` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `staff_id` int(11) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `staff_code` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `status` varchar(256) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `id_issue_place` varchar(256) DEFAULT NULL,
  `id_issue_date` datetime DEFAULT NULL,
  `tel` varchar(256) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `channel_type_id` int(11) DEFAULT NULL,
  `email` varchar(4096) DEFAULT NULL,
  `id_no` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `failed_login_attempt_count` int(2) NOT NULL,
  `last_failed_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_role_has_user_user1_idx` (`user_id`),
  KEY `fk_role_has_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
