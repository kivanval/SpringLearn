package com.example.springlearn.data;

import com.example.springlearn.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from TACO_CLOUD.INGREDIENT",
                this::mapToRowIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from TACO_CLOUD.INGREDIENT where id = ?",
                this::mapToRowIngredient,
                id
        );
        return Optional.ofNullable(results.isEmpty() ? null : results.get(0));
    }

    private Ingredient mapToRowIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getLong("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into TACO_CLOUD.INGREDIENT (name, type) values (?, ?)",
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

}
