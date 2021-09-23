package projeto.spring.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.spring.data.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>{
	
	/*Contém o valor porém no-sensitive*/
	@Query(value = "select u from UsuarioSpringData u where lower(u.nome) like lower(concat('%',?1,'%'))") //jpql
	public List<UsuarioSpringData> buscaNomeNoSensitive (String nome);
	
	/*Contém o valor porém sensitive*/
	@Query(value = "select u from UsuarioSpringData u where u.nome like concat('%', :nameToFind,'%')")
	public List<UsuarioSpringData> buscaNomeSensitive(@Param("nameToFind") String nome);
	
	/*Valor exato - nome completo*/
	@Query(value = "select u from UsuarioSpringData u where u.nome = :paramnome")
	public List<UsuarioSpringData> buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	
	default <S extends UsuarioSpringData> S savePersonalOne(S entity) {
		// processamento individualizado;
		return save(entity);
	}
	

}
