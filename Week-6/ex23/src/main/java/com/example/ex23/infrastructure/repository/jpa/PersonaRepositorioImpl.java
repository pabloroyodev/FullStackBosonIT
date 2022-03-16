package com.example.ex23.infrastructure.repository.jpa;

import com.example.ex23.domain.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositorioImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Persona> filterPersonaByCriteria(HashMap<String, Object> conditions)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        String order = "";
        order = (String) conditions.get("order");

        Integer page = 0;
        page = (Integer) conditions.get("page");

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "user":
                    predicates.add(cb.like (root.get(field), "%"+(String)value+"%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "createdDate":
                    String dateCondition=(String) conditions.get("dateCondition");
                    switch (dateCondition)
                    {
                        case "greater":
                            predicates.add(cb.greaterThan(root.<LocalDate>get(field),(LocalDate)value));
                            break;
                        case "less":
                            predicates.add(cb.lessThan(root.<LocalDate>get(field),(LocalDate)value));
                            break;
                        case "equal":
                            predicates.add(cb.equal(root.<LocalDate>get(field),(LocalDate)value));
                            break;
                    }
                    break;
            }
        });
        if(order == null){
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        }else{
            if(order.equalsIgnoreCase("user") || order.equalsIgnoreCase("name")){
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(cb.asc(root.get(order)));
            }else{
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        if(page == null){
            page = 0;
        }else{
            page = page *10;
        }
        return entityManager.createQuery(query).setMaxResults(10).setFirstResult(page).getResultList();
    }
}
