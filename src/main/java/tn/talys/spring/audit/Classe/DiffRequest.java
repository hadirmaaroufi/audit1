package tn.talys.spring.audit.Classe;

import tn.talys.spring.audit.models.Project;

public class DiffRequest {
    private Project oldProject;
    private Project newProject;

    public DiffRequest() {

    }

    public DiffRequest(Project oldProject, Project newProject) {
        this.oldProject = oldProject;
        this.newProject = newProject;
    }

    public Project getOldProject() {
        return oldProject;
    }

    public Project getNewProject() {
        return newProject;
    }

    public void setOldProject(Project oldProject) {
        this.oldProject = oldProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }
}
