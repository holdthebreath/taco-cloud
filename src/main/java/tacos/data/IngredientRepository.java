package tacos.data;

import tacos.Ingredient;

/**
 * @ClassName IngredientRepository
 * @Description JDBC repository
 * @Author hwd
 * @Date 2020/7/8 9:40 PM
 * @Version 1.0
 */
public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
