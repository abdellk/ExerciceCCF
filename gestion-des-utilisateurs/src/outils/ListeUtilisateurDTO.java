package outils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListeUtilisateurDTO {

	private List<UtilisateurDTO> listedto = new ArrayList<>();

	public ListeUtilisateurDTO() {
		super();
	}

	public List<UtilisateurDTO> getListedto() {
		return listedto;
	}

	public void setListedto(List<UtilisateurDTO> listedto) {
		this.listedto = listedto;
	}

}
