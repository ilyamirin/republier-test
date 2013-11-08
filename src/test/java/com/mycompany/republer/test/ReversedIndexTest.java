package com.mycompany.republer.test;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilyamirin
 */
public class ReversedIndexTest {
 
    @Test
    public void test() throws IOException {        
        Map<String, Set<String>> memory = new HashMap<String, Set<String>>();
        memory.put("first", Sets.newHashSet("/root/first", "/root/inner/first"));
        memory.put("second", Sets.newHashSet("/root/second"));
        memory.put("third", Sets.newHashSet("/tmp/wj3hnfgn23gfm28gm2"));
                
        ReversedIndex reversedIndex = ReversedIndex.newReversedIndex("index.txt");
        reversedIndex.dump(memory);
        
        Map<String, Set<String>> loadedMemory = new HashMap<String, Set<String>>();
        reversedIndex.load(loadedMemory);
        
        assertEquals(memory, loadedMemory);
    }
}
