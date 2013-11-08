package com.mycompany.republer.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ilyamirin
 */
@Slf4j
public class Crawler {

    protected Map<String, List<String>> memory = new HashMap<String, List<String>>();

    protected void memorize(String path, String fileName) {
        if (!memory.containsKey(fileName)) {
            memory.put(fileName, new ArrayList<String>(1));
        }
        memory.get(fileName).add(path);
    }

    public void crawl(File file) {
        if (!file.exists() || !file.canRead()) {
            log.error("Can`t get access to {}", file.getPath());

        } else if (file.isFile()) {
            memorize(file.getAbsolutePath(), file.getName());
            if (memory.size() % 100 == 0) {
                log.info("{} files were found", memory.size());
            }

        } else if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                crawl(innerFile);
            }

        }
    }

    public int memorySize() {
        return memory.size();
    }

    public void remember() throws IOException {
        ReversedIndex index = ReversedIndex.newReversedIndex();
        index.dump(memory);
    }
}