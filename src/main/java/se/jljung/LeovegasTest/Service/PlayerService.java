package se.jljung.LeovegasTest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Transaction;
import se.jljung.LeovegasTest.Repository.PlayerRepo;

import org.springframework.security.crypto.bcrypt.BCrypt;
import se.jljung.LeovegasTest.Repository.TransactionRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    private SessionService sessionService;

    public boolean createPlayer(Player player) {
        if (playerRepo.findPlayerByUsername(player.getUsername()) == null) {
            player.setPassword(BCrypt.hashpw(player.getPassword(), BCrypt.gensalt(12)));
            playerRepo.save(player);
            if (sessionService.getSessions().isEmpty()) {
                sessionService.createSession(player.getPlayerId(), UUID.randomUUID().toString());
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

    public boolean addFunds(Long playerId, double amount) {
        if (playerRepo.findById(playerId).isPresent()) {
            LocalDate localDate = LocalDate.now();
            playerRepo.findById(playerId).get().setFunds(amount+=playerRepo.findById(playerId).get().getFunds());
            Transaction transaction = new Transaction(localDate.toString(), playerRepo.findById(playerId).get());
            transactionRepo.save(transaction);
            playerRepo.findById(playerId).get().setTransactions(transaction);
            return true;
        }
        throw new IllegalArgumentException("Not a number added");
    }

    public boolean removeFunds(Long playerId, double amount) {
        if (playerRepo.findById(playerId).isPresent()) {
            LocalDate localDate = LocalDate.now();
            if(playerRepo.findById(playerId).get().getFunds()<amount) {
                return false;
            } else {
                playerRepo.findById(playerId).get().setFunds(amount-=playerRepo.findById(playerId).get().getFunds());
                Transaction transaction = new Transaction(localDate.toString(), playerRepo.findById(playerId).get());
                transactionRepo.save(transaction);
                playerRepo.findById(playerId).get().setTransactions(transaction);
                return true;
            }
        }
        throw new IllegalArgumentException("Not a number added");
    }
}