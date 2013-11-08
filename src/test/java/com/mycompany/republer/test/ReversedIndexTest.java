package com.mycompany.republer.test;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilyamirin
 */
public class ReversedIndexTest {
 
    @Test
    public void test() throws IOException {        
        Map<String, List<String>> memory = new HashMap<String, List<String>>();
        memory.put("first", Lists.newArrayList("/root/first", "/root/inner/first"));
        memory.put("second", Lists.newArrayList("/root/second"));
        memory.put("third", Lists.newArrayList("/tmp/wj3hnfgn23gfm28gm2"));
                
        ReversedIndex reversedIndex = ReversedIndex.newReversedIndex();
        reversedIndex.dump(memory);
        
        Map<String, List<String>> loadedMemory = new HashMap<String, List<String>>();
        reversedIndex.load(loadedMemory);
        
        assertEquals(memory, loadedMemory);
    }
}
