package com.github.jmgilmour.ids;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IdsTest.Config.class)
public class IdsTest {

	@Configuration
	@EnableMongoRepositories(basePackageClasses = PersonRepository.class)
	public static class Config extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "test-ids";
		}

		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient();
		}
	}

	@Autowired
	private PersonRepository repository;

	@Test
	public void test() {
		Person[] people = {
				new Person("Alexander", "Ovechkin", 30),
				new Person("Nicklas", "Backstrom", 27),
				new Person("Braden", "Holtby", 26),
				new Person("John", "Carlson", 25)
		};

		for (Person person : people) {
			repository.save(person);
		}
	}
}