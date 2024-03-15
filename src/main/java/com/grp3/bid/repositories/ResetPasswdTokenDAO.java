package com.grp3.bid.repositories;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.ResetPasswdToken;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.ResetPasswdTokenServiceInterface;
import com.grp3.bid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ResetPasswdTokenDAO implements ResetPasswdTokenDAOInterface {


    private final String RESET_PASSWD_TOKEN = "INSERT INTO RESET_PASSWD_TOKEN (id_user_app, token, expiration, is_already_used) VALUES (:id_user_app, :token, :expiration, :is_already_used)";
    private final String GET_BY_TOKEN = "SELECT TOP 1 * FROM RESET_PASSWD_TOKEN WHERE is_already_used = false AND expiration > CURRENT_TIMESTAMP() AND token = :token ORDER BY expiration DESC";
    private final String UPDATE_IS_ALREADY_USED = "UPDATE RESET_PASSWD_TOKEN SET is_already_used = :is_already_used WHERE id_reset_passwd_token = :id_reset_passwd_token;";

    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO userDAO;

    public ResetPasswdTokenDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertResetPasswdToken(ResetPasswdToken resetPasswdToken) {
        KeyHolder keyHolder =  new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_user_app", resetPasswdToken.getUser().getId());
        sqlParameterSource.addValue("token", resetPasswdToken.getToken());
        sqlParameterSource.addValue("expiration", resetPasswdToken.getExpiration());
        sqlParameterSource.addValue("is_already_used", resetPasswdToken.isAlreadyUsed());
        return jdbcTemplate.update(RESET_PASSWD_TOKEN, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public ResetPasswdToken getResetPasswdTokenByToken(String token) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("token", token);
        return jdbcTemplate.queryForObject(GET_BY_TOKEN, sqlParameterSource, new ResetPasswdTokenRowMapper());
    }

    @Override
    public boolean updateAlreadyUsed(ResetPasswdToken resetPasswdToken, boolean value) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_reset_passwd_token", resetPasswdToken.getId());
        sqlParameterSource.addValue("is_already_used", value);
        return jdbcTemplate.update(UPDATE_IS_ALREADY_USED, sqlParameterSource) != 0;
    }

    public class ResetPasswdTokenRowMapper implements RowMapper<ResetPasswdToken> {

        @Override
        public ResetPasswdToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResetPasswdToken r = new ResetPasswdToken();
            r.setId(rs.getInt("id_reset_passwd_token"));
            r.setUser(userDAO.getUserById(rs.getInt("id_user_app")));
            r.setToken(rs.getString("token"));
            r.setExpiration(rs.getTimestamp("expiration").toLocalDateTime());
            r.setAlreadyUsed(rs.getBoolean("is_already_used"));
            return r;
        }
    }
}
