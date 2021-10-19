package se.jljung.LeovegasTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Service.PlayerService;

import java.util.HashSet;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    PlayerService playerService;

    @Override
    public void run(String... args) throws Exception {
        playerService.createPlayer(new Player("TestUsername", "TestPassword", 0, true, new HashSet<>()));
    }
}