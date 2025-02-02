package org.example.data.dto;

import org.example.data.dto.Project;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Account {

    private String name;
    private String email;
    private String password;
    private HashSet<Project> projects = new LinkedHashSet<>();

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addProject(Project pj) {
        projects.add(pj);
    }

    public void deleteProject(Project pj) {
        projects.remove(pj);
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
