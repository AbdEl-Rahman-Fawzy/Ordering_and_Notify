package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simple"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compound")
})
public class SimpleOrder extends Order {
    public SimpleOrder(int x , String date, Cart cart) {
        super(x, date, cart);
    }

    @Override
    public void calculate_total_cost() {
        setTotalCost(getCart().getTotal_cost());
    }
}
