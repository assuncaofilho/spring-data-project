package projeto.spring.data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import projeto.spring.data.model.UsuarioSpringData;

public class DataUserCustomImpl implements DataUserCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Object[]> dadosUser() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<UsuarioSpringData> root = criteriaQuery.from(UsuarioSpringData.class);
		
		criteriaQuery.multiselect(root.get("id"), root.get("nome"));
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Object[]> lista = typedQuery.getResultList();
		
		return lista;
	}

}
