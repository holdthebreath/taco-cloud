package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Taco;
import tacos.data.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DesignTacoController
 * @Description 设计taco控制器类
 * @Author hwd
 * @Date 2020/7/8 1:30 PM
 * @Version 1.0
 */
//lombok提供注解,在运行时在这个类中自动生成一个SLF4J(Simple Logging Facade for Java) Logger
// = private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
@Slf4j
@Controller
//指定控制器类处理等请求路径(处理路径以"/design"开头等请求)
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    //处理GET请求(Spring 4.3引入) = @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    //处理POST请求
    @PostMapping
    //@Valid表示对Taco对象进行校验,校验时机在绑定完表单数据,调用processDesign()之前.如果存在校验错误,则错误的细节将会捕捉到一个Errors对象中并传递给processDesign()
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
        System.out.println("Name: " + design.getName());
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);
        //redirect:前缀:说明这是重定向视图,重定向到相对路径/orders/current
        return "redirect:/orders/current";
    }
}
