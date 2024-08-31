package com.in28minutes.learnspringframework.game;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Qualifier("MarioGameQualifier")
public class MarioGame implements GamingConsole {
   public MarioGame() {
   }

   public void up() {
      System.out.println("Jump");
   }

   public void down() {
      System.out.println("Go into a hole");
   }

   public void left() {
      System.out.println("Go back");
   }

   public void right() {
      System.out.println("Accelerate");
   }
}