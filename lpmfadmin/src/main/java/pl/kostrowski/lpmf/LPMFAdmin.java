package pl.kostrowski.lpmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "pl.kostrowski.lpmf")
public class LPMFAdmin {

    public static void main(String[] args) {
        SpringApplication.run(LPMFAdmin.class, args);
    }

}
