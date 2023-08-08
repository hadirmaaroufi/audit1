package tn.talys.spring.audit.serviceInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import tn.talys.spring.audit.enums.EntityCode;
import tn.talys.spring.audit.models.ChangeLog;
import tn.talys.spring.audit.models.DiffRequest;

import java.util.List;

public interface ChangeLogService {
    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException;
    public List<ChangeLog> getChangeLogByInstanceCode(EntityCode entityCode);
    public List<ChangeLog> getChangeLogByInstanceId(Long instanceId);
    public List<ChangeLog> getChangeLogByUserId(Long userId);

    public List<ChangeLog> getChangeLogByEntityCodeAndInstanceId(EntityCode entityCode, Long instanceId);

}
