package etu.nic.git.trajectories_REST_server.repository;

import etu.nic.git.trajectories_REST_server.model.TrajectoryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrajectoryPointRepository extends JpaRepository<TrajectoryPoint, Long> {
    List<TrajectoryPoint> findByTrajectory_Id(Long trajectoryId);
}
