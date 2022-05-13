package edu.uoc.epcsd.showcatalog.repositories;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {



}
