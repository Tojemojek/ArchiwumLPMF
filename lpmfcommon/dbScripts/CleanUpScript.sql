# script to recreate table

DROP TABLE IF EXISTS `songs_in_list`;
DROP TABLE IF EXISTS `song_has_artist`;

DROP TABLE IF EXISTS `song`;
DROP TABLE IF EXISTS `movie`;
DROP TABLE IF EXISTS `artist`;

DROP TABLE IF EXISTS `list_info`;
DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
`next_val` bigint(20) DEFAULT NULL
)
DEFAULT CHARSET = utf8
COLLATE = utf8_polish_ci;

INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1);

CREATE TABLE `list_info` (
  `no_of_list`   int(11) NOT NULL,
  `date_of_list` date DEFAULT NULL,
  PRIMARY KEY (`no_of_list`)
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;


CREATE TABLE `artist` (
  `id`                            bigint(20) NOT NULL,
  `full_name`                     varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `first_time_in_list_no_of_list` int(11)                             DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_artist_first_time` FOREIGN KEY (`first_time_in_list_no_of_list`) REFERENCES `list_info` (`no_of_list`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;

CREATE TABLE `movie` (
  `id`                            bigint(20) NOT NULL,
  `title`                         varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `first_time_in_list_no_of_list` int(11)                             DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_movie_first_time` FOREIGN KEY (`first_time_in_list_no_of_list`) REFERENCES `list_info` (`no_of_list`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;

CREATE TABLE `song` (
  `id`                            bigint(20) NOT NULL,
  `cover_link`                    varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `has_duplicates`                bit(1)                              DEFAULT NULL,
  `title`                         varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `first_time_in_list_no_of_list` int(11)                             DEFAULT NULL,
  `movie_id`                      bigint(20)                          DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_song_first_time` FOREIGN KEY (`first_time_in_list_no_of_list`) REFERENCES `list_info` (`no_of_list`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;

CREATE TABLE `song_has_artist` (
  `song_id`   bigint(20) NOT NULL,
  `artist_id` bigint(20) NOT NULL,
  CONSTRAINT `fk_sha_song_id` FOREIGN KEY (`song_id`) REFERENCES `song` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sha_movie_id` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;

CREATE TABLE `songs_in_list` (
  `id`           bigint(20) NOT NULL,
  `list_info_no_of_list` int(11),
  `pos`          int(11)    DEFAULT NULL,
  `song_id`      bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sil_song_id` FOREIGN KEY (`song_id`) REFERENCES `song` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sil_list_info` FOREIGN KEY (`list_info_no_of_list`) REFERENCES `list_info` (`no_of_list`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_polish_ci;
