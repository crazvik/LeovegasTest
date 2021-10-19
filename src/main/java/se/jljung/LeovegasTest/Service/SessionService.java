package se.jljung.LeovegasTest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Repository.SessionRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SessionService {
    @Autowired
    private SessionRepo sessionRepo;

    public void createSession(Long playerId, String activeSession) {
        Session session = new Session(activeSession, playerId);
        sessionRepo.save(session);
    }

    public List<Session> getSessions() {
        return sessionRepo.findAll();
    }

    public void deleteSession(Player player) {
        sessionRepo.deleteSessionByPlayerId(player.getPlayerId());
    }
}
