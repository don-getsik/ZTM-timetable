package pl.edu.wat.wcy.isi.prz.database;

import pl.edu.wat.wcy.isi.prz.App;

public class PrintStop {

    String print(String name, String locale, String direction,
                 double X, double Y, String stableLines, String requestLines,
                 String endLines, String forGetOffLines, String forGetInLines) {
        StringBuilder s = new StringBuilder();
        s.append(App.getText("print.stop")).append(" ").append(name).append("\n");
        s.append(App.getText("print.street")).append(" ").append(locale).append("\n");
        s.append(App.getText("print.direction")).append(" ").append(direction).append("\n");
        s.append(App.getText("print.xy")).append(" ").append(Y).append(", ").append(X).append("\n");
        if (stableLines != null)
            s.append(App.getText("print.stable")).append(" ").append(stableLines).append("\n");
        if (requestLines != null)
            s.append(App.getText("print.request")).append(" ").append(requestLines).append("\n");
        if (endLines != null)
            s.append(App.getText("print.endlines")).append(" ").append(endLines).append("\n");
        if (forGetOffLines != null)
            s.append(App.getText("print.getoff")).append(" ").append(forGetOffLines).append("\n");
        if (forGetInLines != null)
            s.append(App.getText("print.geton")).append(" ").append(forGetInLines).append("\n");
        return s.toString();
    }
}
