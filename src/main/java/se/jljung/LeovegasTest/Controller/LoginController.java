package se.jljung.LeovegasTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Forms.PlayerForm;
import se.jljung.LeovegasTest.Service.PlayerService;
import se.jljung.LeovegasTest.Service.SessionService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class LoginController {
    @Autowired
    PlayerService playerService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        model.addAttribute("form", new PlayerForm());
        Player player = playerService.findById(1L);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "login";
    }

    @PostMapping("/login/process")
    public String login(@ModelAttribute("form") PlayerForm form) {
        if(playerService.loginPlayer(form.getUsername(), form.getPassword())==null) {
            throw new IllegalArgumentException();
        } else return "redirect:/users/" + playerService.findByUsername(form.getUsername()).getPlayerId();
    }

    @PostMapping("/logout/{playerId}")
    public String logout(@PathVariable Long playerId) {
        for (Session session:
             sessionService.getSessions()) {
            if (session.getPlayerId().equals(playerId)) {
                sessionService.deleteSession(playerService.findById(playerId));
            }
        }
        return "redirect:/index";
    }
}
