package com.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OperationsParserTest {

    @Test
    void parsingStringTest() {
        OperationsParser parser = new OperationsParser();
        parser.parsingString("1+1");
        Assertions.assertEquals(parser.getArgs().getItem(0), "1");
        Assertions.assertEquals(parser.getArgs().getItem(1), "1");
        Assertions.assertEquals(parser.getOperand(), "+");
        parser.parsingString("X-V");
        Assertions.assertEquals(parser.args.getItem(0), "10");
        Assertions.assertEquals(parser.args.getItem(1), "5");
        Assertions.assertEquals(parser.getOperand(), "-");
    }

    @Test
    void getOperandIndexTest() {
        OperationsParser parser = new OperationsParser();
        Assertions.assertEquals(parser.getOperandIndex("2+5".toCharArray()),1);
        Assertions.assertEquals(parser.getOperandIndex("10+6".toCharArray()),2);
        Assertions.assertEquals(parser.getOperandIndex("VIII+I".toCharArray()),4);
    }
}