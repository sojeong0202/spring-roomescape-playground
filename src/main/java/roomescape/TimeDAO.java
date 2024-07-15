package roomescape;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TimeDAO {

    private final JdbcTemplate jdbcTemplate;

    public TimeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Time time) {
        String sql = "INSERT INTO time (time) VALUES ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(
                    sql,
                    new String[]{"id"});
            ps.setString(1, time.getTime());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Time> findAllTimes() {
        String sql = "SELECT id, time FROM time";

        return jdbcTemplate.query(sql, actorRowMapper);
    }

    public void delete(Long id) {

        jdbcTemplate.update("DELETE FROM time WHERE id = ?", id);
    }

    private final RowMapper<Time> actorRowMapper = (resultSet, rowNum) -> {
        Time time = new Time(
                resultSet.getLong("id"),
                resultSet.getString("time")
        );
        return time;
    };
}
