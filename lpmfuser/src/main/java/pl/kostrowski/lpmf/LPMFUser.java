package pl.kostrowski.lpmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "pl.kostrowski.lpmf")
public class LPMFUser {

    public static void main(String[] args) {
        SpringApplication.run(LPMFUser.class, args);
    }

}
