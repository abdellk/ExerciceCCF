package client;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import outils.ListeUtilisateurDTO;
import outils.UtilisateurDTO;

public class ClientREST {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://172.17.0.5:8080/listeur"));
		WebTarget ciblefinale = cible.path("listedto");
		ListeUtilisateurDTO dtoliste = ciblefinale.request(MediaType.APPLICATION_XML).get(ListeUtilisateurDTO.class);
		for(UtilisateurDTO iter: dtoliste.getListedto())
			System.out.println(iter.getNom()+iter.getPrenom()+iter.getEmail()+iter.getPassword()+iter.getRole());
	}
}
