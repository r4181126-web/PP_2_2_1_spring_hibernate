package hiber;

import hiber.config.AppConfig;
import hiber.dao.CarService;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      List<User> users = Arrays.asList(
              new User("User1", "Lastname1", "user1@mail.ru"),
              new User("User2", "Lastname2", "user2@mail.ru"),
              new User("User3", "Lastname3", "user3@mail.ru"),
              new User("User4", "Lastname4", "user4@mail.ru")
              );
      for (User user : users) {
          userService.add(user);
      }
      List<Car> cars = Arrays.asList(
              new Car("Tesla", 10),
              new Car("Lada",100),
              new Car("Mazda",1000),
              new Car("BMW",10_000)
      );
      for (Car car : cars) {
          carService.add(car);
      }

      List<User> savedUsers = userService.listUsers();
      List<Car> savedCars = carService.listCars();

      for (int i = 0; i < savedUsers.size(); i++) {
          User user = savedUsers.get(i);
          Car car = savedCars.get(i);
          user.setCar(car);
          car.setUser(user);

          userService.update(user);
      }

      List<User> result = userService.listUsers();
      for (User user : result) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
             System.out.println("model = " + user.getCar().getModel());
             System.out.println("series = " + user.getCar().getSeries());
         }
          System.out.println();
      }
      context.close();
   }
}
