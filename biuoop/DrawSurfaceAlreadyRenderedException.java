package biuoop;

public class DrawSurfaceAlreadyRenderedException extends RuntimeException {
   public DrawSurfaceAlreadyRenderedException() {
   }

   public DrawSurfaceAlreadyRenderedException(String message) {
      super(message);
   }

   public DrawSurfaceAlreadyRenderedException(String message, Throwable cause) {
      super(message, cause);
   }

   public DrawSurfaceAlreadyRenderedException(Throwable cause) {
      super(cause);
   }

   public DrawSurfaceAlreadyRenderedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
