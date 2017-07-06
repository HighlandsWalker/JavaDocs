package com.java_8_training.problems.collectors;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java_8_training.problems.collectors.Dish.CaloricLevel;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static junit.framework.Assert.assertEquals;

@Ignore
public class PartitioningAndGroupingTest {

    // See: Dish.menu.stream()
    // Partition the menu with dishes > 380
    @Test
    public void partitionDishes() {

        Map<Boolean, List<Dish>> partitionedDishes = new HashMap<>();

        //TODO #C1

        partitionedDishes = Dish.menu.stream()
                .collect(partitioningBy(elem -> elem.getCalories() > 380));

        assertEquals(2, partitionedDishes.get(false).size());
        assertEquals(7, partitionedDishes.get(true).size());
    }

    // Group the list of dishes by caloric level
    @Test
    public void groupDishes() {
        Map<CaloricLevel, List<Dish>> groupedDishes = new HashMap<>();

        //TODO #C2

//        groupedDishes = Dish.menu.stream()
//                .collect(groupingBy(elem ->
//                        (elem.getCalories() > 0 && elem.getCalories() <= 400) ? CaloricLevel.DIET :
//                                (elem.getCalories() > 400 && elem.getCalories() <= 700 ? CaloricLevel.NORMAL : CaloricLevel.FAT )));
//
        groupedDishes = Dish.menu.stream()
                .collect(groupingBy(Dish::getCaloricLevel));

        assertEquals(4, groupedDishes.get(CaloricLevel.DIET).size());
        assertEquals(4, groupedDishes.get(CaloricLevel.NORMAL).size());
        assertEquals(1, groupedDishes.get(CaloricLevel.FAT).size());
    }

    @Test
    public void groupCounting(){
        Map<Dish.Type, Long> groupedDishes = new HashMap<>();
        //TODO #C7

        groupedDishes = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 140)
                .collect(groupingBy(Dish::getType, counting()));

        assertEquals(3, groupedDishes.get(Dish.Type.MEAT).intValue());
        assertEquals(2, groupedDishes.get(Dish.Type.FISH).intValue());
        assertEquals(3, groupedDishes.get(Dish.Type.OTHER).intValue());
    }

}
