package tn.talys.spring.audit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.talys.spring.audit.enums.EntityCode;
import tn.talys.spring.audit.models.ChangeLog;
import tn.talys.spring.audit.models.DiffRequest;
import tn.talys.spring.audit.serviceInterface.ChangeLogService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Audit")
public class ChangeLogController {
    ChangeLogService changeLogService;
    @PostMapping(value = "/auditChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void auditChanges(@RequestBody DiffRequest diffRequest) throws JsonProcessingException {
        changeLogService.auditChanges(diffRequest);
    }

    @GetMapping("/{entityCode}")
    public List<ChangeLog> getChangeLogByInstanceCode(@PathVariable EntityCode entityCode) {

        return changeLogService.getChangeLogByInstanceCode(entityCode);

    }
    @GetMapping("/getChangeLogByInstanceId/{instanceId}")
    public List<ChangeLog> getChangeLogByInstanceId(@PathVariable Long instanceId) {

        return changeLogService.getChangeLogByInstanceId(instanceId);

    }
    @GetMapping("/getChangeLogByUserId/{userId}")
    public List<ChangeLog> getChangeLogByUserId(@PathVariable Long userId) {

        return changeLogService.getChangeLogByUserId(userId);

    }
    @GetMapping("/getChangeLogByEntityCodeAndInstanceId/{entityCode}/{instanceId}")
    public List<ChangeLog> getChangeLogByEntityCodeAndInstanceId(@PathVariable EntityCode entityCode, @PathVariable Long instanceId) {
        return changeLogService.getChangeLogByEntityCodeAndInstanceId(entityCode, instanceId);
    }
}
