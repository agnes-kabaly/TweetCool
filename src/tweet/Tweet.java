package tweet;

import java.util.Date;

public class Tweet {
    private String poster;
    private String content;
    private Date timestamp;

    public Tweet(String poster, String content, Date timestamp) {
        this.poster = poster;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getPoster() {
        return poster;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "poster='" + poster + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
