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
@RequestMapping("/index")
public class IndexController {
    @Autowired
    SessionService sessionService;

    @Autowired
    PlayerService playerService;

    @GetMapping()
    public String index(Model model) {
        ControllerHelper(model, 1L, sessionService, playerService);
        return "index";
    }

    @GetMapping("/{playerId}")
    public String index(Model model, @PathVariable Long playerId) {
        ControllerHelper(model, playerId, sessionService, playerService);
        return "index";
    }

    static void ControllerHelper(Model model, @PathVariable Long playerId, SessionService sessionService, PlayerService playerService) {
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        Player player = playerService.findById(playerId);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
    }
}