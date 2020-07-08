package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName JdbcIngredientRepository
 * @Description IngredientRepository接口实现类
 * @Author hwd
 * @Date 2020/7/8 9:43 PM
 * @Version 1.0
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository{
    private JdbcTemplate jdbc;
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
            resultSet.getString("name"),
            Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }

    @Override
    public Iterable<Ingredient> findAll(){
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id){
        return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", this::mapRowToIngredient);
    }
}
