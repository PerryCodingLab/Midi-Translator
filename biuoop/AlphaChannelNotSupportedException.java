package biuoop;

public class AlphaChannelNotSupportedException extends RuntimeException {
   public AlphaChannelNotSupportedException() {
   }

   public AlphaChannelNotSupportedException(String message) {
      super(message);
   }

   public AlphaChannelNotSupportedException(String message, Throwable cause) {
      super(message, cause);
   }

   public AlphaChannelNotSupportedException(Throwable cause) {
      super(cause);
   }

   protected AlphaChannelNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
