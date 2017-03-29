package fii.practic.domain.repository;

import fii.practic.domain.entity.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Video repository interface use for managing video objects in ElasticSearch.
 */
public interface VideoRepository extends ElasticsearchRepository<Video, String> {
    List<Video> findDistinctByNameContaining(String name);
    List<Video> findDistinctByVideoSourceContains(String name);
}
