package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> video =  movieLists
                .stream()
                .flatMap(c ->c.getVideos()
                        .stream()
                        .map( x -> ImmutableMap
                                .of("id", x.getId(), "title", x.getTitle(), "boxart", x.getBoxarts().stream().filter(z -> z.getWidth().equals(150) && z.getHeight().equals(200)).map(BoxArt::getUrl).findFirst().orElse(""))))
                .collect(Collectors.toList());
        System.out.println(video.toString());
        return video;
    }
}
