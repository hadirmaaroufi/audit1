package tn.talys.spring.audit.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tn.talys.spring.audit.models.ChangeLog;

@Repository
public interface ChangeLogRepository extends ElasticsearchRepository<ChangeLog,String> {
}
