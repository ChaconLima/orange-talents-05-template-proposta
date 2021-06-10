package br.com.mateuschacon.proposta.CardResource.Repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.mateuschacon.proposta.CardResource.Models.Card;

public interface CardRepository extends CrudRepository<Card,String>{
    
}
