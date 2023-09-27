package ast;

import java.util.List;

public record Path(
    /**
     * A list of path segments (identifiers separated by dots).
     */
    List<Identifier> segments) implements LocationAware {

  @Override
  public SegmentLocation getLocation() {
    return new SegmentLocation(
        segments.get(0).getLocation().firstByteLocation(),
        segments.get(segments.size() - 1).getLocation().lastByteLocation());
  }
}
