package biuoop;

public class Sleeper {
   public void sleepFor(long milliseconds) {
      try {
         Thread.sleep(milliseconds);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

   }
}
