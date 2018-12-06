package isep.web.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import isep.web.sakila.dao.business.IBusiness;
import isep.web.sakila.jpa.config.DomainAndPersistenceConfig;
import isep.web.sakila.jpa.entities.Actor;

@SpringBootApplication(scanBasePackages={
"isep.web.sakila.dao.repositories", "isep.web.sakila.dao.business","isep.web.sakila.dao.config","isep.web.sakila.dao.entities"})
public class SakilaBusinessDaoApplication
{

	public static void main(String[] args)
	{
		// We prepare the Spring Configuration
		SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
		app.setLogStartupInfo(false);

		// We launch the Application Context
		ConfigurableApplicationContext context = app.run(args);
		// Business Layer
		IBusiness business = context.getBean(IBusiness.class);

		try
		{
			for (Actor actor : business.getAllActors())
			{
				System.out.println(actor);
			}
			Actor guiness = business.getByID(1);
			System.out.printf("Who is ID 1? %s %s %n", guiness.getLastName(), guiness.getFirstName());

		} catch (Exception ex)
		{
			System.out.println("Exception : " + ex.getCause());
		}
		// Closing Spring Context
		context.close();

	}
}
