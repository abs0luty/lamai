package ast;

public final class Identifier implements LocationAware {

    /**
     * A name of an identifier.
     */
    String name;

    /**
     * A location of an identifier.
     */
    SegmentLocation location;

    public Identifier(String name, SegmentLocation location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    @Override
    public SegmentLocation getLocation() {
        return location;
    }
}
