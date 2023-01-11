package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> video =  movieLists
                .stream()
                .flatMap(c ->c.getVideos()
                        .stream()
                        .map( x -> ImmutableMap.of("id", x.getId(), "title", x.getTitle(), "time", x.getInterestingMoments().stream().filter(z -> "Middle".equals(z.getType())).map(InterestingMoment::getTime), "boxart", x.getBoxarts().stream()
                                .reduce((box1, box2) -> box1.getWidth() < box2.getWidth() ? box1 : box2).map(BoxArt::getUrl)
                                .orElse(""))))
                .collect(Collectors.toList());
        System.out.println(video.toString());

        return video;
    }
}
