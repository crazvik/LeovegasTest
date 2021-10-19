package se.jljung.LeovegasTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jljung.LeovegasTest.Entity.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findPlayerByUsername(String username);
}
