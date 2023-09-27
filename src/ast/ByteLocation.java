package ast;

public final class ByteLocation {
    private String filepath;
    private int lineNumber;
    private int column;
    private int offset;

    public ByteLocation(String filepath,
            int lineNumber,
            int column,
            int offset) {
        this.filepath = filepath;
        this.lineNumber = lineNumber;
        this.column = column;
        this.offset = offset;
    }

    public String getFilepath() {
        return filepath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumn() {
        return column;
    }

    public int getOffset() {
        return offset;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int lineIndex() {
        return lineNumber - 1;
    }

    /**
     * <p>
     * Retrieves the location of the next byte with the given offset.
     * </p>
     * <p>
     * <b>NOTE</b>: Returns location of the byte with incremented column number, but
     * line numbers
     * are not processed.
     * </p>
     *
     * @param offset the offset of the byte relative to the current one
     * @return the location of the next byte
     */
    public ByteLocation locationOfNextByte(int offset) {
        return new ByteLocation(filepath, lineNumber, column + offset, this.offset + offset);
    }

    /**
     * <p>
     * Retrieves the location of the next byte.
     * </p>
     * <p>
     * <b>NOTE</b>: Returns location of the byte with incremented column number, but
     * line numbers
     * are not processed.
     * </p>
     *
     * @return the location of the next byte
     */
    public ByteLocation locationOfNextByte() {
        return new ByteLocation(filepath, lineNumber, column + 1, offset + 1);
    }

    /**
     * <p>
     * Retrieves the location of the previous byte with the given offset.
     * </p>
     * <p>
     * <b>NOTE</b>: Returns location of the byte with decremented column number, but
     * line numbers
     * are not processed.
     * </p>
     *
     * @param offset the offset of the byte relative to the current one
     * @return the location of the previous byte
     */
    public ByteLocation locationOfPreviousByte(int offset) {
        return new ByteLocation(filepath, lineNumber, column - offset, this.offset - offset);
    }

    /**
     * <p>
     * Retrieves the location of the previous byte.
     * </p>
     * <p>
     * <b>NOTE</b>: Returns location of the byte with decremented column number, but
     * line numbers
     * are not processed.
     * </p>
     *
     * @return the location of the previos byte
     */
    public ByteLocation locationOfPreviousByte() {
        return new ByteLocation(filepath, lineNumber, column - 1, offset - 1);
    }
}
