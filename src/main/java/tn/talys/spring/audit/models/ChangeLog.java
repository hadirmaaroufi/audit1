package tn.talys.spring.audit.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import tn.talys.spring.audit.enums.EntityCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "changelogs")
public class ChangeLog {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String fieldName;

    @Field(type = FieldType.Text)
    private List<String> oldValues = new ArrayList<>();

    @Field(type = FieldType.Text)
    private List<String> newValues = new ArrayList<>();

    @Field(type = FieldType.Text)
    private EntityCode entityCode;

    @Field(type = FieldType.Long)
    private Long instanceId;

    @Field(type = FieldType.Date)
    private Date updatedAt;

    @Field(type = FieldType.Long)
    private Long userId;

    public void addChange(String oldValue, String newValue) {
        oldValues.add(oldValue);
        newValues.add(newValue);
    }
}
