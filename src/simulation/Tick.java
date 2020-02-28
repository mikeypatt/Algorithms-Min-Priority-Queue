package simulation;

public class Tick extends AbstractEvent {

  // Constructor of tick it gets time from the superclass
  public Tick(double t) {
    super(t);
  }

  @Override
  public void happen(ParticleEventHandler h) {

    // the particle event handler reacts to the tick
    h.reactTo(this);
  }

  // a tick event is always valid
  @Override
  public boolean isValid() {
    return true;
  }
}
