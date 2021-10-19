package se.jljung.LeovegasTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.jljung.LeovegasTest.Entity.Player;
import se.jljung.LeovegasTest.Entity.Session;
import se.jljung.LeovegasTest.Forms.TransactionForm;
import se.jljung.LeovegasTest.Service.PlayerService;
import se.jljung.LeovegasTest.Service.SessionService;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    PlayerService playerService;

    @Autowired
    SessionService sessionService;

    @GetMapping()
    public String transaction(Model model) {
        model.addAttribute("form", new TransactionForm());
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        Player player = playerService.findById(1L);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "transaction";
    }

    @GetMapping("/{playerId}")
    public String transaction(Model model, @PathVariable Long playerId) {
        model.addAttribute("form", new TransactionForm());
        List<Session> sessions = sessionService.getSessions();
        model.addAttribute("sessions", sessions);
        Player player = playerService.findById(playerId);
        model.addAttribute("player", player);
        List<Player> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "transaction";
    }

    @PostMapping("/process")
    public String addFunds(@ModelAttribute("form") TransactionForm form) {
        if (playerService.addFunds(form.getPlayerId(), form.getAmount())) {
            return "redirect:/users/" + form.getPlayerId();
        } else throw new IllegalArgumentException("Form error");
    }

    @PostMapping("/remove")
    public String removeFunds(@ModelAttribute("form") TransactionForm form) {
        if (playerService.removeFunds(form.getPlayerId(), form.getAmount())) {
            return "redirect:/users/" + form.getPlayerId();
        } else throw new IllegalArgumentException("Form error");
    }
}
