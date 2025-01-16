package timemanager.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class GitHubDownloader {
    private static final String REPO_URL = "https://AbdullahMostafa24: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@github.com/AbdullahMostafa24/TimeManagementData.git";
    private static final String LOCAL_PATH = "local-repo";

    public static void download() {
        try {
            //Clone repo if not cloned
            File localRepo = new File(LOCAL_PATH);
            if (!localRepo.exists()) {
                Git.cloneRepository()
                        .setURI(REPO_URL)
                        .setDirectory(localRepo)
                        .call();
            } else {
                //If repo exists, pull the latest changes
                Git.open(localRepo).pull().call();
            }
            System.out.println("Repo synced successfully.");
        } catch (GitAPIException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
