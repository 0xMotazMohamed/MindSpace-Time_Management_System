package org.example.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GitHubDownloader {
    private static final String REPO_URL = "https://AbdullahMostafa24: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@github.com/AbdullahMostafa24/TimeManagementData.git";
    private static final String LOCAL_REPO_PATH = "local-repo"; //Temp folder to clone repo
    private static final String LOCAL_JSON_PATH = "src/main/resources/accounts.json";

    public static void download() {
        try {
            //Clone repo if not cloned
            File localRepo = new File(LOCAL_REPO_PATH);
            if (!localRepo.exists()) {
                Git.cloneRepository()
                        .setURI(REPO_URL)
                        .setDirectory(localRepo)
                        .call();
                System.out.println("Cloned repo successfully");
            } else {
                //If repo exists, pull the latest changes
                Git.open(localRepo).pull().call();
                System.out.println("Pulled latest changes");
            }
            File repoJsonFile = new File(localRepo, "accounts.json");
            File targetJsonFile = new File(LOCAL_JSON_PATH);
            if (repoJsonFile.exists()) {
                Files.copy(repoJsonFile.toPath(),
                        targetJsonFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("synced successfully.");
            } else {
                System.out.println("json not found in repo");
            }
        } catch (GitAPIException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
