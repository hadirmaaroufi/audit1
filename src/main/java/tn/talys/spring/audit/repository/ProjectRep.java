package tn.talys.spring.audit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tn.talys.spring.audit.models.Project;

public interface ProjectRep extends ElasticsearchRepository<Project, String> {

    Page<Project> findByName(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Project> findByNameUsingCustomQuery(String name, Pageable pageable);
}