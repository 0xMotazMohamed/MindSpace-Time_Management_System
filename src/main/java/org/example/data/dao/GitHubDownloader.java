package org.example.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GitHubDownloader {
    private static final String REPO_URL = "https://AbdullahMostafa24:<GITHUB_TOKEN>@github.com/AbdullahMostafa24/MindSpaceData.git";

    // Singleton pattern
    private static final GitHubDownloader instace = new GitHubDownloader();

    private final File targetDir;
    private Git git;

    private GitHubDownloader() {
        // Initialize target dir
        String appDataPath = System.getenv("APPDATA");
        if (appDataPath == null) {
            throw  new IllegalStateException("AppData not found");
        }
        targetDir = new File(appDataPath, ".MindSpace");
        System.out.println(targetDir.getAbsolutePath());
        // Initialize repo
        try {
            if (targetDir.exists()) {
                git= Git.open(targetDir);
            }
        } catch (Exception e) {
            System.err.println("Error initializing Git instance: " + e.getMessage());
        }
    }

    public static GitHubDownloader getInstance() {
        return instace;
    }

    public void download() {
        try {
            if (!targetDir.exists()) {
                System.out.println("Cloning repo to " + targetDir.getAbsolutePath());
                git = Git.cloneRepository().setURI(REPO_URL)
                        .setDirectory(targetDir).call();
                System.out.println("Repo cloned");
            } else if (git != null){
                System.out.println("pulling latest changes");
                git.pull().call();
                System.out.println("Repo updated");
            } else {
                System.err.println("Git instance not initialized. Unable to pull");
            }
        } catch (GitAPIException e) {
            System.err.println("Error interacting with Git repo");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
