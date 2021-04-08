package com.map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MapDemo {
    public static void main(String args[]){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        //Creating Question
        Question q1= new Question();
        q1.setQuestionId(1212);
        q1.setQuestion("what is java?");

        //Creating Answer
        Answer ans = new Answer();
        ans.setAnswerId(343);
        ans.setAnswer("Java is a programming language");
        ans.setQ(q1);

        q1.setAnswer(ans);

        //Creating second instance
        Question q2= new Question();
        q2.setQuestionId(242);
        q2.setQuestion("what is collection?");

        //Creating Answer
        Answer ans1 = new Answer();
        ans1.setAnswerId(344);
        ans1.setAnswer("Api to work with java objects");
        ans1.setQ(q2);
        q2.setAnswer(ans1);


        //For saving the question we will use save method to use that save method , we use session
        //session
        Session s=factory.openSession();
        Transaction tx = s.beginTransaction();

        //save
        s.save(q1);
        s.save(q2);
        s.save(ans);
        s.save(ans1);

        tx.commit(); //Because we want changes in physical database

        //Fetching
        Question newQ= (Question) s.get(Question.class,242);
        System.out.println(newQ.getQuestion());
        System.out.println(newQ.getAnswer().getAnswer());
        s.close();
        factory.close();

    }
}
