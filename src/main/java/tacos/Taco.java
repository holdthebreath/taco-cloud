package tacos;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Size(min = 3, message = "Name must be least 3 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}
