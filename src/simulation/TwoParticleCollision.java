package simulation;

public class TwoParticleCollision extends Collision {

  public TwoParticleCollision(Particle p1, Particle p2, double t) {
    super(t, new Particle[] {p1, p2});
  }

  @Override
  public void happen(ParticleEventHandler h) {
    Particle.collide(this.particles[0], this.particles[1]);
    h.reactTo(this);
  }
}
