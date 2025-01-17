package timemanager.data.dao;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.nio.file.StandardCopyOption;

public class GitHubUploader {
    private static final String REPO_URL = "https://AbdullahMostafa24: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@github.com/AbdullahMostafa24/TimeManagementData.git";
    private static final String LOCAL_PATH = "local-repo";
    private static final String LOCAL_FILE = "accounts.json";

    public static void upload() {
        try {
            //Clone repo if not cloned
            File localRepo = new File(LOCAL_PATH);
            if (!localRepo.exists()) {
                Git.cloneRepository()
                        .setURI(REPO_URL)
                        .setDirectory(localRepo)
                        .call();
            }

            //Open repo and copy file into it
            Git git = Git.open(localRepo);
            File sourceFile = new File("accounts.json");
            File targetFile = new File(LOCAL_PATH, "accounts.json");
            if (sourceFile.exists()) {
                java.nio.file.Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            //Add & Commit changes
            git.add().addFilepattern("accounts.json").call();
            git.commit().setMessage("Updated accounts.json").call();

            //Push changes
            git.push().call();
            System.out.println("File pushed to GitHub!");

        } catch (GitAPIException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
