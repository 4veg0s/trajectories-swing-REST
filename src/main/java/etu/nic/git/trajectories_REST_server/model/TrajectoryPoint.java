package etu.nic.git.trajectories_REST_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Objects;

@Entity
@AllArgsConstructor
@Table(name = "trajectory_points")
public class TrajectoryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double time;
    private double coordinateX;
    private double coordinateY;
    private double coordinateZ;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    @ManyToOne
    @JoinColumn(name = "trajectory_id", nullable = false)
    private Trajectory trajectory;

    public TrajectoryPoint() {
    }

    public TrajectoryPoint(double time,
                           double coordinateX,
                           double coordinateY,
                           double coordinateZ,
                           double velocityX,
                           double velocityY,
                           double velocityZ) {
        this.time = time;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getCoordinateZ() {
        return coordinateZ;
    }

    public void setCoordinateZ(double coordinateZ) {
        this.coordinateZ = coordinateZ;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(double velocityZ) {
        this.velocityZ = velocityZ;
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public long getTrajectoryId() {
        return this.trajectory.getId();
    }

    public void replicate(TrajectoryPoint newPoint) {
        this.id = newPoint.getId();
        this.time = newPoint.getTime();
        this.velocityX = newPoint.getVelocityX();
        this.velocityY = newPoint.getVelocityY();
        this.velocityZ = newPoint.getVelocityZ();
        this.coordinateX = newPoint.getCoordinateX();
        this.coordinateY = newPoint.getCoordinateY();
        this.coordinateZ = newPoint.getCoordinateZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrajectoryPoint that = (TrajectoryPoint) o;
        return id == that.id && Double.compare(time, that.time) == 0 && Double.compare(coordinateX, that.coordinateX) == 0 && Double.compare(coordinateY, that.coordinateY) == 0 && Double.compare(coordinateZ, that.coordinateZ) == 0 && Double.compare(velocityX, that.velocityX) == 0 && Double.compare(velocityY, that.velocityY) == 0 && Double.compare(velocityZ, that.velocityZ) == 0 && Objects.equals(trajectory, that.trajectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, coordinateX, coordinateY, coordinateZ, velocityX, velocityY, velocityZ, trajectory);
    }
}
