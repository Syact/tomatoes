package lv.venta.bluma.dao;

import lv.venta.bluma.domain.Game;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameDao {

    final static Logger logger = Logger.getLogger(GameDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Game createNewGame(String playerName, byte gameDifficultyId, String gameState) {
        final String sql = "INSERT INTO game (id_game_difficulty," +
                "player_name, game_state, moves, finished) " +
                "VALUES (?, ?, ?, ?, ?) ";

        final KeyHolder keyHolder = new GeneratedKeyHolder(); // PK holder
        logger.error( sql+ " " +gameDifficultyId +"  "+ playerName +" "+ gameState);

        jdbcTemplate.update(connection -> {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setByte(1, gameDifficultyId);
            statement.setString(2, playerName);
            statement.setString(3, gameState);
            statement.setInt(4, 0);
            statement.setBoolean(5, false);
            return statement;
        }, keyHolder);

        logger.error("game id: " +  getGameById(keyHolder.getKey().intValue()).toString());
        return getGameById(keyHolder.getKey().intValue());
    }

    public Game getGameById(int gameId) { // optional - ja neeksiste
        try {
            return jdbcTemplate.queryForObject("SELECT id_game_difficulty, player_name, game_state, " +
                            "moves, finished " +
                            "FROM game " +
                            "WHERE id_game = ?",
                    new Object[]{gameId},
                    (resultSet, i) -> new Game(
                            gameId,
                            resultSet.getByte(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getBoolean(5)
                    ));
        }
        catch (Exception e){
            return null;
        }
    }

    public List<Game> getBestGameResults(byte gameDifficultyId) {
        return jdbcTemplate.query("SELECT id_game, player_name, game_state, moves " +
                        "FROM game " +
                        "WHERE id_game_difficulty = ? AND finished = 1 " +
                        "ORDER BY moves ASC " +
                        "LIMIT 10 ",
                new Object[]{gameDifficultyId},
                (resultSet, i) -> new Game(
                        resultSet.getInt(1),
                        gameDifficultyId,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        true
                ));
    }

    public void updateGame(Game game) {
        jdbcTemplate.update("UPDATE game " +
                "SET id_game_difficulty = ?, player_name = ?, game_state = ?, moves = ?, finished = ? " +
                "WHERE id_game = ? ",
                game.getGameDifficultyId(), game.getPlayerName(), game.getGameState(), game.getMoves(),
                game.isFinished(), game.getGameId());
    }
}