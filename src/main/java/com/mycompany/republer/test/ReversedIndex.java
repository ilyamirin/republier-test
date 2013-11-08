package com.mycompany.republer.test;

import com.google.common.collect.Sets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
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

    public void dump(Map<String, Set<String>> indexMap) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToIndex));
        for (Map.Entry<String, Set<String>> entry : indexMap.entrySet()) {
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

    public void load(Map<String, Set<String>> indexMap) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToIndex));
        while (bufferedReader.ready()) {
            String[] result = bufferedReader.readLine().split(" ");            
            Set<String> pathes = Sets.newHashSet(Arrays.copyOfRange(result, 0, result.length));
            indexMap.put(result[0], pathes);
        }
        bufferedReader.close();
    }

}
