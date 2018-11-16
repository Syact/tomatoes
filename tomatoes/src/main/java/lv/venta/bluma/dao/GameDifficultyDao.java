package lv.venta.bluma.dao;

import lv.venta.bluma.domain.GameDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDifficultyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GameDifficulty> getAllDifficulties() {
//        query atgriezh objektu sarakstu
        return jdbcTemplate.query("SELECT id_game_difficulty, name, tomatoe_count " +
                        "FROM game_difficulty " +
                        "ORDER BY tomatoe_count ASC ",
                new Object[]{},
                (resultSet, i) -> new GameDifficulty(
                        resultSet.getByte(1),
                        resultSet.getString(2),
                        resultSet.getByte(3)
                ));
    }

    public GameDifficulty getDifficulty(byte gameDifficultyId) {
//        queryForObject atgriezh vienu objektu
        return jdbcTemplate.queryForObject("SELECT id_game_difficulty, name, tomatoe_count " +
                "FROM game_difficulty " +
                "WHERE id_game_difficulty = ? ",
                new Object[]{gameDifficultyId},
                (resultSet, i) -> new GameDifficulty(
                        resultSet.getByte(1),
                        resultSet.getString(2),
                        resultSet.getByte(3)
                ));

    }
}
