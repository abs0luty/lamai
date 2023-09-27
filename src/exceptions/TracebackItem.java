package exceptions;

import ast.Identifier;
import ast.location.LocationAware;
import ast.location.SegmentLocation;

public class TracebackItem implements LocationAware {

  /**
   * Name of the function being called.
   */
  private Identifier functionNameIdentifier;

  public TracebackItem(Identifier functionNameIdentifier) {
    this.functionNameIdentifier = functionNameIdentifier;
  }

  public Identifier getFunctionNameIdentifier() {
    return functionNameIdentifier;
  }

  @Override
  public SegmentLocation getLocation() {
    return functionNameIdentifier.getLocation();
  }
}
