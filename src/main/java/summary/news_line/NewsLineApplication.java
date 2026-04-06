package summary.news_line;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
@EnableScheduling
@EnableAsync
public class NewsLineApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsLineApplication.class, args);
	}

	@GetMapping
	public String helloworld() {
		return "Hello lineNews ";
	}

	@GetMapping("/health")
	public String health(){
		return "OK";
	}
}
