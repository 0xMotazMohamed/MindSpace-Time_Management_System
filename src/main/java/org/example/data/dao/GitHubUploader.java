package org.example.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class GitHubUploader {
    private static final String REPO_URL = "https://AbdullahMostafa24:<GITHUB_TOKEN>@github.com/AbdullahMostafa24/MindSpaceData.git";
    private static final String LOCAL_REPO_PATH = System.getenv("APPDATA");

    // Singleton pattern
    private static final GitHubUploader instance = new GitHubUploader();

    private File targetDir;
    private Git git;

    private GitHubUploader() {
        try {
            // initialize Git repo
            targetDir = new File(LOCAL_REPO_PATH, ".MindSpace");
            if (targetDir.exists()) {
                git = Git.open(targetDir);
            }
        } catch (Exception e) {
            System.err.println("Error initializing Git instance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static GitHubUploader getInstance() {
        return instance;
    }

    public void upload() {
        try {
            if (!targetDir.exists()) {
                System.out.println("Cloning repo to " + targetDir.getAbsolutePath());
                git = Git.cloneRepository().setURI(REPO_URL)
                        .setDirectory(targetDir).call();
                System.out.println("Repo cloned");
            } else if (git != null) {
                git.add().addFilepattern(".").call();
                git.commit().setMessage("Uploading changes").call();
                System.out.println("Changes commited");

                git.push().call();
                System.out.println("Changes pushed");
            } else {
                System.err.println("Git instance not initialized. Unable to upload");
            }
        } catch (GitAPIException e) {
            System.err.println("Error interacting with Git repo");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
