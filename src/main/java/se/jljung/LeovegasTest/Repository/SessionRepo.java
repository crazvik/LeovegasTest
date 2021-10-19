package se.jljung.LeovegasTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jljung.LeovegasTest.Entity.Session;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    void deleteSessionByPlayerId(Long playerId);
}
