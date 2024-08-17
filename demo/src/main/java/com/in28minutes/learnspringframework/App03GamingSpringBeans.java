package com.in28minutes.learnspringframework;


import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.demo.game"})
public class App03GamingSpringBeans {
   public App03GamingSpringBeans() {
   }

   @Bean
   public GameRunner gameRunner(GamingConsole game) {
      GameRunner gameRunner = new GameRunner(game);
      return gameRunner;
   }

   public static void main(String[] args) {
    

      try {
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{App03GamingSpringBeans.class});

         try {
            ((GamingConsole)context.getBean(GamingConsole.class)).up();
            ((GameRunner)context.getBean(GameRunner.class)).run();
         } finally {
            if (context != null) {
               context.close();
            }

         }
      }catch (Exception e) {
		// TODO: handle exception
	}
   }
}


       
   
