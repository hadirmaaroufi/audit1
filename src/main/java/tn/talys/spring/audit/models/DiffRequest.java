package tn.talys.spring.audit.models;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import tn.talys.spring.audit.enums.EntityCode;

import javax.json.JsonObject;

@Data
public class DiffRequest {
    private String oldInstance;
    private String newInstance;
    private User user;
    private Long instanceId;
    private EntityCode entityCode;
}
