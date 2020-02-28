package simulation;

public abstract class AbstractEvent implements Event {

  /** Constructor for AbstractEvent. */
  private double time;

  public AbstractEvent(double t) {

    time = t;
  }

  /** Returns the time (in ticks) at which this event will occur. */
  @Override
  public double time() {

    return time;
  }

  /** Compares this object with the specified Event. */
  @Override
  public int compareTo(Event that) {

    if (time > that.time()) {
      return 1;
    }
    if (time < that.time()) {
      return -1;
    }
    return 0;
  }
}
