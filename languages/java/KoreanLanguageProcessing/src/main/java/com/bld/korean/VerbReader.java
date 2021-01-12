package com.bld.korean;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class VerbReader {

    public List<Verb> readAll() throws IOException {
        Gson gson = new Gson();

        // TODO Extract to pure reader service.
        Path verbJsonPath = Paths.get("src", "main", "resources", "infinitive-verbs.json").normalize();
        BufferedReader bufferedReader = Files.newBufferedReader(verbJsonPath);
        Verb[] verbs = gson.fromJson(bufferedReader, Verb[].class);

        return Arrays.asList(verbs);
    }

}
