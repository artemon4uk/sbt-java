package handlers;

import java.util.Collection;

public class StringWorker {
    private final String TAB = "    ";
    private String indention;

    public StringWorker(String indention) {
        this.indention = indention;
    }

    protected void addStartingPart(StringBuilder stringBuilder, String starting) {
        stringBuilder
                .append(starting)
                .append('\n');
        indention += TAB;
    }

    protected void addInformativePart(StringBuilder stringBuilder, String info) {
        stringBuilder
                .append(indention)
                .append(info)
                .append('\n');
    }

    protected void addEndPart(StringBuilder stringBuilder, String ending) {
        indention = indention.substring(0, indention.length() - TAB.length());
        stringBuilder
                .append(indention)
                .append(ending);
    }

    protected String join(Collection<String> elements) {
        return String.join('\n' + indention, elements);
    }
}
