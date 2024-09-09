DROP TABLE IF EXISTS sys_user;
CREATE TABLE `sys_user`
(
    `ID`             bigint NOT NULL AUTO_INCREMENT,
    `LOGIN`       varchar(64)   DEFAULT NULL,
    `PASSWORD`       varchar(64)   DEFAULT NULL,
    `PHONE_NUM`      varchar(20)   DEFAULT NULL,
    `EMAIL`          varchar(120)  DEFAULT NULL,
    `LAST_NAME`      varchar(32)   DEFAULT NULL,
    `FIRST_NAME`     varchar(32)   DEFAULT NULL,
    `CREATE_TIME`    datetime      DEFAULT NULL,
    `CREATE_BY`      varchar(30)   DEFAULT NULL,
    `UPDATE_TIME`    datetime      DEFAULT NULL,
    `UPDATE_BY`      varchar(30)   DEFAULT NULL,
    `DELETED`        bit(1)        DEFAULT FALSE,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `sys_user_ID_uindex` (`ID`),
    UNIQUE KEY `sys_user_EMAIL_uindex` (`EMAIL`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
;