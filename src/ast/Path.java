package ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ast.location.LocationAware;
import ast.location.SegmentLocation;

public final class Path implements LocationAware {

  /**
   * A list of path segments (identifiers separated by dots).
   */
  private List<Identifier> segments;

  public Path() {
    this.segments = new ArrayList<>();
  }

  public Path(Identifier segment) {
    this.segments = new ArrayList<>();
    this.segments.add(segment);
  }

  public Path(Identifier... segments) {
    this.segments = new ArrayList<>();
  }

  public Path(List<Identifier> segments) {
    this.segments = segments;
  }

  public List<Identifier> getSegments() {
    return segments;
  }

  public void setSegments(List<Identifier> segments) {
    this.segments = segments;
  }

  public void addSegment(Identifier segment) {
    segments.add(segment);
  }

  public void addSegments(Collection<Identifier> segments) {
    this.segments.addAll(segments);
  }

  @Override
  public SegmentLocation getLocation() {
    return new SegmentLocation(
        segments.get(0).getLocation().getFirstByteLocation(),
        segments.get(segments.size() - 1).getLocation().getLastByteLocation());
  }
}
