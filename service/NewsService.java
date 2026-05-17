package service;

import enums.Classification;
import model.News;

import java.util.ArrayList;
import java.util.List;


public class NewsService {
    private final List<News> newsList = new ArrayList<>();
    private final NewsAnalyzer analyzer = new NewsAnalyzer();

    public Classification analyzeNewsText(String text) {
        return analyzer.analyze(text);
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
