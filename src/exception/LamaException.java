package exception;

import ast.LocationAware;
import ast.SegmentLocation;

public class LamaException extends Exception implements LocationAware {

  private final SegmentLocation location;
  private final String message;

  public LamaException(SegmentLocation location, String message) {
    super(message);
    this.location = location;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }
}
