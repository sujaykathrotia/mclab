package matlab;

import beaver.Symbol;

public class TextPosition implements Comparable<TextPosition> {
    private final int line;
    private final int col;

    public TextPosition(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public TextPosition(int pos) {
        this.line = Symbol.getLine(pos);
        this.col = Symbol.getColumn(pos);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return col;
    }

    public int compareTo(TextPosition o) {
        if(o == null) {
            throw new NullPointerException("Cannot compareTo a null SourcePosition.");
        }
        int lineDif = this.line - o.line;
        if(lineDif != 0) {
            return lineDif;
        } else {
            int colDif = this.col - o.col;
            return colDif;
        }
    }

    public String toString() {
        return "(" + line + ", " + col + ")";
    }
}