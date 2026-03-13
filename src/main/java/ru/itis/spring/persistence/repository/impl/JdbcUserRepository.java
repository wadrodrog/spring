package ru.itis.spring.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.spring.persistence.entity.UserEntity;
import ru.itis.spring.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserEntity user) {
        String query = "INSERT INTO account (name, birth_date) VALUES (?, ?)";
        jdbcTemplate.update(query, user.getName(), user.getBirthDate());
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        String query = "SELECT * FROM account WHERE id = ?";
        try {
            UserEntity entity = jdbcTemplate.queryForObject(query,
                    new BeanPropertyRowMapper<>(UserEntity.class), id);
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
        String query = "UPDATE account SET name = ?, birth_date = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getName(), user.getBirthDate(), user.getId());
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
