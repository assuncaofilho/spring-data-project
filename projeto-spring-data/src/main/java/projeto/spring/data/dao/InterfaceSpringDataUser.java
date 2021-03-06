package projeto.spring.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>, DataUserCustom{
	
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
	
	/*Deleta o usuário caso encontre o nome exato(Completo) no BD*/
	@Modifying // pois gerará mudança no BD
	@Transactional // pois necessecita de uma transação
	@Query(value = "delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNomeExato(String nome);
	
	/*Deleta o usuário caso encontre o parâmetro passado de forma exata.*/
	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioSpringData u where u.nome like concat('%', :nameToDelete, '%')")
	public void deletePorNomeContemSensitive(@Param("nameToDelete") String nome);
	
	/*Deleta o usuário caso encontre o parâmetro passado de forma exata.*/
	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioSpringData u where u.nome like concat(:nameToDelete, '%')")
	public void deletePorPrimeiroNome(@Param("nameToDelete") String nome);
	
	
	

}
