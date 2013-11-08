package com.mycompany.republer.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ilyamirin
 */
@Slf4j
public class ReversedIndex {

    private String pathToIndex;

    private ReversedIndex(String pathToIndex) {
        this.pathToIndex = pathToIndex;
    }

    public static ReversedIndex newReversedIndex() throws IOException {
        String pathToIndex = "index.txt";
        return new ReversedIndex(pathToIndex);
    }

    public void dump(Map<String, List<String>> indexMap) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToIndex));
        for (Map.Entry<String, List<String>> entry : indexMap.entrySet()) {
            bufferedWriter.write(entry.getKey());
            bufferedWriter.write(" ");
            for (String path : entry.getValue()) {
                bufferedWriter.write(path);
                bufferedWriter.write(" ");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }

    public void load(Map<String, List<String>> indexMap) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToIndex));
        while (bufferedReader.ready()) {
            String[] result = bufferedReader.readLine().split(" ");
            List<String> pathes = new ArrayList(Arrays.asList(result));
            indexMap.put(pathes.remove(0), pathes);
        }
        bufferedReader.close();
    }

}
