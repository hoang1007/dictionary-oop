package com.gryffindor.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gryffindor.backend.utils.TextUtils;

import org.junit.jupiter.api.Test;

public class TestTextUtils {
    @Test
    void testFormat() {
        assertEquals("head and shoulder and arm", TextUtils.format("head-and_shoulder\nand\narm"));
    }
}
