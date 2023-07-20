package tn.talys.spring.audit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.talys.spring.audit.models.DiffRequest;
import tn.talys.spring.audit.serviceInterface.ChangeLogService;

@RestController
@AllArgsConstructor
@RequestMapping("/Audit")
public class ChangeLogController {
    ChangeLogService changeLogService;
    @PostMapping(value = "/auditChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void auditChanges(@RequestBody DiffRequest diffRequest) throws JsonProcessingException {
        changeLogService.auditChanges(diffRequest);
    }
}
