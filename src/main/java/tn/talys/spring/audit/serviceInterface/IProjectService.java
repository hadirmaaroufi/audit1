package tn.talys.spring.audit.serviceInterface;

import tn.talys.spring.audit.Classe.ChangeLog;
import tn.talys.spring.audit.models.Project;

import java.util.List;

public interface IProjectService {
    void saveChangeLogs(List<ChangeLog> changeLogs);
    List<ChangeLog> detectChanges(Project oldProject, Project newProject);

}
