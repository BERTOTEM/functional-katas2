package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<MovieList> movies = DataUtil.getMovieLists();
        String largestboxart = movies
                .stream().flatMap(x ->x.getVideos().stream())
                .flatMap(z ->z.getBoxarts().stream())
                .reduce((box1, box2) -> box1.getWidth() > box2.getWidth() ? box1 : box2)
                .map(BoxArt::getUrl)
                .orElse("");
        System.out.println(largestboxart);
        return largestboxart;
    }
}
