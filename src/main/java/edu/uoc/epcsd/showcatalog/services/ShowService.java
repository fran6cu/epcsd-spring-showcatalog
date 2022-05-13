package edu.uoc.epcsd.showcatalog.services;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.entities.Show;


import java.util.List;

public interface ShowService {

    public boolean delShow(Long idShow);

    public Show addShow(Show show);

    public Show queryShowDetails(Long idShow);

    public List<Show> queryShowByName(String nom);

    public List<Show> queryShowByCategory(List<Long> catIds);

    public List<Show> queryAllShows();

    public boolean addAct(Long idShow, Performance performance) throws Exception;

    public boolean delAct(Long idShow, Performance performance) throws Exception;
}
