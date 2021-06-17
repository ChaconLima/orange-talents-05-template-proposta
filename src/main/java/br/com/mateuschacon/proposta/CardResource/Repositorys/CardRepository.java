package br.com.mateuschacon.proposta.CardResource.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mateuschacon.proposta.CardResource.Models.Card;
@Repository
public interface CardRepository extends CrudRepository<Card,String>{
    
}
