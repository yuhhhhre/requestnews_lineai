package summary.news_line.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NewsFetchService {
    private final BbcNewsJP bbcNewsJP;
    private final BbcNewsEN bbcNewsEN;

    public NewsFetchService(BbcNewsJP bbcNewsJP, BbcNewsEN bbcNewsEN) {
        this.bbcNewsJP = bbcNewsJP;
        this.bbcNewsEN = bbcNewsEN;
    }
    
    public List<NewsArticle> fetchJapaneseNews() {
        return bbcNewsJP.fetchNewsJP();
    }

    public List<NewsArticle> fetchEnglishNews() {
        return bbcNewsEN.fetchNewsEN();
    }
}
