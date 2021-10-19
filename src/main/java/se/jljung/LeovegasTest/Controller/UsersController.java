package se.jljung.LeovegasTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Service.PlayerService;
import se.jljung.LeovegasTest.Service.SessionService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    PlayerService playerService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/{playerId}")
    public String users(Model model, @PathVariable Long playerId) {
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        Player player = playerService.findById(playerId);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "user";
    }

    @GetMapping("/admin")
    public String users(Model model) {
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        Player player = playerService.findById(1L);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "user";
    }
}
