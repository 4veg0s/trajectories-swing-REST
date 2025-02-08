package etu.nic.git.trajectories_REST_server.repository;

import etu.nic.git.trajectories_REST_server.model.Trajectory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajectoryRepository extends JpaRepository<Trajectory, Long> {
}
