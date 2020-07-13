package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

/**
 * @ClassName IngredientByIdConverter
 * @Description Ingredient转换器类
 * @Author hwd
 * @Date 2020/7/13 9:36 PM
 * @Version 1.0
 */
//SpringMVC数据绑定机制
//1：request到达SpringMVC框架时，框架会把request对象以及其映射的处理函数的参数实例，传递给 DataBinder；
//2：DataBinder调用ConversionService组件，对request对象传过来的参数进行数据类型转换、数据格式化操作，然后把数据绑定到处理函数的参数实例；
//3：然后调用Validator组件对绑定了值的参数对象进行数据校验，并生成数据绑定结果BindingResult。
//4：SpringMVC抽取BindingResult中的函数参数对象以及校验结果对象，赋值给请求处理函数的相应参数。
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
