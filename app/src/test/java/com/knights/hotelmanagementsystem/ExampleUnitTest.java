package com.knights.hotelmanagementsystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * IT20014018 Ranasingha R.M.V.W.
     **/
    @Test
    public void roomtotPriceCalculation() {
        String input1 = "Single Room";
        int input2 = 2;
        int input3 = 3;
        int output;
        int expected = 63000;
        double delta = .1;

        roombooking rm = new roombooking();
        output = rm.roomtotPriceCalculation(input1, input2, input3);

        assertEquals(expected, output, delta);

    }

    /**
     * IT20019518 Rajapakse L.M.D.M.
     **/
    @Test
    public void tabletotPriceCalculation() {
        String input1 = "For 2 people";
        int input2 = 2;
        int output;
        int expected = 4000;
        double delta = .1;

        tables tb = new tables();
        output = tb.totPriceCalculation(input1, input2);

        assertEquals(expected, output, delta);
    }

    /**
     * IT20018214 Nirod W.M.T
     **/
    @Test
    public void halltotPriceCalculation() {
        String input1 = "Indoor Wedding Hall";
        int input2 = 2;
        int output;
        int expected = 16000;
        double delta = .1;

        hallsbooking hall = new hallsbooking();
        output = hall.totPriceCalculation(input1, input2);

        assertEquals(expected, output, delta);
    }

    /**
     * IT20055158 Fernando P.A.T
     **/
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