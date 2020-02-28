package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler {

  private static final long FRAME_INTERVAL_MILLIS = 40;

  private final ParticlesModel model;
  private final ParticlesView screen;
  private MinPriorityQueue<Event> queue;
  private double clock = 0;

  /** Constructor. */
  public ParticleSimulation(String name, ParticlesModel m) {
    this.model = m;
    screen = new ParticlesView(name, m);
    queue = new MinPriorityQueue<Event>();
    queue.add(new Tick(1));
    model.predictAllCollisions(0, queue);
  }

  /** Runs the simulation. */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeAndWait(screen);
    } catch (InvocationTargetException | InterruptedException e) {
      e.printStackTrace();
    }

    while (!queue.isEmpty()) {

      Event newevent = queue.remove();
      if (newevent.isValid()) {

        double dt = newevent.time() - clock;
        clock = newevent.time();
        model.moveParticles(dt);
        newevent.happen(this);
      }
    }
  }

  @Override
  public void reactTo(Tick tick) {
    try {
      Thread.sleep(FRAME_INTERVAL_MILLIS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    screen.update();
    queue.add(new Tick(tick.time() + 1));
  }

  @Override
  public void reactTo(Collision c) {
    // update list
    for (Particle p : c.getParticles()) {
      model.predictCollisions(p, clock, queue);
    }
  }
}
