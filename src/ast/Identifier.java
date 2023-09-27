package ast;

public final class Identifier implements LocationAware {

    private final String name;
    private final SegmentLocation location;

    public Identifier(String name, SegmentLocation location) {
        this.name = name;
        this.location = location;
    }

    /**
     * @return a name of an identifier
     */
    public String getName() {
        return name;
    }

    @Override
    public SegmentLocation getLocation() {
        return location;
    }
}
