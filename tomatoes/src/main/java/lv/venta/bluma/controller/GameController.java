package lv.venta.bluma.controller;

import lv.venta.bluma.domain.Game;
import lv.venta.bluma.forms.ContinueForm;
import lv.venta.bluma.forms.HighscoreForm;
import lv.venta.bluma.forms.MakeMoveForm;
import lv.venta.bluma.forms.NewGameForm;
import lv.venta.bluma.service.GameDifficultyService;
import lv.venta.bluma.service.GameService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    final static Logger logger = Logger.getLogger(GameController.class);

    @Autowired
    private GameDifficultyService gameDifficultyService;

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "home"; // apzimee, kursh html fails no resources mapes tiks izmantots
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String showNewGameScreen(Model model) {
        model.addAttribute("gameDifficulties", gameDifficultyService.getAllDifficulties());
        model.addAttribute("newGameForm", new NewGameForm());
        return "new-game";
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startNewGame(@ModelAttribute NewGameForm newGameForm) {
        final Game game = gameService.createNewGame(newGameForm.getPlayerName(), newGameForm.getDifficultyId());
        return "redirect:/game?id=" + game.getGameId();
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String continueGame(@RequestParam Integer id, Model model) {
        final Game game = gameService.getGame(id);
        model.addAttribute("game", game);
        return "gameInProcess";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String makeMove(@ModelAttribute MakeMoveForm makeMoveForm) {
        final Game game = gameService.getGame(makeMoveForm.getGameId());
        gameService.makeMove(makeMoveForm.getGameId(), makeMoveForm.getTomato1() - 1, makeMoveForm.getTomato2() - 1);
        return "redirect:/game?id=" + game.getGameId();
    }

    @RequestMapping(value = "/continue", method = RequestMethod.GET)
    public String continueGameForm() {
        return "continue-game";
    }

    @RequestMapping(value = "/continue", method = RequestMethod.POST)
    public String continueGameForm(@ModelAttribute ContinueForm continueForm) {
        final Game game = gameService.getGame(continueForm.getGameId());

        if (game == null){
            return "home";
        }
        if (!game.isFinished()) {
            return "redirect:/game?id=" + continueForm.getGameId();
        }

        return "home";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String showHighscoreScreen(Model model) {
        model.addAttribute("gameDifficulties", gameDifficultyService.getAllDifficulties());
        model.addAttribute("highscoreForm", new HighscoreForm());
        return "results";
    }

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String showHighscores(@ModelAttribute HighscoreForm highscoreForm, Model model) {
        logger.error(highscoreForm);
        model.addAttribute("gameDifficulties", gameDifficultyService.getAllDifficulties());
        model.addAttribute("highscoreForm", new HighscoreForm());
        final List<Game> gameScores = gameService.getBestGameResults(highscoreForm.getDifficultyId());
        model.addAttribute("games", gameScores);
        return "results";
    }
}
