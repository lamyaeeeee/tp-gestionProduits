package gestionProduit;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
	 private List<Produit> produits;

	    public ProduitService() 
	    {
	        this.produits = new ArrayList<>();
	    }
	    
	 //creation de produit 
	    public void creerProduit(Produit newProduit) 
	    {
	        if (produitExisteParId(newProduit.getId()) || produitExisteParNom(newProduit.getNom())) 
	        {
	            System.out.println("Erreur:Un produit avec le même ID ou nom existe déja");
	            return;
	        }
	        if (newProduit.getPrix() < 0 || newProduit.getQuantite() < 0) 
	        {
	            System.out.println("Erreur:Le prix et la quantité doivent être positifs");
	            return;
	        }
	        produits.add(newProduit);
	        System.out.println("Produit ajouté avec succès:" + newProduit);
	    }
	    
	    private boolean produitExisteParId(Long id) {
	        return produits.stream().anyMatch(produit -> produit.getId().equals(id));
	    }

	    private boolean produitExisteParNom(String nom) {
	        return produits.stream().anyMatch(produit -> produit.getNom().equalsIgnoreCase(nom));
	    }
}
