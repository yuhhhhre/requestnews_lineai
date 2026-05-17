/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package summary.news_line.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
/**
 *
 * @author eshimayuusuke
 */
public class SummaryServiceTest {

     public SummaryServiceTest() {
    }

    /**
     * Test of summary method, of class SummaryService.
     */
    @Test
    public void testSummary() {
        System.out.println("summary");
        String title = "";
        String link = "";
        SummaryService instance = null;
        String expResult = "";
        String result = instance.summary(title, link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}