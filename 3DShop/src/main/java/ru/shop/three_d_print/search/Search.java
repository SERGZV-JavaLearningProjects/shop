package ru.shop.three_d_print.search;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search
{
    public String getFirstFileNameInDirectory(String suffixPath)
    {
        Path path = GetFullPathByClasspath(suffixPath);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
        {
            var iterator = stream.iterator();
            if (iterator.hasNext()) return iterator.next().getFileName().toString();
            else return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> getDirectoryFileNames(String suffixPath)
    {
        Path path = GetFullPathByClasspath(suffixPath);

        if (!Files.exists(path)) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

        File[] files = path.toFile().listFiles();
        List<String> fileNames = new ArrayList();

        if (files != null)
        {
            for(File file : files)
                fileNames.add(file.getName());
        }

        return fileNames;
    }

    private Path GetFullPathByClasspath(String suffixPath)
    {
        Class thisClass = getClass();
        URL url = thisClass.getClassLoader().getResource(suffixPath);
        String correctedPath = url.getPath().replace("%20", " ");
        File folder = new File(correctedPath);
        return Paths.get(folder.getPath());
    }
}
