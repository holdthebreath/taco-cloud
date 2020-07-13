package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @ClassName Ingredient
 * @Description taco配料领域类
 * @Author hwd
 * @Date 2020/7/8 1:16 PM
 * @Version 1.0
 */
//lombok生成所有缺失的方法(getter,setter等),还会生成final属性作为参数等构造器
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
