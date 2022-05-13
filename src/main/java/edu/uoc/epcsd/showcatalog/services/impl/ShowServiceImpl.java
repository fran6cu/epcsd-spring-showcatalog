package edu.uoc.epcsd.showcatalog.services.impl;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.repositories.CategoryRepository;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import edu.uoc.epcsd.showcatalog.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ShowRepository showRepository;





    @Override
    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    @DeleteMapping("/{id}")
    public boolean delShow(Long idShow) {
        showRepository.deleteById(idShow);
        return true;
    }

    @Override
    public Show queryShowDetails(Long idShow) {
        return showRepository.findById(idShow).orElse(null);
    }

    @Override
    public List<Show> queryShowByName(String nom) {
        return showRepository.findByName(nom);
    }

    @Override
    public List<Show> queryShowByCategory(List<Long> categoryIds) {
        return showRepository.findByCategoriesIdIn(categoryIds);

    }



    @Override
    public List<Show> queryAllShows() {
        return showRepository.findAll();
    }

    @Override
    public boolean addAct(Long idShow, Performance performance) throws Exception {
        try{
            Show show = queryShowDetails(idShow);
            if(Objects.nonNull(show)){
                if(Objects.isNull(show.getPerformances()))show.setPerformances(new ArrayList<Performance>());
                if(show.getPerformances().indexOf(performance) < 0){
                    show.getPerformances().add(performance);
                    showRepository.saveAndFlush(show);
                    return true;
                }
                else throw new Exception("act already exist");
            }
            else throw new Exception("show already exist");
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public boolean delAct(Long idShow, Performance performance) throws Exception {
        try{
            Show show = queryShowDetails(idShow);
            if(Objects.nonNull(show)){
                if(Objects.isNull(show.getPerformances()))
                    throw new Exception("operacio invalida, el show no conte actuacions");
                if(show.getPerformances().indexOf(performance) >= 0){
                    show.getPerformances().remove(performance);
                    showRepository.saveAndFlush(show);
                    return true;
                }
                else throw new Exception("operacio invalida, actuacio inexistent");
            }
            else throw new Exception("operacio invalida, show inexistent");
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


}
