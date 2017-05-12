package tweet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet(name = "TweetServlet")
public class TweetServlet extends HttpServlet {

    private List<Tweet> tweetList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("UTF-8");
        PrintWriter out = response.getWriter();

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        String poster = request.getParameter("name");
        String content = request.getParameter("message");

        if (poster != "" && content != "") {
            tweetList.add(new Tweet(poster, content, ts));
        } else {
            response.sendRedirect("error.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("UTF-8");
        PrintWriter out = response.getWriter();

        StringBuilder message = new StringBuilder();

        message.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Messages</title>\n" +
                "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
                "</head>" +
                "<body>\n");

        String filter = request.getParameter("filter");

        List<Tweet> filteredTweetList = new ArrayList<>();
        if(filter != null && !filter.isEmpty()){
            //filteredTweetList = tweetList.stream().filter(new PosterFilter(filter)).collect(Collectors.toList());
            filteredTweetList = tweetList.stream().
                    filter(zjukgfzu -> zjukgfzu.getPoster().equalsIgnoreCase(filter)).
                    map(tweet -> {tweet.setPoster(tweet.getPoster().toUpperCase()); return tweet;}).
                    collect(Collectors.toList());
            //filteredTweetList = tweetList.stream().map(tweet -> {tweet.setPoster(tweet.getPoster().toUpperCase()); return tweet;}).collect(Collectors.toList());

            /*for (Tweet tweet : tweetList) {
                if (tweet.getPoster().equalsIgnoreCase(filter)) {
                    filteredTweetList.add(tweet);
                }
            }*/
        } else {
            filteredTweetList = tweetList;
        }

        String limit = request.getParameter("limit");
        int max = 3;
        if (limit != null && !limit.isEmpty()) {
            max = Integer.valueOf(limit).intValue();
        }
        max = Math.min(max, filteredTweetList.size());
        for (int i = 0; i < max; i++) {
            Tweet tweet = filteredTweetList.get(i);
            message.append("<div align=\"center\">" + getTweetToHtml(i+1, tweet) + "<br>");
            message.append("_____________________________________________________________________________ </div>");
        }
        message.append("</body>\n" +
                "</html>");
        out.print(message);

    }

    public String getTweetToHtml(int count, Tweet tweet) {
        return "<p><h2>" + count +
        ".; <strong>" + tweet.getPoster() +
                "</strong>: </h2><p><h3>" + tweet.getContent() +
                "</h3></p>" + "<" + tweet.getTimestamp() + ">";
    }
}
