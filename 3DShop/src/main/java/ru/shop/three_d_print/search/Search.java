package ru.shop.three_d_print.search;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search
{
    public List<String> getDirectoryFileNames(String suffixPath)
    {
        Class thisClass = getClass();
        URL url = thisClass.getClassLoader().getResource(suffixPath);
        String correctedPath = url.getPath().replace("%20", " ");
        File folder = new File(correctedPath);
        Path path = Paths.get(folder.getPath());

        if (!Files.exists(path)) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

        File[] files = folder.listFiles();
        List<String> fileNames = new ArrayList();

        if (files != null)
        {
            for(File file : files)
            {
                System.out.println("Очередной файл был наден: " + file.getName());
                fileNames.add(file.getName());
            }
        }

        return fileNames;
    }
}
