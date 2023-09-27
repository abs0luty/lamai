package ast;

public record Identifier(
    /**
     * A name of an identifier.
     */
    String name,

    /**
     * A location of an identifier.
     */
    SegmentLocation location) implements LocationAware {

  @Override
  public SegmentLocation getLocation() {
    return location;
  }
}
