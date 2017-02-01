package ressources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import outils.ListeUtilisateurDTO;
import outils.UtilisateurDTO;

@ManagedBean(name ="ihm")
@RequestScoped
public class Ihm {

	private List<UtilisateurDTO> listedto = new ArrayList <UtilisateurDTO>();

	public Ihm() {
		super();
	}

	public List<UtilisateurDTO> getListedto() {
		return listedto;
	}

	public void setListedto(List<UtilisateurDTO> listedto) {
		this.listedto = listedto;
	}
	
	@PostConstruct
	public void controler() {
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatauthentificateur:8080/listeur"));
		WebTarget ciblefinale = cible.path("listedto");
		ListeUtilisateurDTO dtoliste = ciblefinale.request(MediaType.APPLICATION_XML).get(ListeUtilisateurDTO.class);
		listedto = dtoliste.getListedto();
	}

}
