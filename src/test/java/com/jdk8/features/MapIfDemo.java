package com.jdk8.features;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author alan.chen
 * @date 2020/8/11 5:32 PM
 */
public class MapIfDemo {


    private static Map<String, Function<BigDecimal, BigDecimal>> map = new HashMap<>();

    private static Map<String, Consumer<Integer>> map2 = new HashMap<>();

    static {
        map.put("common", (origin) -> origin.multiply(new BigDecimal(0.9)));
        map.put("vip", (origin) -> origin.multiply(new BigDecimal(0.8)));
        map.put("svip", (origin) -> origin.multiply(new BigDecimal(0.5)));
    }

    static {
        map2.put("common", (id) -> {
            System.out.println("Common更新数据id=" + id);
        });
        map2.put("vip", (id) -> {
            System.out.println("Vip更新数据id=" + id);
        });
        map2.put("svip", (id) -> {
            System.out.println("Svip更新数据id=" + id);
        });
    }


    @Test
    public void test() {
        String common = "common";
        String vip = "vip";
        String svip = "svip";
        BigDecimal originPrice = new BigDecimal(100);

        BigDecimal price1 = calculePrice(common, originPrice);
        BigDecimal price2 = calculePrice(vip, originPrice);
        BigDecimal price3 = calculePrice(svip, originPrice);

        System.out.println("普通用户打折后价格：" + price1);
        System.out.println("Vip打折后价格：" + price2);
        System.out.println("Svip打折后价格：" + price3);

    }

    @Test
    public void test2() {
        String common = "common";
        String vip = "vip";
        String svip = "svip";
        int commonId = 1;
        int vipId = 2;
        int svipId = 3;

        update(common, commonId);
        update(vip, vipId);
        update(svip, svipId);
    }

    private BigDecimal calculePrice(String level, BigDecimal originPrice) {
       return map.get(level).apply(originPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private void update(String level, int id) {
        map2.get(level).accept(id);
    }

}
