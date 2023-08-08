package tn.talys.spring.audit.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tn.talys.spring.audit.enums.EntityCode;
import tn.talys.spring.audit.models.ChangeLog;

import java.util.List;

@Repository
public interface ChangeLogRepository extends ElasticsearchRepository<ChangeLog,String> {
    List<ChangeLog> findByEntityCode(EntityCode entityCode);
    List<ChangeLog> findByInstanceId(Long instanceId);
    List<ChangeLog> findByUserId(Long userId);
}
