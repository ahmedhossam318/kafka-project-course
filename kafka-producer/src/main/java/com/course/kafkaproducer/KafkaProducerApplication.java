package com.course.kafkaproducer;

import com.course.kafkaproducer.producer.*;
import com.course.kafkaproducer.service.ImageService;
import com.course.kafkaproducer.service.InvoiceService;
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

    @Autowired
    private InvoiceProducer invoiceProducer;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private FoodOrderProducer foodOrderProducer;

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageProducer imageProducer;
    @Autowired
    private SimpleNumberProducer simpleNumberProducer;

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

//        var chickenOrder = new FoodOrder(3, "chicken");
//        var fishOrder = new FoodOrder(10, "fish");
//        var pizzaOrder = new FoodOrder(3, "pizza");
//        foodOrderProducer.send(chickenOrder);
//        foodOrderProducer.send(fishOrder);
//        foodOrderProducer.send(pizzaOrder);
        //--------------------------------------
//        for (int i = 100; i < 103; i++) {
//            SimpleNumber number = new SimpleNumber(i);
//            simpleNumberProducer.sendMessage(number);
//        }

        //--------------------------------------
//        var image1 = imageService.generateImage("jpg");
//        var image2 = imageService.generateImage("svg");
//        var image3 = imageService.generateImage("png");
//
//        imageProducer.send(image1);
//        imageProducer.send(image2);
//        imageProducer.send(image3);
        //--------------------------------------

        for (int i = 0; i < 10; i++) {
            var invoice = invoiceService.generateInvoice();
            if (i >= 5) {
                invoice.setAmount(-1);
            }
            invoiceProducer.send(invoice);
        }
    }
}
