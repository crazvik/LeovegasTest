package se.jljung.LeovegasTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Forms.PlayerForm;
import se.jljung.LeovegasTest.Service.PlayerService;
import se.jljung.LeovegasTest.Service.SessionService;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    PlayerService playerService;

    @Autowired
    SessionService sessionService;

    @GetMapping()
    public String getRegisterForm(Model model) {
        ControllerHelper(model, 1L, sessionService, playerService);
        return "register";
    }

    static void ControllerHelper(Model model, @PathVariable Long playerId, SessionService sessionService, PlayerService playerService) {
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        model.addAttribute("form", new PlayerForm());
        Player player = playerService.findById(playerId);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
    }

    @GetMapping("/{playerId}")
    public String getRegisterForm(Model model, @PathVariable Long playerId) {
        ControllerHelper(model, playerId, sessionService, playerService);
        return "register";
    }

    @PostMapping("/process")
    public String register(@ModelAttribute("form") PlayerForm form) {
        if(form.getPassword().equals(form.getPasswordConfirm()) && !form.getPassword().isEmpty() &&
                !form.getPasswordConfirm().isEmpty() && !form.getUsername().isEmpty()) {
            if(playerService.createPlayer(new Player(form.getUsername(), form.getPassword(), 0, false, new HashSet<>()))) {
                return "redirect:/users/" + playerService.findByUsername(form.getUsername()).getPlayerId();
            } else {
                return "redirect:/index";
            }
        }
        throw new IllegalArgumentException("Wrong form data");
    }
}