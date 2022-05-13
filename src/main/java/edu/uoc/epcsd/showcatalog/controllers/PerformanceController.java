package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.repositories.PerformanceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Performance> getAllPerformances() {
        log.trace("getAllPerformances");
        return performanceRepository.findAll();
    }



    @PostMapping
    public Performance addPerformance(@RequestBody Performance performance) {
        log.trace("addPerformance");
        return performanceRepository.save(performance);
    }

    @DeleteMapping("/{id}")
    public void delPerformance(@PathVariable Long id) {
        log.trace("delPerformance");
        performanceRepository.deleteById(id);
    }

}