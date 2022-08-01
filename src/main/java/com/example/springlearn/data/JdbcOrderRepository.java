package com.example.springlearn.data;

import com.example.springlearn.domain.Ingredient;
import com.example.springlearn.domain.Taco;
import com.example.springlearn.domain.TacoOrder;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into TACO_CLOUD.Taco_Order " +
                        "(DELIVERY_NAME, DELIVERY_STREET, DELIVERY_CITY, DELIVERY_STATE," +
                        " DELIVERY_ZIP, CC_NUMBER, CC_EXPIRATION, CC_CVV, PLACED_AT) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, taco);
        }
        return null;
    }

    private long saveTaco(Long orderId, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into TACO_CLOUD.TACO " +
                        "(NAME, TACO_ORDER_ID, CREATED_AT) " +
                        "values (?, ?, ?)",
                Types.VARCHAR, Types.BIGINT, Types.TIMESTAMP);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        orderId,
                        taco.getCreatedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long tacoId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        saveIngredientRefs(tacoId, taco.getIngredients());

        taco.setId(tacoId);
        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        ingredients.forEach(i -> jdbcTemplate.update(
                "insert into TACO_CLOUD.TACO_INGREDIENT " +
                        "(INGREDIENT_ID, TACO_ID) " +
                        "values (?, ?)",
                i.getName(), tacoId));

    }
}
