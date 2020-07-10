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
//构造型注解,Spring组件扫描会发现，将其初始化为Spring应用上下文中的bean
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbc;

    //    创建JdbcIngredientRepository bean时,通过@Autowired标注的构造器将JdbcTemplate注入
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
            resultSet.getString("name"),
            Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }

    @Override
    public Iterable<Ingredient> findAll() {
//        query()接收执行的sql以及Spring RowMapper的一个实现(用以将结果集中的每行数据映射为一个对象)
//        query()还能以最终参数形式接收查询所需的任意参数
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
//        queryForObject()只返回一个对象,而不是List
        return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
//        update()执行向数据库写入或更新数据的查询语句
        jdbc.update("insert into Ingredient (id, name, type) values(?, ?, ?)", ingredient.getId(), ingredient.getName(), ingredient.getType());
        return ingredient;
    }
}
