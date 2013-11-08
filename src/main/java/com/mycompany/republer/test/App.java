package com.mycompany.republer.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            log.error("Choose your destiny please: use build or find command");

        } else if (args[0].equals("build")) {
            Crawler crawler = new Crawler();
            for (int i = 2; i < args.length; i++) {
                File file = new File(args[i]);
                crawler.crawl(file);
            }
            log.trace("{} files were found", crawler.memorySize());
            crawler.remember(args[1]);
            
        } else if (args[0].equals("find")) {
            ReversedIndex reversedIndex = ReversedIndex.newReversedIndex(args[1]);
            Map<String, Set<String>> indexMap = new HashMap<String, Set<String>>();
            reversedIndex.load(indexMap);
            for (int i = 2; i < args.length; i++) {
                System.out.println(indexMap.get(args[i]));
            }
            
        } else {
            log.error("What do you want? I can do only build or find.");
            
        }
    }//main
}
