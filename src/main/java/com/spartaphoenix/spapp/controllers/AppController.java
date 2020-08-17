package com.spartaphoenix.spapp.controllers;

import com.spartaphoenix.spapp.models.Article;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.sun.xml.internal.stream.writers.WriterUtility.UTF_8;

@Controller
public class AppController {

    @ModelAttribute("articles")
    public List<Article> articles() throws IOException {
        List<Article> articles = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:static/articles/c3ed5ea1-7e1b-49bd-bcc8-3ca09414ecc5.txt");
        String articleString = FileCopyUtils.copyToString(new InputStreamReader(new FileInputStream(file), UTF_8));
        articles.add(new Article(UUID.fromString("c3ed5ea1-7e1b-49bd-bcc8-3ca09414ecc5"),"Sparta Phoenix launch new website", "Keep up to date with all the latest news from Sparta Phoenix", articleString, "Jordan Olney"));
        return articles;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Article not found")
    public class ArticleNotFoundException extends RuntimeException {}

    @GetMapping(path = "/")
    public String home() {
        return "redirect:news";
    }

    @GetMapping(path = "/news")
    public String news(Model model) {
        model.addAttribute("title", "News");
        return "news";
    }

    @GetMapping("/news/{article}")
    public String article(@PathVariable("article") UUID articleId, Model model) throws IOException {
        Article article = articles()
                .stream()
                .filter(a -> a.getId().equals(articleId))
                .findFirst()
                .orElseThrow(ArticleNotFoundException::new);

        model.addAttribute("title", "News");
        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping(path = "/player-profiles")
    public String playerProfiles(Model model) {
        model.addAttribute("title", "Player Profiles");
        return "coming-soon";
    }

    @GetMapping(path = "/fixtures-and-results")
    public String fixturesAndResults(Model model) {
        model.addAttribute("title", "Fixtures and Results");
        return "coming-soon";
    }

    @GetMapping(path = "/merchandise")
    public String merchandise(Model model) {
        model.addAttribute("title", "Merchandise");
        return "coming-soon";
    }

    @GetMapping(path = "/contact-us")
    public String contactUs(Model model) {
        model.addAttribute("title", "Contact Us");
        return "contact-us";
    }
}
