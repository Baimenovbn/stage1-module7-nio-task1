package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        Path path = file.toPath();
        Profile profile = new Profile();

        try (BufferedReader in = Files.newBufferedReader(path)) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] lineArr = line.split(" ");
                switch (getFieldName(lineArr)) {
                    case "Age":
                        profile.setAge(Integer.parseInt(lineArr[1]));
                        break;
                    case "Name":
                        profile.setName(lineArr[1]);
                        break;
                    case "Email":
                        profile.setEmail(lineArr[1]);
                        break;
                    case "Phone":
                        profile.setPhone(Long.parseLong(lineArr[1]));
                        break;
                    default:
                        LOGGER.log(Level.SEVERE, () -> "Wrong field name" + getFieldName(lineArr));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    private static String getFieldName(String[] line) {
        return line[0].substring(0, line[0].length() - 1);
    }
}
