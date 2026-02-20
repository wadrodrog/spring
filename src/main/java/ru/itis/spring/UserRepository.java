package ru.itis.spring;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {
    private Connection connection = null;

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return getConnection().prepareStatement(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Unable to get prepared statement: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            String DATABASE_URL = "jdbc:postgresql://localhost:5432/spring";
            String DATABASE_USER = "postgres";
            String DATABASE_PASSWORD = "postgres";
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        }
        return connection;
    }

    public void create(String name) {
        String query = "insert into users (name) values (?);";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query: " + e.getMessage());
        }
    }

    public UserEntity read(long userId) {
        String query = "select name from users where user_id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new UserEntity(userId, resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query: " + e.getMessage());
        }
    }

    public UserEntity read(String name) {
        String query = "select user_id from users where name = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new UserEntity(resultSet.getLong("user_id"), name);
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query: " + e.getMessage());
        }
    }

    public UserEntity update(long userId, String name) {
        String query = "update users set name = ? where user_id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, userId);
            preparedStatement.execute();
            return new UserEntity(userId, name);
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query: " + e.getMessage());
        }
    }

    public void delete(long userId) {
        String query = "delete from users where user_id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query: " + e.getMessage());
        }
    }
}
