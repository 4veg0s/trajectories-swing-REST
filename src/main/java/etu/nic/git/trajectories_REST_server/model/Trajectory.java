package etu.nic.git.trajectories_REST_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "trajectories")
public class Trajectory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fileName;

    @OneToMany(mappedBy = "trajectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrajectoryPoint> trajectoryPoints = new ArrayList<>();

    public Trajectory() {
    }

    public Trajectory(String fileName) {
        this.fileName = fileName;
    }

    public Trajectory(String fileName, List<TrajectoryPoint> trajectoryPoints) {
        this.fileName = fileName;
        this.trajectoryPoints = trajectoryPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<TrajectoryPoint> getTrajectoryPoints() {
        return trajectoryPoints;
    }

    public void setTrajectoryPoints(List<TrajectoryPoint> trajectoryPoints) {
        this.trajectoryPoints.clear();
        this.trajectoryPoints.addAll(trajectoryPoints);
        this.trajectoryPoints.forEach(point -> point.setTrajectory(this));
    }
}
