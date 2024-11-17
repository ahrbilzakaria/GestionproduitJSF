package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import metier.ICategorieLocal;
import metier.IProduitLocal;
import metier.entities.Categorie;
import metier.entities.Produit;


@ManagedBean(name="produitBean")
@RequestScoped

public class ProduitBean {

    @EJB
    private IProduitLocal produitService;
    @EJB
    private ICategorieLocal categorieService;

    private Long code;
    private String designation;
    private double prix;
    private int quantite;
    private String categorie;
    private List<Produit> produits;


    @PostConstruct
    public void init() {
    	produits = produitService.listProduits();
    }

    public void addProduit() {
        Categorie cat = categorieService.getCategorie(categorie).get(0);
        Produit p = new Produit(designation, quantite, prix, cat);
        produitService.addProduit(p);
        produits = produitService.listProduits();
        code = null;
        designation = null;
        prix=0;
        quantite=0;
        
        
    }
    
    public List<Produit> getProduit() {
    	List<Produit> produit = produitService.getProduit(designation);
    	produits=produit;
    	return produit;
    }

    public void editProduit() {
        Categorie cat = categorieService.getCategorie(categorie).get(0);
        Produit p = new Produit(designation, quantite, prix, cat);
        p.setCode(code);
        produitService.editProduit(p);
        produits = produitService.listProduits();
        code = null;
        designation = null;
        prix=0;
        quantite=0;
    }

    public void deleteProduit(Long code) {
        produitService.deleteProduit(code);
        produits = produitService.listProduits();
    }

    public void addQuantity() {
        produitService.ajouterQte(code, quantite);
        produits = produitService.listProduits();
        code = null;
        designation = null;
        prix=0;
        quantite=0;
    }

    public void removeQuantity() {
        produitService.retirerQte(code, quantite);
        produits = produitService.listProduits();
        code = null;
        designation = null;
        prix=0;
        quantite=0;
    }

    public List<Produit> listProduits() {
       
        return produits;
    }

    
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
}
