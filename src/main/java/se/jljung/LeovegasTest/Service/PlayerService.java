package se.jljung.LeovegasTest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Repository.PlayerRepo;
import se.jljung.LeovegasTest.Repository.SessionRepo;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private SessionService sessionService;

    public boolean createPlayer(Player player) {
        if (playerRepo.findPlayerByUsername(player.getUsername()) == null) {
            player.setPassword(BCrypt.hashpw(player.getPassword(), BCrypt.gensalt(12)));
            playerRepo.save(player);
            if (sessionService.getSessions().isEmpty()) {
                System.out.println(player);
                sessionService.createSession(player.getPlayerId(), UUID.randomUUID().toString());
                System.out.println(sessionService.getSessions());
                return true;
            }
        }
        return false;
    }

    public Player loginPlayer(String username, String password) {
        if (playerRepo.findPlayerByUsername(username) != null) {
            if (BCrypt.checkpw(password, playerRepo.findPlayerByUsername(username).getPassword())) {
                sessionService.createSession(playerRepo.findPlayerByUsername(username).getPlayerId(), UUID.randomUUID().toString());
                return playerRepo.findPlayerByUsername(username);
            }
        } throw new IllegalArgumentException("Wrong password or username");
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player findById(Long id) {
        if (playerRepo.findById(id).isPresent()) {
            return playerRepo.findById(id).get();
        } else return null;
    }

    public Player findByUsername(String username) {
        return playerRepo.findPlayerByUsername(username);
    }

}
