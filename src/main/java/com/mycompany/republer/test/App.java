package com.mycompany.republer.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            log.error("Choose your destiny please: use build or find command");

        } else if (args[0].equals("build")) {
            Crawler crawler = new Crawler();
            for (int i = 1; i < args.length; i++) {
                File file = new File(args[i]);
                crawler.crawl(file);
            }
            log.info("{} files were found", crawler.memorySize());
            crawler.remember();
            
        } else if (args[0].equals("find")) {
            ReversedIndex reversedIndex = ReversedIndex.newReversedIndex();
            Map<String, List<String>> indexMap = new HashMap<String, List<String>>();
            reversedIndex.load(indexMap);
            for (int i = 1; i < args.length; i++) {
                log.info("{}", indexMap.get(args[i]));
            }
            
        } else {
            log.error("What do you want? I can do only build or find.");
            
        }
    }//main
}
