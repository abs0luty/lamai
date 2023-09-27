package error;

import ast.Identifier;
import ast.LocationAware;
import ast.SegmentLocation;

public record TracebackItem(
    /**
     * Name of the function being called.
     */
    Identifier functionNameIdentifier) implements LocationAware {

  @Override
  public SegmentLocation getLocation() {
    return functionNameIdentifier.getLocation();
  }
}
