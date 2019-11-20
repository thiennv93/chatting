-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-11-20 09:59:30.506

-- tables
-- Table: conversation
CREATE TABLE conversation (
                              conversation_id bigint NOT NULL AUTO_INCREMENT,
                              title varchar(40) NOT NULL,
                              creator_id bigint NOT NULL,
                              channel_id varchar(45),
                              status enum('active', 'inactive'),
                              created_user varchar(64) DEFAULT 0,
                              created_datetime datetime,
                              updated_user varchar(64),
                              updated_datetime datetime,
                              CONSTRAINT conversation_pk PRIMARY KEY (conversation_id)
) ENGINE InnoDB;

-- Table: message
CREATE TABLE message (
                         message_id bigint NOT NULL,
                         conversation_id bigint NOT NULL,
                         sender_id bigint NOT NULL,
                         message_type enum('text','image','video','audio') NOT NULL,
                         message varchar(512) NULL DEFAULT '''',
                         attachment_thumb_url varchar(255) NULL DEFAULT '',
                         attachment_url varchar(255) NULL DEFAULT '',
                         status enum('active', 'inactive'),
                         created_user varchar(64) DEFAULT 0,
                         created_datetime datetime,
                         updated_user varchar(64),
                         updated_datetime datetime,
                         CONSTRAINT messages_pk PRIMARY KEY (message_id)
) ENGINE InnoDB;

CREATE INDEX message_idx_1 ON message (conversation_id);

CREATE INDEX message_idx_2 ON message (sender_id);

-- Table: participant
CREATE TABLE participant (
                             participant_id bigint NOT NULL AUTO_INCREMENT,
                             conversation_id bigint NOT NULL,
                             users_id bigint NOT NULL,
                             type enum('single','group') NOT NULL,
                             status enum('active', 'inactive', 'block'),
                             created_user varchar(64) DEFAULT 0,
                             created_datetime datetime,
                             updated_user varchar(64),
                             updated_datetime datetime,
                             CONSTRAINT participants_pk PRIMARY KEY (participant_id)
) ENGINE InnoDB;

-- Table: user
CREATE TABLE user (
                      user_id bigint NOT NULL AUTO_INCREMENT,
                      username varchar(64) NOT NULL,
                      password varchar(255) NOT NULL,
                      first_name varchar(20) DEFAULT '''',
                      last_name varchar(20) DEFAULT '''',
                      middle_name varchar(20) DEFAULT '''',
                      birthday date DEFAULT 0,
                      email varchar(255),
                      phone varchar(16),
                      status enum('active', 'inactive'),
                      created_user varchar(64) DEFAULT 0,
                      created_datetime datetime,
                      updated_user varchar(64),
                      updated_datetime datetime,
                      CONSTRAINT users_pk PRIMARY KEY (user_id)
) ENGINE InnoDB;

CREATE  UNIQUE INDEX phone_UNIQUE ON user (phone);

CREATE  UNIQUE INDEX email_UNIQUE ON user (email);

-- Table: user_contact
CREATE TABLE user_contact (
                              user_id bigint NOT NULL,
                              contact_id bigint NOT NULL,
                              first_name varchar(45) DEFAULT '''',
                              last_name varchar(45) DEFAULT '''',
                              status enum('active', 'inactive'),
                              created_user varchar(64) DEFAULT 0,
                              created_datetime datetime,
                              updated_user varchar(64),
                              updated_datetime datetime,
                              CONSTRAINT user_contact_pk PRIMARY KEY (user_id,contact_id)
) ENGINE InnoDB;

-- foreign keys
-- Reference: message_conversation (table: message)
ALTER TABLE message ADD CONSTRAINT message_conversation FOREIGN KEY message_conversation (conversation_id)
    REFERENCES conversation (conversation_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Reference: participant_conversation (table: participant)
ALTER TABLE participant ADD CONSTRAINT participant_conversation FOREIGN KEY participant_conversation (conversation_id)
    REFERENCES conversation (conversation_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Reference: participant_user (table: participant)
ALTER TABLE participant ADD CONSTRAINT participant_user FOREIGN KEY participant_user (users_id)
    REFERENCES user (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Reference: user_contact_user (table: user_contact)
ALTER TABLE user_contact ADD CONSTRAINT user_contact_user FOREIGN KEY user_contact_user (user_id)
    REFERENCES user (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Reference: user_contact_user_1 (table: user_contact)
ALTER TABLE user_contact ADD CONSTRAINT user_contact_user_1 FOREIGN KEY user_contact_user_1 (contact_id)
    REFERENCES user (user_id);

-- End of file.
