CREATE TABLE IF NOT EXISTS `game_difficulty` (
  `id_game_difficulty` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `tomatoe_count` TINYINT NOT NULL,
  PRIMARY KEY (`id_game_difficulty`));

CREATE TABLE IF NOT EXISTS `game` (
  `id_game` INT NOT NULL AUTO_INCREMENT,
  `id_game_difficulty` TINYINT NOT NULL,
  `player_name` VARCHAR(50) NOT NULL,
  `game_state` VARCHAR(255) NOT NULL,
  `moves` INT NOT NULL,
  `finished` TINYINT NOT NULL,
  PRIMARY KEY (`id_game`),
  CONSTRAINT `fk_game_difficulty_game`
    FOREIGN KEY (`id_game_difficulty`)
    REFERENCES `game_difficulty` (`id_game_difficulty`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);