package gui.guiutils.popup;

public class DefaultOptionsUtils {
    public static String convertTextToHTMLFormat(String text) {
        String formattedText = "<html>" + text + "</html>";
        return formattedText.replaceAll("\n", "<br>");
    }
}
