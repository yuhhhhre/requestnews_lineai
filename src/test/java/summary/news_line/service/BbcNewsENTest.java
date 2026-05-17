/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package summary.news_line.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
 
/**
 *
 * @author eshimayuusuke
 */
public class BbcNewsENTest {

    public BbcNewsENTest() {
    }

    @Test
    public void testFetchNewsEN() {
        System.out.println("fetchNewsEN");
        BbcNewsEN instance = new BbcNewsEN();
        List<NewsArticle> expResult = null;
        List<NewsArticle> articles = instance.fetchNewsEN();
        StringBuilder replyBuilder = new StringBuilder();
        for (NewsArticle article : articles) {
                replyBuilder
                        .append("■ ")
                        .append(article.getTitle())
                        .append("\n")
                        .append(article.getLink())
                        .append("\n");
            }
        assertEquals(expResult, replyBuilder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}