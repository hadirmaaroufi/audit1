package tn.talys.spring.audit.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import tn.talys.spring.audit.enums.EntityCode;

import java.util.Date;

@Data
@Document(indexName = "changelogs")
public class ChangeLog {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String fieldName;

    @Field(type = FieldType.Text)
    private String oldValue;

    @Field(type = FieldType.Text)
    private String newValue;

    @Field(type = FieldType.Text)
    private EntityCode entityCode;

    @Field(type = FieldType.Long)
    private Long instanceId;

    @Field(type = FieldType.Date)
    private Date updatedAt;

    @Field(type = FieldType.Long)
    private Long userId;
}
