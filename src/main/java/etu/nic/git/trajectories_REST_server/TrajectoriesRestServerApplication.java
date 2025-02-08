package etu.nic.git.trajectories_REST_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "etu.nic.git.trajectories_REST_server")
@SpringBootApplication
public class TrajectoriesRestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrajectoriesRestServerApplication.class, args);
	}

}
