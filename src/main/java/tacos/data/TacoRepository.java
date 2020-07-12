package tacos.data;

import tacos.Taco;

/**
 * @ClassName TacoRepository
 * @Description Taco类数据库接口类
 * @Author hwd
 * @Date 2020/7/11 11:21 AM
 * @Version 1.0
 */
public interface TacoRepository {
    Taco save(Taco design);
}
