package com.gildedrose;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum PremiumItem {
    AGED_BRIE("Aged Brie"), SULFURAS("Sulfuras, Hand of Ragnaros"), BACKSTAGE_CONCERT_PASS("Backstage passes to a TAFKAL80ETC concert");
    private final String name;

    private final Map<String, PremiumItem> mapByName = Arrays.stream(values()).collect(Collectors.toMap(PremiumItem::getName, Function.identity()));

    PremiumItem(String name) {
        this.name = name;
    }
}
