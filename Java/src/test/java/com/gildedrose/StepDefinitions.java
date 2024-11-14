package com.gildedrose;

import static org.junit.Assert.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StepDefinitions {
    private final List<Item> itemList = new ArrayList<>();
    private GildedRose app;

    List<Map<String, String>> orderDetails;
    @Given("^The items as item with sellin and quality:$")
    public void initial_sellin_is_and_quality_is(DataTable table) {
        orderDetails = table.asMaps(String.class, String.class);
        Item[] items = new Item[orderDetails.size()];

        for(int i=0; i < orderDetails.size(); i++){
            String name = orderDetails.get(i).get("item");
            int sellin = Integer.parseInt(orderDetails.get(i).get("sellin"));
            int quality = Integer.parseInt(orderDetails.get(i).get("quality"));

            Item item= new Item(name, sellin, quality);
            items[i] = item;
            itemList.add(item);
        }
        app = new GildedRose(items);
    }

    @When("I update the quality")
    public void i_update_the_quality() {
        app.updateGildedRose();
    }

    @Then("^I should get item updated response as:$")
    public void i_should_get_sellin_as_and_quality_as(DataTable table) {
        List<Map<String, String>> orderDetailsResult = table.asMaps(String.class, String.class);

        Map<String, Item> actualMap = Arrays.stream(app.items).collect(Collectors.toMap(x->x.name, Function.identity()));

        for (Map<String, String> resultRow : orderDetailsResult) {
            String name = resultRow.get("item");
            Item item = actualMap.get(name);

            int sellin = Integer.parseInt(resultRow.get("sellin"));
            int quality = Integer.parseInt(resultRow.get("quality"));

            assertEquals(name, item.name);
            assertEquals(quality, item.quality);
            assertEquals(sellin, item.sellIn);
        }
    }
}