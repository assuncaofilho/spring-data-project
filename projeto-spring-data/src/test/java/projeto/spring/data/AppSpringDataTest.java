package projeto.spring.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;

@RunWith(SpringJUnit4ClassRunner.class) // integrar o Spring ao JUnit
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired // injeção automática do SpringFramework;
	private InterfaceSpringDataUser InterfaceSpringDataUser;
	
	@Test
	public void testeInsert() {
		System.out.println("iniciou Spring com sucesso!");
	}

}
