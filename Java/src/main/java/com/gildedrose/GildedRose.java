package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateGildedRose() {
        Arrays.stream(items).forEach(this::updateGildedRoseQuality);
    }

    private void updateGildedRoseQuality(Item item) {
        if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            // "Sulfuras is a legendary item, never has to be sold or decreases its value from 80"
            return;
        }

        item.sellIn = item.sellIn - 1;

        switch (item.name) {
            case AGED_BRIE:
                updateIncreasedQuality(item);
                if (item.sellIn < 0) updateIncreasedQuality(item);
                break;
            case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                updateIncreasedQuality(item);
                if (item.sellIn + 1 < 11) {
                    updateIncreasedQuality(item);
                }
                if (item.sellIn + 1 < 6) {
                    updateIncreasedQuality(item);
                }
                if (item.sellIn < 0) item.quality = 0;
                break;
            default:
                updateDecreasedQuality(item);
                if (item.sellIn < 0) updateDecreasedQuality(item);
                break;
        }
    }

    private static void updateDecreasedQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private static void updateIncreasedQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
