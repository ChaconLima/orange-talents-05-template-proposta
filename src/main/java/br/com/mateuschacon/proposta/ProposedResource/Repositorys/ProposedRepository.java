package br.com.mateuschacon.proposta.ProposedResource.Repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;

public interface ProposedRepository extends CrudRepository<Proposed, String>{
    
}
