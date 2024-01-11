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
	    
	  //lecture des produits 
	    public void lireProduits() 
	    {	if (produits.isEmpty()) 
	        {
	            System.out.println("Aucun produit n'est disponible");
	        } 
	        else {
	            System.out.println("Liste des produits:");
	            for (Produit produit : produits) 
	            {
	                System.out.println(produit);
	            }
	        }
	    }
	    
	  //mettre a jour les produits 
	    public void mettreAJourProduit(Produit produitMaj) 
	    { 
	    try{
	        if (!produitExisteParId(produitMaj.getId())) 
	        {
	        	throw new IllegalArgumentException("Erreur : Produit non trouvé avec l'ID " + produitMaj.getId());
	        }
	        if (produitMaj.getPrix() < 0 || produitMaj.getQuantite() < 0) 
	        {
	            System.out.println("Erreur : Le prix et la quantité des produits doivent être positifs.");
	            return;
	        }

	        for (int i = 0;i< produits.size();i++) 
	        {	Produit produit = produits.get(i);
	            if (produit.getId().equals(produitMaj.getId())) 
	            { 	            	
	                produits.set(i,produitMaj);
	                System.out.println("Produit mis à jour avec succès : " + produitMaj);
	                return;
	            }
	        }
	    }
	    catch (IllegalArgumentException e) 
	    {
	        System.out.println(e.getMessage());
	    }
	   }
	    
	    //supression dun produit
	    public void supprimerProduit(Long productId) {
	        try {
	            Produit produitASupp = lireProduit(productId);
	            if (produitASupp != null) 
	            {
	                produits.remove(produitASupp);
	                System.out.println("Produit supprimé avec succès !");
	            } 
	            else 
	            {
	                throw new Exception("Erreur lors de la suppression du produit");
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    
	    public Produit lireProduit(Long productId) {
	        for (Produit produit : produits) 
	        {
	            if (produit.getId().equals(productId)) 
	            {
	                return produit;
	            }
	        }
	        System.out.println("Erreur : Le produit avec l'ID " + productId + " n'existe pas.");
	        return null;
	    }
}
