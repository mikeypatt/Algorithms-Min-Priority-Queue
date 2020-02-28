package simulation;

public class ParticleWallCollision extends Collision {

  private Wall wall;

  public ParticleWallCollision(Particle p, Wall w, double t) {
    super(t, new Particle[] {p});
    wall = w;
  }

  @Override
  public void happen(ParticleEventHandler h) {
    Particle.collide(this.particles[0], wall);
    h.reactTo(this);
  }
}
