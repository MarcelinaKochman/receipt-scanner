package com.wfiis.receiptscanner.ectractors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DateExtractorTest {

    @Autowired
    DateExtractor dateExtractor;

    @Test
    public void extractDate() {
        String actualDate = dateExtractor.extractDate(getRaceipt());
        assertEquals("18r04.04", actualDate);
    }

    private String getRaceipt() {
        return "on uk ia i DR\n" +
                "bo Ler ene A I / 3 i fer ory\n" +
                "4 | Ek he\n" +
                "at MIDAHID Sp. 2 0.0. Rs\n" +
                "oe 37-100 Lancut, ul. Sktadova 2 ar\n" +
                "wel DEL IKATESY ASIA ty\n" +
                "BR 37-100 tancut, PI. Sobieskiego 15 Ey ak.\n" +
                "a NIP 815-15-55-361 Fun\n" +
                "a dn. 15r06.09 wydr.6925 EEE\n" +
                "PARAGON FISkALNY RC\n" +
                "C0 ZUEMNIAKLMEODE 1,655 + 1,29 = 2,13 C REECE\n" +
                "SO MLEKOSWIEZE IL 2 1k 2,49 = 2,49 C [EE\n" +
                "CC ( CIELBASA 2uvC2AN Fo\n" +
                "So 0,582 * 14,89 = 8,67 C J\n" +
                "Comex 1159-1595 EEE\n" +
                "SL THROG POT B06 1x 3,49 - 3,09 CREE\n" +
                "CL SEMA 80sl 1x58 =579 C BEES\n" +
                "© WSLOEXRA 20068 15,49 ='5,49 ¢ REREEES\n" +
                "CL Sona LOT Duk 0,002 % 22,39 = 4,64 C EE\n" +
                "C0 WINIARY KETCH.bGD  1%2,79 = 2,09 3 BEE\n" +
                "CT mon 0,152 3,69 = 0,4 ( EEE\n" +
                "| PETRUS { 0,05 % 8,79 = 0,44 C REESE\n" +
                "© | POMIDOR MALINOWY © 0,37 * 4,99 = 1,8 CF\n" +
                "God pn p= To, BE\n" +
                "CC pai pemies 16 REE\n" +
                "I nat Razen PTU ATT\n" +
                "ol SUMA PLN 03\n" +
                "Fa un rol 00, ar LS\n" +
                "C2 JaptACOND GATORS PLN 60,20 pH 2\n" +
                "A - wv 1 ¥ AS  .\n" +
                "4 ia 000405 KniLn TTA EEE\n" +
                "CL OOM FESaRBE 696205 16k AUgSRAS?\n" +
                "i ify BIC 120257885 Ne\n" +
                "! ou’ » vy, i 4 Y, \"a\n" +
                "A Tw TX (ARE\n";
    }
}