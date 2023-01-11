package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> video =  movieLists
                .stream()
                .flatMap(c ->c.getVideos()
                        .stream()
                        .map( x -> ImmutableMap.of("id", x.getId(), "title", x.getTitle(), "boxart", x.getBoxarts().stream()
                                        .reduce((box1, box2) -> box1.getWidth() < box2.getWidth() ? box1 : box2).map(BoxArt::getUrl)
                                .orElse(""))))
                .collect(Collectors.toList());

        System.out.println(video.toString());

        return video;
    }
}
