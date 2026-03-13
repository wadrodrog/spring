package ru.itis.spring.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(UserEntity user) {
        String query = "INSERT INTO account (name, birth_date) VALUES (:name, :birth_date)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("birth_date", user.getBirthDate());
        jdbcTemplate.update(query, params);
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        String query = "SELECT * FROM account WHERE id = :id";
        try {
            UserEntity entity = jdbcTemplate.queryForObject(query,
                    Collections.singletonMap("id", id),
                    userMapper);
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String query = "SELECT * FROM account";
        return jdbcTemplate.query(query, userMapper);
    }

    @Override
    public void update(UserEntity user) {
        String query = "UPDATE account SET name = :name, birth_date = :birth_date WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("birth_date", user.getBirthDate())
                .addValue("id", user.getId());
        jdbcTemplate.update(query, params);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void deleteAll() {

    }

    private final RowMapper<UserEntity> userMapper = (rs, rowNumber) -> {
        UserEntity entity = new UserEntity();
        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        entity.setBirthDate(LocalDate.parse(rs.getString("birth_date")));
        return entity;
    };
}
