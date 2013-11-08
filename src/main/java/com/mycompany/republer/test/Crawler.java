package com.mycompany.republer.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ilyamirin
 */
@Slf4j
public class Crawler {

    protected Map<String, Set<String>> memory = new HashMap<String, Set<String>>();

    /*
     Apache commons code
     */
    protected static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        File canon;
        if (file.getParent() == null) {
            canon = file;
        } else {
            File canonDir = file.getParentFile().getCanonicalFile();
            canon = new File(canonDir, file.getName());
        }
        return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
    }

    protected void memorize(String path, String fileName) {
        if (!memory.containsKey(fileName)) {
            memory.put(fileName, new HashSet<String>(1));
        }
        memory.get(fileName).add(path);
    }

    public void crawl(File file) throws IOException {
        if (!file.exists() || !file.canRead() || Crawler.isSymlink(file)) {
            log.error("Can`t get access to {} or it`s symlink", file.getPath());
            return;
        }
        
        memorize(file.getAbsolutePath(), file.getName());
        if (memory.size() % 100 == 0) {
            log.info("{} files were found", memory.size());
        }

        if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                crawl(innerFile);
            }
        }
    }

    public int memorySize() {
        return memory.size();
    }

    public void remember(String filePath) throws IOException {
        ReversedIndex index = ReversedIndex.newReversedIndex(filePath);
        index.dump(memory);
    }
}
