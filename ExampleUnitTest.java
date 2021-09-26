package com.example.food4;

import org.junit.Test;

import static org.junit.Assert.*;


//IT20055158
public class ExampleUnitTest {
    @Test

    public void deliverytotPriceCalculation() {
        String input1 = "Rice And Curry";
        int input2 = 2;
        int output;
        int expected = 500;
        double delta = .1;

        deliveryorder delivery = new deliveryorder();
        output = delivery.totPriceCalculation(input1, input2);

        assertEquals(expected, output, delta);
    }

}