package tn.talys.spring.audit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.talys.spring.audit.Classe.ChangeLog;
import tn.talys.spring.audit.Classe.DiffRequest;
import tn.talys.spring.audit.models.Project;
import tn.talys.spring.audit.serviceInterface.IProjectService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ProjectC")
public class ProjectController {

    IProjectService iProjectService;

    @PostMapping("/project/diff")
    public void detectChanges(@RequestBody DiffRequest diffRequest) {
        List<ChangeLog> changeLogs = iProjectService.detectChanges(diffRequest.getOldProject(), diffRequest.getNewProject());
        iProjectService.saveChangeLogs(changeLogs);
    }


}
