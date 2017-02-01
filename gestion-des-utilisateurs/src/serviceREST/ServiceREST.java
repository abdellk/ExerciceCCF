package serviceREST;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modele.Utilisateur;
import outils.ListeUtilisateurDTO;
import outils.UtilisateurDTO;
import ressources.FournisseurDePersistance;

@Path("listedto")
public class ServiceREST {
	
	ListeUtilisateurDTO listeDTO; 
		
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListeUtilisateurDTO getDTO() {
		listeDTO = new ListeUtilisateurDTO();
		peuplementListe();
		return listeDTO;		
	}
	
	private void peuplementListe() {
	
		EntityManager em = null;
	
		try {
				em = FournisseurDePersistance.getInstance().fournir();
				em.getTransaction().begin();
				Query requete = em.createNativeQuery("SELECT * FROM UTILISATEUR", Utilisateur.class);
				List<Utilisateur> resultat = requete.getResultList();
				
				for (Utilisateur util : resultat ) {
					
					listeDTO.getListedto().add(new UtilisateurDTO(util.getNom(), util.getPrenom(), util.getEmail(), util.getPassword(), util.getRole().getRole()));
					
					
				}
				
				// implémenter la requête native JPA avec le code SQL approprié afin de restaurer tous les utilisateurs dans une liste
				// todo
				// implémenter l'itération qui permet à partir des objets Utilisateur restaurés ci-dessus de remplir la liste des objets UtilisateurDTO de la classe ListeUtilisateurDTO
				// todo
				em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		finally {
			try {
					em.close();
				} catch (Exception e) {e.printStackTrace();
			}			
		}
	}
}
