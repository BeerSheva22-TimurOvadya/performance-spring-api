package telran.spring.performance;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("performance")
public class SpringApiController {
    final MongoTemplate mongoTemplate;

    @GetMapping("total")
    ResultDto getTotal(long count) {
        Instant start = Instant.now();
        long total = 0;
        for (long i = 0; i < count; i++) {
            total++;
        }
        return new ResultDto("spring-api", total, ChronoUnit.MILLIS.between(start, Instant.now()));
    }

    @GetMapping("students")
    ResultDto getStudents() {
        Instant start = Instant.now();
        List<Document> students = mongoTemplate.findAll(Document.class, "students");
        return new ResultDto("spring-api", students.size(), ChronoUnit.MILLIS.between(start, Instant.now()));
    }
}
