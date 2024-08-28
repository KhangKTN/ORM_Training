package org.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Scanner scanner = new Scanner(System.in);
            String date = "2024-09-05";

            LocalDate localDate = LocalDate.parse(date);
            System.out.println(localDate);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
