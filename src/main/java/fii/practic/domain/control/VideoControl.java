package fii.practic.domain.control;

import fii.practic.domain.entity.Video;
import fii.practic.domain.repository.VideoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * CRUD and find operations for {@code Video} class.
 */
@Service
@Transactional
public class VideoControl {
    /**
     * Repository interface used for managing video items in elastic search.
     */
    private final VideoRepository repository;

    @Autowired
    public VideoControl(VideoRepository repository) {
        this.repository = repository;
    }

    /**
     * Persist an item into the {@code VideoRepository} .
     *
     * @param video Video instance to be persisted.
     * @return persisted video instance.
     */
    public Video save(Video video) {
        if (video == null) {
            throw new NullPointerException("Video instance cannot be null.");
        }

        return this.repository.save(video);
    }

    /**
     * Bulk save.
     *
     * @param videos A list of videos to be saved.
     * @return {@code Iterable<Video>} containing the persisted videos.
     */
    public Iterable<Video> save(List<Video> videos) {
        return this.repository.save(videos);
    }

    /**
     * Delete all items from repository.
     */
    public void delete() {
        this.repository.deleteAll();
    }

    /**
     * Delete specific item from repository.
     *
     * @param id Video id to be removed.
     */
    public void delete(String id) {
        this.repository.delete(id);
    }


    public List<Video> findByName(String name) {
        return this.repository.findDistinctByNameContaining(name);
    }

    public List<Video> findByVideoSource(String source) {
        return this.repository.findDistinctByVideoSourceContains(source);
    }
}
