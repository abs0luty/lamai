package ast.location;

public final class SegmentLocation {

    private final ByteLocation firstByteLocation, lastByteLocation;

    public SegmentLocation(ByteLocation firstByteLocation, ByteLocation lastByteLocation) {
        this.firstByteLocation = firstByteLocation;
        this.lastByteLocation = lastByteLocation;
    }

    public SegmentLocation(ByteLocation firstByteLocation) {
        this(firstByteLocation, firstByteLocation.locationOfNextByte());
    }

    public ByteLocation getFirstByteLocation() {
        return firstByteLocation;
    }

    public ByteLocation getLastByteLocation() {
        return lastByteLocation;
    }

    /**
     * Checks if the start and end positions of a code segment aren't on the
     * same line.
     *
     * @return true if the start and end line numbers aren't on the same
     *         line, false otherwise
     */
    public boolean isMultiLine() {
        return !isSingleLine();
    }

    /**
     * Checks if the start and end positions of a code segment are on the
     * same line.
     *
     * @return true if the start and end line numbers are on the same
     *         line, false otherwise
     */
    public boolean isSingleLine() {
        return firstByteLocation.getLineNumber() == lastByteLocation.getLineNumber();
    }

    /**
     * @return the path of the file containing the code segment
     */
    public String filepath() {
        return firstByteLocation.getFilepath();
    }
}