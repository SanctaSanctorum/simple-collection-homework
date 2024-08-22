package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for homework Emails collector app
 */

@DisplayName("Программа для хранения адресов электронной почты")
public class EmailListTest {
    private EmailList emailList;

    @BeforeEach
    public void setUp() {
        emailList = new EmailList();
    }

    @Test
    @DisplayName("Добавление валидного email [hello@skillbox.ru]")
    void testAddValidEmail() {
        String validEmail = "hello@skillbox.ru";
        emailList.add(validEmail);
        assertEquals(List.of(validEmail), emailList.getSortedEmails());
    }

    @Test
    @DisplayName("Добавление НЕвалидного email [hello_skillbox.ru]")
    void testAddNotValidEmailWithoutAt() {
        String notValidEmail = "hello_skillbox.ru";
        emailList.add(notValidEmail);
        assertEquals(List.of(), emailList.getSortedEmails());
    }

    @Test
    @DisplayName("Добавление НЕвалидного email [hello@skillboxru]")
    void testAddNotValidEmailWithoutDomainDot() {
        String notValidEmail = "hello@skillboxru";
        emailList.add(notValidEmail);
        assertEquals(List.of(), emailList.getSortedEmails());
    }

    @Test
    @DisplayName("Добавление дубликата валидного email [hello@skillbox.ru]")
    void testAddDuplicateValidEmail() {
        String validEmail = "hello@skillbox.ru";
        String validDuplicateEmail = "HELLO@skillbox.ru";

        emailList.add(validEmail);
        emailList.add(validDuplicateEmail);

        assertEquals(List.of(validEmail), emailList.getSortedEmails());
    }


    @Test
    @DisplayName("Проверка возврата списка адресов в алфавитном порядке")
    void testSortedEmails() {
        String firstEmail = "hello@skillbox.ru";
        String secondEmail = "asgard@yggdrasil.com";
        String thirdEmail = "hello@mail.ru";

        emailList.add(firstEmail);
        emailList.add(secondEmail);
        emailList.add(thirdEmail);

        assertEquals(List.of(secondEmail, thirdEmail, firstEmail), emailList.getSortedEmails());
    }
}