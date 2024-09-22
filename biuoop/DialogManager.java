package biuoop;

public interface DialogManager {
   String showQuestionDialog(String var1, String var2, String var3);

   void showInformationDialog(String var1, String var2);

   void showWarningDialog(String var1, String var2);

   void showErrorDialog(String var1, String var2);

   boolean showConfirmationDialog(String var1, String var2);

   boolean showYesNoDialog(String var1, String var2);
}
