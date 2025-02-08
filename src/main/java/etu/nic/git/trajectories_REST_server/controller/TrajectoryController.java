package etu.nic.git.trajectories_REST_server.controller;

import etu.nic.git.trajectories_REST_server.model.Trajectory;
import etu.nic.git.trajectories_REST_server.model.TrajectoryPoint;
import etu.nic.git.trajectories_REST_server.repository.TrajectoryPointRepository;
import etu.nic.git.trajectories_REST_server.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TrajectoryController {
    @Autowired
    TrajectoryPointRepository trajectoryPointRepository;

    @Autowired
    TrajectoryRepository trajectoryRepository;

    @GetMapping("/trajectories")
    public ResponseEntity<List<Trajectory>> getAllTrajectories() {
        try {
            List<Trajectory> trajectories = new ArrayList<>();

            trajectoryRepository.findAll().forEach(trajectories::add);

            if (trajectories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(trajectories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trajectories/{id}")
    public ResponseEntity<Trajectory> getTrajectoryById(@PathVariable("id") long id) {
        Optional<Trajectory> tutorialData = trajectoryRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trajectories")
    public ResponseEntity<Trajectory> createTrajectory(@RequestBody Trajectory trajectory) {
        try {
            // Сначала сохраняем только Trajectory
            Trajectory savedTrajectory = trajectoryRepository.save(new Trajectory(trajectory.getFileName()));

            // Привязываем точки к сохранённой траектории
            trajectory.getTrajectoryPoints().forEach(point -> point.setTrajectory(savedTrajectory));

            // Сохраняем точки
            List<TrajectoryPoint> savedPoints = trajectoryPointRepository.saveAll(trajectory.getTrajectoryPoints());

            // Устанавливаем сохранённые точки обратно в Trajectory
            savedTrajectory.setTrajectoryPoints(savedPoints);

            return new ResponseEntity<>(savedTrajectory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/trajectories/{id}")
    public ResponseEntity<Trajectory> updateTrajectory(@PathVariable("id") long id, @RequestBody Trajectory trajectory) {
        Optional<Trajectory> trajectoryData = trajectoryRepository.findById(id);

        if (trajectoryData.isPresent()) {
            Trajectory _trajectory = trajectoryData.get();
            _trajectory.setFileName(trajectory.getFileName());

            trajectoryRepository.save(_trajectory); // Сохраняем траекторию перед использованием

            // Удаляем старые точки
            List<TrajectoryPoint> relatedPoints = trajectoryPointRepository.findByTrajectory_Id(_trajectory.getId());
            trajectoryPointRepository.deleteAll(relatedPoints);

            // Устанавливаем для новых точек ссылку на сохранённую траекторию
            List<TrajectoryPoint> newPoints = trajectory.getTrajectoryPoints();
            newPoints.forEach(point -> point.setTrajectory(_trajectory));

            // Сохраняем обновлённые точки
            List<TrajectoryPoint> savedPoints = trajectoryPointRepository.saveAll(newPoints);
            _trajectory.setTrajectoryPoints(savedPoints);

            return new ResponseEntity<>(trajectoryRepository.save(_trajectory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/trajectories/{id}")
    public ResponseEntity<HttpStatus> deleteTrajectory(@PathVariable("id") long id) {
        try {
            trajectoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
