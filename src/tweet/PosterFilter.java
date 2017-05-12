package tweet;

import java.util.function.Predicate;

/**
 * Created by kabaly on 5/8/17.
 */
public class PosterFilter implements Predicate<Tweet>{

    String poster;

    public PosterFilter(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean test(Tweet tweet) {
        return tweet.getPoster().equalsIgnoreCase(poster);
    }
}
