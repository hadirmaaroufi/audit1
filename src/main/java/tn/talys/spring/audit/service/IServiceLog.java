//package tn.talys.spring.audit.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import tn.talys.spring.audit.models.ChangeLog;
//import tn.talys.spring.audit.models.DiffRequest;
//import tn.talys.spring.audit.repository.ChangeLogRepository;
//import tn.talys.spring.audit.serviceInterface.ChangeLogService;
//
//import javax.json.JsonObject;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.fasterxml.jackson.databind.util.JSONPObject;
//@Service
//public class IServiceLog implements ChangeLogService {
//    @Autowired
//    ChangeLogRepository changeLogRepository;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
////    @Override
////    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException {
////        ChangeLog changeLog = createChangeLog(diffRequest);
////        changeLogRepository.save(changeLog);
////    }
//    @Override
//    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException {
//        List<ChangeLog> changeLogs = createChangeLog(diffRequest);
//        if (!changeLogs.isEmpty()) {
//            changeLogRepository.saveAll(changeLogs);
//        } else {
//            System.out.println("No changes found for entity: " + diffRequest.getEntityCode());
//        }
//    }
//
////    private ChangeLog createChangeLog(DiffRequest diffRequest) throws JsonProcessingException {
////        ChangeLog changeLog = new ChangeLog();
////        changeLog.setFieldName(diffRequest.getEntityCode().toString());
////        changeLog.setEntityCode(diffRequest.getEntityCode());
////        changeLog.setInstanceId(diffRequest.getInstanceId());
////        changeLog.setUpdatedAt(new Date());
////        changeLog.setUserId(diffRequest.getUser().getId());
////
////        JSONObject oldObject = new JSONObject(diffRequest.getOldInstance());
////        JSONObject newObject = new JSONObject(diffRequest.getNewInstance());
////
////        boolean changesFound = findChanges(changeLog, "", oldObject, newObject);
////
////        return changesFound ? changeLog : null;
////    }
//
//    private List<ChangeLog> createChangeLog(DiffRequest diffRequest) throws JsonProcessingException {
//        List<ChangeLog> changeLogs = new ArrayList<>();
//
//        JSONObject oldObject = new JSONObject(diffRequest.getOldInstance());
//        JSONObject newObject = new JSONObject(diffRequest.getNewInstance());
//
//        for (String entityKey : oldObject.keySet()) {
//            Object oldValue = oldObject.get(entityKey);
//            Object newValue = newObject.get(entityKey);
//
//            if (oldValue != null && !oldValue.equals(newValue)) {
//                ChangeLog changeLog = new ChangeLog();
//                changeLog.setFieldName(entityKey);
//                changeLog.setEntityCode(diffRequest.getEntityCode());
//                changeLog.setInstanceId(diffRequest.getInstanceId());
//                changeLog.setUpdatedAt(new Date());
//                changeLog.setUserId(diffRequest.getUser().getId());
//                changeLog.addChange(oldValue.toString(), newValue != null ? newValue.toString() : "null");
//
//                changeLogs.add(changeLog);
//            }
//        }
//
//        for (String entityKey : newObject.keySet()) {
//            Object newValue = newObject.get(entityKey);
//            Object oldValue = oldObject.get(entityKey);
//
//            if (newValue != null && !newValue.equals(oldValue)) {
//                if (oldValue == null) {
//                    ChangeLog changeLog = new ChangeLog();
//                    changeLog.setFieldName(entityKey);
//                    changeLog.setEntityCode(diffRequest.getEntityCode());
//                    changeLog.setInstanceId(diffRequest.getInstanceId());
//                    changeLog.setUpdatedAt(new Date());
//                    changeLog.setUserId(diffRequest.getUser().getId());
//                    changeLog.addChange("null", newValue.toString());
//
//                    changeLogs.add(changeLog);
//                }
//            }
//        }
//
//        return changeLogs;
//    }
//
//
////    private boolean findChanges(ChangeLog changeLog, String path, JSONObject oldObject, JSONObject newObject) {
////        boolean changesFound = false;
////
////        for (String key : oldObject.keySet()) {
////            Object oldValue = oldObject.get(key);
////            Object newValue = newObject.get(key);
////
////            if (oldValue != null && !oldValue.equals(newValue)) {
////                String fullPath = path.isEmpty() ? key : path + "." + key;
////                changeLog.setFieldName(fullPath);
////                changeLog.setOldValue(oldValue.toString());
////                changeLog.setNewValue(newValue != null ? newValue.toString() : "null");
////                changesFound = true;
////            }
////        }
////
////        for (String key : newObject.keySet()) {
////            Object newValue = newObject.get(key);
////            Object oldValue = oldObject.get(key);
////
////            if (newValue != null && !newValue.equals(oldValue)) {
////                String fullPath = path.isEmpty() ? key : path + "." + key;
////                if (oldValue == null) {
////                    changeLog.setFieldName(fullPath);
////                    changeLog.setOldValue("null");
////                    changeLog.setNewValue(newValue.toString());
////                    changesFound = true;
////                }
////            }
////        }
////
////        return changesFound;
////    }
//
//
//    private boolean findChanges(ChangeLog changeLog, String path, JSONObject oldObject, JSONObject newObject) {
//        boolean changesFound = false;
//
//        for (String key : oldObject.keySet()) {
//            Object oldValue = oldObject.get(key);
//            Object newValue = newObject.get(key);
//
//            if (oldValue != null && !oldValue.equals(newValue)) {
//                String fullPath = path.isEmpty() ? key : path + "." + key;
//                changeLog.setFieldName(fullPath);
//                changeLog.addChange(oldValue.toString(), newValue != null ? newValue.toString() : "null");
//                changesFound = true;
//            }
//        }
//
//        for (String key : newObject.keySet()) {
//            Object newValue = newObject.get(key);
//            Object oldValue = oldObject.get(key);
//
//            if (newValue != null && !newValue.equals(oldValue)) {
//                String fullPath = path.isEmpty() ? key : path + "." + key;
//                if (oldValue == null) {
//                    changeLog.setFieldName(fullPath);
//                    changeLog.addChange("null", newValue.toString());
//                    changesFound = true;
//                }
//            }
//        }
//
//        return changesFound;
//    }
//
//
//
//}

package tn.talys.spring.audit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tn.talys.spring.audit.enums.EntityCode;
import tn.talys.spring.audit.models.ChangeLog;
import tn.talys.spring.audit.models.DiffRequest;
import tn.talys.spring.audit.repository.ChangeLogRepository;
import tn.talys.spring.audit.serviceInterface.ChangeLogService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IServiceLog implements ChangeLogService {
    @Autowired
    ChangeLogRepository changeLogRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
     ElasticsearchOperations elasticsearchOperations;

    @Override
    public void auditChanges(DiffRequest diffRequest) throws JsonProcessingException {
        List<ChangeLog> changeLogs = createChangeLog(diffRequest);
        if (!changeLogs.isEmpty()) {
            changeLogRepository.saveAll(changeLogs);
        } else {
            System.out.println("No changes found for entity: " + diffRequest.getEntityCode());
        }
    }

    @Override
    public List<ChangeLog> getChangeLogByInstanceCode(EntityCode entityCode) {

        return changeLogRepository.findByEntityCode(entityCode);
    }

    @Override
    public List<ChangeLog> getChangeLogByInstanceId(Long instanceId) {
        return changeLogRepository.findByInstanceId(instanceId);
    }

    @Override
    public List<ChangeLog> getChangeLogByUserId(Long userId) {
        return changeLogRepository.findByUserId(userId);
    }

    @Override
    public List<ChangeLog> getChangeLogByEntityCodeAndInstanceId(EntityCode entityCode, Long instanceId) {
        return changeLogRepository.findByEntityCodeAndInstanceId(entityCode,instanceId);
    }

    private List<ChangeLog> createChangeLog(DiffRequest diffRequest) throws JsonProcessingException {
        List<ChangeLog> changeLogs = new ArrayList<>();

        JSONObject oldObject = new JSONObject(diffRequest.getOldInstance());
        JSONObject newObject = new JSONObject(diffRequest.getNewInstance());

        for (String entityKey : oldObject.keySet()) {
            Object oldValue = oldObject.get(entityKey);
            Object newValue = newObject.get(entityKey);

            if (oldValue != null && !oldValue.equals(newValue)) {
                ChangeLog changeLog = new ChangeLog();
                changeLog.setFieldName(entityKey);
                changeLog.setEntityCode(diffRequest.getEntityCode());
                changeLog.setInstanceId(diffRequest.getInstanceId());
                changeLog.setUpdatedAt(new Date());
                changeLog.setUserId(diffRequest.getUser().getId());

                List<String> oldValues = new ArrayList<>();
                List<String> newValues = new ArrayList<>();
                oldValues.add(oldValue.toString());
                newValues.add(newValue != null ? newValue.toString() : "null");

                // Check if the field name already exists in the changeLogs list
                for (ChangeLog existingChangeLog : changeLogs) {
                    if (existingChangeLog.getFieldName().equals(entityKey)) {
                        existingChangeLog.getOldValues().addAll(oldValues);
                        existingChangeLog.getNewValues().addAll(newValues);
                        return changeLogs;
                    }
                }

                changeLog.setOldValues(oldValues);
                changeLog.setNewValues(newValues);
                changeLogs.add(changeLog);
            }
        }

        for (String entityKey : newObject.keySet()) {
            Object newValue = newObject.get(entityKey);
            Object oldValue = oldObject.get(entityKey);

            if (newValue != null && !newValue.equals(oldValue)) {
                if (oldValue == null) {
                    ChangeLog changeLog = new ChangeLog();
                    changeLog.setFieldName(entityKey);
                    changeLog.setEntityCode(diffRequest.getEntityCode());
                    changeLog.setInstanceId(diffRequest.getInstanceId());
                    changeLog.setUpdatedAt(new Date());
                    changeLog.setUserId(diffRequest.getUser().getId());

                    List<String> oldValues = new ArrayList<>();
                    List<String> newValues = new ArrayList<>();
                    oldValues.add("null");
                    newValues.add(newValue.toString());

                    // Check if the field name already exists in the changeLogs list
                    for (ChangeLog existingChangeLog : changeLogs) {
                        if (existingChangeLog.getFieldName().equals(entityKey)) {
                            existingChangeLog.getOldValues().addAll(oldValues);
                            existingChangeLog.getNewValues().addAll(newValues);
                            return changeLogs;
                        }
                    }

                    changeLog.setOldValues(oldValues);
                    changeLog.setNewValues(newValues);
                    changeLogs.add(changeLog);
                }
            }
        }

        return changeLogs;
    }
}


