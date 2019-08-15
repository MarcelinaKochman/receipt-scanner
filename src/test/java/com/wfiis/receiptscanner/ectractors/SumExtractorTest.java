package com.wfiis.receiptscanner.ectractors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SumExtractorTest {

    @Autowired
    SumExtractor sumExtractor;

    @Test
    public void extractSum() {
        String text = "_— _—\n" +
                "=\n" +
                "— —\n" +
                "rl\n" +
                "=\n" +
                "/ Rossmann SDP Sklep nr 0741\n" +
                "ul. Szymanowskiego 14\n" +
                "30-047 Krakow\n" +
                "Nr rej. 000003093\n" +
                "727-001-91-83\n" +
                "d 2018-05-22 421059\n" +
                "PARAGON FISKALNY\n" +
                "L'OREAL MASKA WYG\\AX 1 %34,99 34,99A\n" +
                "HADALABO WHITE SUNAX 1 47.99 47,99A\n" +
                "LOREAL ZEL WYGELAVAX 1 x33.99 33,99\n" +
                "LOREAL ZEL OCZYS\\AX 1 x33,99 33,997\n" +
                "COLGATE NATURAL WAAX 1 x14,99 14,997\n" +
                "} TOREBKA 28/50 - S\\AX 1 x0,29 0,297\n" +
                "Rabat Pielegnacja2+2Gratis -67,98A\n" +
                "SPRZEDAZ OPODATK. A 98,26\n" +
                "PTU A 28,00 % 18,37\n" +
                "SUMA PTU 18,37\n" +
                "SUMA PLN 98, 26\n" +
                "00532 #Kasa 22 Kasjer Fo 208 17:27\n" +
                "00 He ASB DESGE CZF 20470012\n" +
                "J= BCY 11191325\n" +
                "Ni SYS. 1504\n" +
                "Karta Karta ptatnicza 98.26 PLN\n" +
                "7, 98\n" +
                "Udzielono tacznie rabatow: 8\n" +
                "zi gkudem za 2aKuPY =)\n" +
                "-_—\n" +
                "»\n" +
                "oF A\n" +
                "gy\n";

        String sum = sumExtractor.extractSum(text);

        assertEquals(sum, "98.26");
    }
}