package tacos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName Order
 * @Description 订单领域类
 * @Author hwd
 * @Date 2020/7/8 3:14 PM
 * @Version 1.0
 */
@Data
public class Order {
    @NotBlank(message = "Name is required")
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
