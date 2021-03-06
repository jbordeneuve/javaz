package org.javaz;

import org.junit.*;

import java.util.*;

import static org.javaz.Option.*;
import static org.junit.Assert.assertEquals;

public class OptionTest {

    @Test
    public void testMap() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("a", "5");
        param.put("b", "true");
        param.put("c", "-3");

        assertEquals(5, readPositiveIntParam(param, "a"));
        assertEquals(0, readPositiveIntParam(param, "b"));
        assertEquals(0, readPositiveIntParam(param, "c"));
        assertEquals(0, readPositiveIntParam(param, "d"));
    }

    public int readPositiveIntParam_OLD(Map<String, String> params, String name) {
        String val = params.get(name);
        if (val == null) {
            return 0;
        }
        try {
            int i = Integer.parseInt(val);
            return i > 0 ? i : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int readPositiveIntParam(Map<String, String> params, String name) {
        return option(params.get(name))
                .flatMap(OptionTest::s2i)
                .filter(n -> n > 0).getOrElse(0);
    }

    public static Option<Integer> s2i(String s) {
        try {
            return some(Integer.parseInt(s));
        } catch (Exception e) {
            return none();
        }
    }
}
























/*
    public int readPositiveIntParam(Map<String, String> params, String name) {
        return option(params.get(name))
                .flatMap(OptionTest::stringToInt)
                .filter(i -> i > 0)
                .getOrElse(0);
    }

    public static Option<Integer> stringToInt(String s) {
        try {
            return some(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return none();
        }
    }
*/
