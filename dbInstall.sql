-- DROP TABLE IF EXISTS `polish`;
-- polish: table
CREATE TABLE `polish` (
  `id`            bigint(20)                                    NOT NULL AUTO_INCREMENT,
  `word`          varchar(30) COLLATE utf8mb4_unicode_ci        NOT NULL,
  `add_date`      datetime                                      NOT NULL,
  `adder_ip`      int(11) unsigned                              NOT NULL DEFAULT '0',
  `accepted`      enum ('yes', 'no') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no',
  `reject_reason` varchar(255) COLLATE utf8mb4_unicode_ci                DEFAULT NULL,
  `review_date`   datetime                                               DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_pl_0900_as_cs;

