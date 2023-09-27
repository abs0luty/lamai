package exceptions;

import java.util.List;

import ast.LocationAware;
import ast.SegmentLocation;

public class LamaExceptionWithTraceback extends RuntimeException implements LocationAware {

  private final SegmentLocation location;
  private final String message;
  private final List<TracebackItem> traceback;

  public LamaExceptionWithTraceback(SegmentLocation location, String message, List<TracebackItem> traceback) {
    super(message);
    this.location = location;
    this.message = message;
    this.traceback = traceback;
  }

  public String getMessage() {
    return message;
  }

  public List<TracebackItem> getTraceback() {
    return traceback;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }
}
