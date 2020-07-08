package tacos;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Taco
 * @Description Taco领域对象
 * @Author hwd
 * @Date 2020/7/8 1:53 PM
 * @Version 1.0
 */
@Data
public class Taco {
    private String name;
    private List<String> ingredients;
}
