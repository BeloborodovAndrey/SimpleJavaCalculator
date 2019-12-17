package com.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomeConverterTest {

    @Test
    void convertRomeDigitsResultTest() {
        RomeConverter converter = new RomeConverter();
        assertEquals(converter.convertRomeDigitsResult(100), "C");
        assertEquals(converter.convertRomeDigitsResult(99), "XCIX");
        assertEquals(converter.convertRomeDigitsResult(98), "XCVIII");
        assertEquals(converter.convertRomeDigitsResult(50), "L");
        assertEquals(converter.convertRomeDigitsResult(9), "IX");
        assertEquals(converter.convertRomeDigitsResult(45), "XLV");
        assertEquals(converter.convertRomeDigitsResult(43), "XLIII");
        assertEquals(converter.convertRomeDigitsResult(37), "XXXVII");
        assertEquals(converter.convertRomeDigitsResult(27), "XXVII");
        assertEquals(converter.convertRomeDigitsResult(17), "XVII");
    }
}