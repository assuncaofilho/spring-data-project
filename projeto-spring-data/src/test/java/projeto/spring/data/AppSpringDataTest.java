package projeto.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class) // integrar o Spring ao JUnit
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired // injeção automática do SpringFramework; /*A interface conhece a impl do CrudRepository */
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Test
	public void testeInsert() {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setNome("Ronildo Ihut");
		usuarioSpringData.setLogin("roni");
		usuarioSpringData.setSenha("3de1");
		usuarioSpringData.setEmail("roni@gmail.com");
		usuarioSpringData.setIdade(45);
		
		interfaceSpringDataUser.save(usuarioSpringData);

		
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		System.out.println("Usuário consultado: " + usuarioSpringData.get().getNome());
		
	}
	
	@Test
	public void consultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println("ID: " + usuarioSpringData.getId());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("E-mail: " + usuarioSpringData.getEmail());
			System.out.println("--------------------------------------");
		}
	}
	
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> user = interfaceSpringDataUser.findById(2L);
		
		UsuarioSpringData userToUpdate = user.get();
		
		userToUpdate.setSenha("134679785");
		
		interfaceSpringDataUser.save(userToUpdate);
		
		
	}
	
	@Test
	public void testeDelete() {
		
		/*Delete por consulta*/
		Optional<UsuarioSpringData> userOptional = interfaceSpringDataUser.findById(5L);
		
		interfaceSpringDataUser.delete(userOptional.get());
		
		/*Delete por ID*/
		interfaceSpringDataUser.deleteById(4L);
		
		
	}
	
	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> lista = interfaceSpringDataUser.buscaPorNomeParam("Roberto Carlos");
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println("ID: " + usuarioSpringData.getId());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("E-mail: " + usuarioSpringData.getEmail());
			System.out.println("--------------------------------------");
			
		}
		
	}
	

}
