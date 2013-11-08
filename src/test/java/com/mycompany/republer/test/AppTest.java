package com.mycompany.republer.test;

import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author ilyamirin
 */
public class AppTest {
    
    @Test
    public void test() throws IOException {
        App.main(new String[] {"build", "testdir"});
        App.main(new String[] {"find", "file"});
        App.main(new String[] {"find", "second"});
    }
    
}
