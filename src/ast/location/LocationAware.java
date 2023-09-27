package ast.location;

public interface LocationAware {

    /**
     * @return location of a code segment associated with a node
     */
    public SegmentLocation getLocation();
}
