package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    void testSpyOnList() {
        List<String> realList = new ArrayList<>();

        List<String> spyList = spy(realList);

        spyList.add("Spring");
        spyList.add("Boot");

        verify(spyList, times(2)).add(anyString());

        assertEquals(2, spyList.size());
        assertEquals("Spring", spyList.get(0));
        assertEquals("Boot", spyList.get(1));
    }
}