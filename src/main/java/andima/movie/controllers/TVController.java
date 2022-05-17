package andima.movie.controllers;

import andima.movie.models.Vote;
import andima.movie.models.entity.TV;
import andima.movie.service.interfaces.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tv/")
public class TVController {
    private final TVService tvService;

    @Autowired
    public TVController(TVService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/id/{id}")
    public TV getTV(@PathVariable String id) {
        TV tv = tvService.getTV(Long.valueOf(id));
        System.out.println(tv);
        return tv;
    }

    @PostMapping("id")
    public void saveTV(@Valid @RequestBody TV tv) {
        tvService.saveTV(tv);
        System.out.println("TV has been saved");
    }

    @PatchMapping("id")
    public void updateTV(@Valid @RequestBody TV tv) {
        tvService.updateTV(tv);
        System.out.println("TV with id = " + tv.getId() + " has been updated");
    }

    @DeleteMapping("id/{id}")
    public void deleteTV(@PathVariable String id) {
        tvService.deleteTV(Long.valueOf(id));
        System.out.println("TV with id = " + id + " was deleted");
    }

    @PostMapping("vote")
    public void voteTV(@Valid @RequestBody Vote vote) {
        tvService.voteTV(vote.getId(), vote.getVote());
        System.out.println("TV with id = " + vote.getId() + " was voted");
    }

    @GetMapping("search")
    public List<TV> searchMovie(@RequestParam(required = false, name = "year") String year,
                                   @RequestParam(required = false, name = "media_type") String media_type,
                                   @RequestParam(required = false, name = "title") String title,
                                   @RequestParam(required = false, name = "lang") String lang) {
        List<TV> list = tvService.searchTV(year, media_type, title, lang);
        System.out.println("TV list filtered by year=" + year
                + " media_type=" + media_type
                + " title=" + title
                + " lang=" + lang);
        return list;
    }
    @GetMapping("trendy")
    public List<TV> trendyTV(){
        return tvService.trendyTV();
    }
}

