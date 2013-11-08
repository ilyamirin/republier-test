package com.mycompany.republer.test;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ilyamirin
 */
@Slf4j
public class CrawlerTest {

    @Test
    public void memorizeTest() {
        Crawler crawler = new Crawler();
        crawler.memorize("/path/path1", "name1");
        crawler.memorize("/path/path1/path2", "name2");
        crawler.memorize("/path/", "name3");
        crawler.memorize("/path/path1", "name3");

        assertEquals(3, crawler.memory.size());

        assertTrue(crawler.memory.get("name3").contains("/path/"));
        assertTrue(crawler.memory.get("name3").contains("/path/path1"));

        assertTrue(crawler.memory.get("name1").contains("/path/path1"));
        assertTrue(crawler.memory.get("name2").contains("/path/path1/path2"));
    }

    @Test
    public void crawlingTest() throws IOException {
        File root = new File("testdir");
        if (!root.exists()) {
            root.mkdir();
        }
        File rootFile = new File("testdir/file");
        if (!rootFile.exists()) {
            rootFile.createNewFile();
        }
        File innerDir = new File("testdir/inner");
        if (!innerDir.exists()) {
            innerDir.mkdir();
        }
        File innerDirFirst = new File("testdir/inner/first");
        if (!innerDirFirst.exists()) {
            innerDirFirst.createNewFile();
        }
        File innerDirSecond = new File("testdir/inner/second");
        if (!innerDirSecond.exists()) {
            innerDirSecond.createNewFile();
        }
        File innerDirFile = new File("testdir/inner/file");
        if (!innerDirFile.exists()) {
            innerDirFile.createNewFile();
        }
                
        Crawler crawler = new Crawler();
        crawler.crawl(root);
        
        log.info("{}", crawler.memorySize());
        
        crawler.remember();
    }
}
