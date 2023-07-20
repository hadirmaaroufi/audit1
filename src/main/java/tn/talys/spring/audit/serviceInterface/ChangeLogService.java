package tn.talys.spring.audit.serviceInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import tn.talys.spring.audit.models.ChangeLog;
import tn.talys.spring.audit.models.DiffRequest;

public interface ChangeLogService {
    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException;

}
