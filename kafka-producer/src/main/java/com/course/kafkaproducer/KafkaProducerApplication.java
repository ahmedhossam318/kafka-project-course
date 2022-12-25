package com.course.kafkaproducer;

import com.course.kafkaproducer.producer.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;

    //	@Autowired
//	HelloKafkaProducer  helloKafkaProducer;
//	@Autowired
//	KafkaKeyProducer kafkaKeyProducer;
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		helloKafkaProducer.sendHello("Ahmed " + Math.random());
//		for (int i = 0 ; i <10000 ; i++){
//			var key = "key-" + (i%3);
//			var data = "Data " + i + " with key " + key ;
//			kafkaKeyProducer.send(key, data);
//			Thread.sleep(500);
//		}
//        for (int i = 0; i < 5; i++) {
//            Employee employee = new Employee((long) i, "Employee " + i, new Date());
//            employeeJsonProducer.sendMessage(employee);
//        }
    }
}
