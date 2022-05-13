package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.kafka.KafkaConstants;
import edu.uoc.epcsd.showcatalog.services.ShowService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    @GetMapping(path = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity queryShowDetails(@RequestParam(required = false) Long idShow) {
        log.trace("queryShowDetails");
        Show show = showService.queryShowDetails(idShow);
        return new ResponseEntity<>("queryshowdetails", HttpStatus.OK);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addShow(@RequestBody Show show) {
        log.trace("addShow");
        Show newShow = showService.addShow(show);
        if(Objects.nonNull(newShow)){
            kafkaTemplate.send(KafkaConstants.SHOW_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, newShow);
            return new ResponseEntity<>(newShow, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>("error", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idShow}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delShow(@PathVariable Long idShow) {
         log.trace("delShow");
         showService.delShow(idShow);
         return new ResponseEntity<>("delShow", HttpStatus.OK);

    }

    @PostMapping("/{idShow}/addAct")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addAct(@PathVariable Long idShow ,@RequestBody Performance performance) throws Exception {
           log.trace("addAct");
           showService.addAct(idShow, performance);
           return new ResponseEntity<>("addAct", HttpStatus.OK);

    }

    @GetMapping("/{idShow}/queryActs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity queryActs(@PathVariable("idShow") Long idShow) {
        log.trace("queryActs");
        Show showResult = showService.queryShowDetails(idShow);
        return new ResponseEntity<>(showResult.getPerformances(), HttpStatus.OK);

    }

    @DeleteMapping("/{idShow}/delAct")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delActs(@PathVariable Long idShow, @RequestBody Performance performance) throws Exception {
       log.trace("delAct");
       showService.delAct(idShow, performance);
       return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @GetMapping(path = "/query", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity queryShowByCategory(@RequestParam(required = false) Long idShow, @RequestParam(required = false) String showName, @RequestParam(required = false) List<Long> idCategories) {
        log.trace("Query Show By Category");
        List<Show> show = Objects.nonNull(idShow) ? Arrays.asList(showService.queryShowDetails(idShow)).stream().filter(s -> (s != null)).collect(Collectors.toList()) :
                Objects.nonNull(showName) ? showService.queryShowByName(showName) :
                        Objects.nonNull(idCategories) ? showService.queryShowByCategory(idCategories) :
                                showService.queryAllShows();
        return new ResponseEntity<>("queryshowByCategory", HttpStatus.OK);
    }
}
