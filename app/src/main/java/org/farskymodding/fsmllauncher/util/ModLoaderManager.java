package org.farskymodding.fsmllauncher.util;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ModLoaderManager {
    private static Map<String, File> modloaderFiles;
    private static Map<String, String> remoteFiles;

    public static void init() {
        modloaderFiles = Map.of(
            "fsml.jar", new File("lib/fsml.jar"),
            "byte-buddy.jar", new File("lib/byte-buddy.jar"),
            "byte-buddy-agent.jar", new File("lib/byte-buddy-agent.jar")
        );

        remoteFiles = Map.of(
            "fsml.jar", "http://localhost/fsml.jar",//"https://github.com/farskymodding/fsml/releases/latest/fsml.jar"
            
            "byte-buddy.jar", "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.14.3/byte-buddy-1.14.3.jar",
            "byte-buddy-agent.jar", "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/1.14.3/byte-buddy-agent-1.14.3.jar"
        );
    }

    public static boolean verifyLoaderFiles() {
        for (String filename : modloaderFiles.keySet()) {
            File file = modloaderFiles.get(filename);
            if (!file.exists() || !file.isFile()) {
                return false;
            }
        }

        return true;
    }

    public static boolean installModLoader() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            
            // Download files
            for (var entry : remoteFiles.entrySet()) {
                String fileName = entry.getKey();
                String fileUrl = entry.getValue();
                File destination = modloaderFiles.get(fileName);

                System.out.println("Downloading " + fileUrl + "...");
                HttpRequest fileRequest = HttpRequest.newBuilder()
                    .uri(new URI(fileUrl))
                    .build();

                client.send(fileRequest, HttpResponse.BodyHandlers.ofFile(destination.toPath()));
                System.out.println("Saved as " + destination.getName() + ".");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return verifyLoaderFiles();
    }

    public static boolean removeModLoader() {
        for (File file : modloaderFiles.values()) {
            String fileName = file.getName();
            try {
                file.delete();
                System.out.println("Deleted "+ fileName + ".");
            } catch (SecurityException se) {
                return false;
            }
        }
        return true;
    }
}
