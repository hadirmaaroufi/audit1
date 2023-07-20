package tn.talys.spring.audit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.talys.spring.audit.models.ChangeLog;
import tn.talys.spring.audit.models.DiffRequest;
import tn.talys.spring.audit.repository.ChangeLogRepository;
import tn.talys.spring.audit.serviceInterface.ChangeLogService;

import javax.json.JsonObject;
import java.util.Date;
import com.fasterxml.jackson.databind.util.JSONPObject;
@Service
public class IServiceLog implements ChangeLogService {
    @Autowired
    ChangeLogRepository changeLogRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException {
        ChangeLog changeLog = createChangeLog(diffRequest);
        changeLogRepository.save(changeLog);
    }

    private ChangeLog createChangeLog(DiffRequest diffRequest) throws JsonProcessingException {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setFieldName(diffRequest.getEntityCode().toString());
        changeLog.setEntityCode(diffRequest.getEntityCode());
        changeLog.setInstanceId(diffRequest.getInstanceId());
        changeLog.setUpdatedAt(new Date());
        changeLog.setUserId(diffRequest.getUser().getId());
        JSONObject oldObject = new JSONObject(diffRequest.getOldInstance());
        JSONObject newObject = new JSONObject(diffRequest.getNewInstance());
        findChanges(changeLog, "", oldObject, newObject);
        return changeLog;
    }
    private void findChanges(ChangeLog changeLog, String path, JSONObject oldObject, JSONObject newObject) {
        for (String key : oldObject.keySet()) {
            Object oldValue = oldObject.get(key);
            Object newValue = newObject.get(key);

            if (oldValue != null && !oldValue.equals(newValue)) {
                String fullPath = path.isEmpty() ? key : path + "." + key;
                changeLog.setFieldName(fullPath);
                changeLog.setOldValue(oldValue.toString());
                changeLog.setNewValue(newValue != null ? newValue.toString() : "null");
            }
        }

        for (String key : newObject.keySet()) {
            Object newValue = newObject.get(key);
            Object oldValue = oldObject.get(key);

            if (newValue != null && !newValue.equals(oldValue)) {
                String fullPath = path.isEmpty() ? key : path + "." + key;
                if (oldValue == null) {
                    changeLog.setFieldName(fullPath);
                    changeLog.setOldValue("null");
                    changeLog.setNewValue(newValue.toString());
                }
            }
        }
    }


}
