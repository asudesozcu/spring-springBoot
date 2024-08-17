package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
   private GamingConsole game;

   public GameRunner(@Qualifier("MarioGameQualifier") GamingConsole game) {
      this.game = game;
   }

   public void run() {
      System.out.println("runnging game:" + String.valueOf(this.game));
   }
}
