package tn.talys.spring.audit.service;

import org.springframework.stereotype.Service;
import tn.talys.spring.audit.Classe.ChangeLog;
import tn.talys.spring.audit.models.Project;
import tn.talys.spring.audit.repository.ProjectRep;
import tn.talys.spring.audit.serviceInterface.IProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    private final ProjectRep projectRepository;

    public ProjectService(ProjectRep projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ChangeLog> detectChanges(Project oldProject, Project newProject) {
        List<ChangeLog> changeLogs = new ArrayList<>();

        if (!oldProject.getName().equals(newProject.getName())) {
            ChangeLog changeLog = new ChangeLog("name", oldProject.getName(), newProject.getName());
            changeLogs.add(changeLog);
        }

        if (!oldProject.getDescription().equals(newProject.getDescription())) {
            ChangeLog changeLog = new ChangeLog("description", oldProject.getDescription(), newProject.getDescription());
            changeLogs.add(changeLog);
        }

        if (!oldProject.getBudget().equals(newProject.getBudget())) {
            ChangeLog changeLog = new ChangeLog("budget", oldProject.getBudget().toString(), newProject.getBudget().toString());
            changeLogs.add(changeLog);
        }

        return changeLogs;
    }
    public void saveChangeLogs(List<ChangeLog> changeLogs) {
        for (ChangeLog changeLog : changeLogs) {
            Project project = new Project();
            projectRepository.save(project); // Sauvegarde du Project modifié dans ElasticSearch
        }
    }
}

