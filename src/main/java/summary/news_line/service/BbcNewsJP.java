package summary.news_line.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class BbcNewsJP {

    public List<NewsArticle> fetchNewsJP() {
        try {
            URL url = new URL("https://feeds.bbci.co.uk/japanese/rss.xml");
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(url));

            List<SyndEntry> entries = feed.getEntries();

            List<NewsArticle> result = new ArrayList<>();
            int count = Math.min(3, entries.size());
            for (int i = 0; i < count; i++) {
                SyndEntry entry = entries.get(i);
                result.add(new NewsArticle(entry.getTitle(), entry.getLink()));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
