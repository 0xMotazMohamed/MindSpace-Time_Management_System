package org.example.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GitHubUploader {
    private static final String REPO_URL = "https://AbdullahMostafa24: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@github.com/AbdullahMostafa24/TimeManagementData.git";
    private static final String LOCAL_REPO_PATH = "local-repo";
    private static final String LOCAL_JSON_PATH = "src/main/resources/accounts.json";

    public static void upload() {
        try {
            //Clone repo if not cloned
            File localRepo = new File(LOCAL_REPO_PATH);
            if (!localRepo.exists()) {
                System.out.println("local not found. Downloading..");
                GitHubDownloader.download();
            }

            File sourceJsonFile = new File(LOCAL_JSON_PATH);
            File repoJsonFile = new File(localRepo, "accounts.json");
            if (sourceJsonFile.exists()) {
                Files.copy(sourceJsonFile.toPath(),
                        repoJsonFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("file copied");
            } else {
                System.out.println("not found in res");
            }

            //Open repo and copy file into it
            Git git = Git.open(localRepo);
            git.add().addFilepattern("accounts.json").call();
            git.commit().setMessage("update accounts.json").call();
            git.push().call();

            System.out.println("upload to github");
        } catch (TransportException e) {
            e.printStackTrace();
        } catch (GitAPIException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
