package com.course.kafkaproducer.service;

import com.course.kafkaproducer.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {
    private static final Map<String , Commodity> COMMODITY_BASE = new HashMap<>();
    private static final String COPPER = "copper";
    private static final String GOLD= "gold";

    private static final double MAX_ADJUSTMENT = 1.05;
    private static final double MIN_ADJUSTMENT = .95;

    static {
        var timestamp = System.currentTimeMillis();

        COMMODITY_BASE.put(GOLD,new Commodity(GOLD,1_407.25,"ounce",timestamp));
        COMMODITY_BASE.put(COPPER,new Commodity(COPPER,5_900.57,"tonne",timestamp));
    }

    public Commodity createDummyCommodity(String name ){
        if (!COMMODITY_BASE.containsKey(name)){
            throw new IllegalArgumentException("Invalid Commodity name: " + name);
        }
        var commodity = COMMODITY_BASE.get(name);
        var basePrice = commodity.getPrice();
        var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT , MAX_ADJUSTMENT);

        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());
        return commodity;
    }

    public List<Commodity> createDummiesCommodities()
    {
        var result = new ArrayList<Commodity>();
        COMMODITY_BASE.keySet().forEach(c -> result.add(createDummyCommodity(c)));

        return result;
    }

}
