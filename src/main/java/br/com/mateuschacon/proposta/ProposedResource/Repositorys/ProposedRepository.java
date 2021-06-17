package br.com.mateuschacon.proposta.ProposedResource.Repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.AssociationStatusEnum;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.ProposalStatusEnum;
@Repository
public interface ProposedRepository extends CrudRepository<Proposed, String>{

    List<Proposed> 
        findByAssociationStatusEnumAndProposalStatusEnum(
            AssociationStatusEnum naoFeito,
            ProposalStatusEnum elegivel
        );
    
}
