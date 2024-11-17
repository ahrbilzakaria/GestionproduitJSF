package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import metier.ICategorieLocal;	
import metier.entities.Categorie;

@ManagedBean(name="categorieBean")
@RequestScoped
public class CategorieBean {

    @EJB
    private ICategorieLocal categorieService;

    private Long code;
    private String libelle;
    private String description;
    private List<Categorie> categories;
    
    
    @PostConstruct
    public void init() {
    	categories = categorieService.listCategorie();
    }

    public void addCategorie() {
        Categorie c = new Categorie(libelle, description);
        categorieService.addCategorie(c);
        categories = categorieService.listCategorie();
    }

    public void editCategorie() {
        Categorie c = new Categorie(libelle, description);
        c.setCode(code);
        categorieService.editCategorie(c);
        categories = categorieService.listCategorie();
    }

    public void deleteCategorie(Long code) {
        categorieService.deleteCategorie(code);
        categories = categorieService.listCategorie();
    }

    public List<Categorie> listCategories() {
        return categories;
    }
    
    public void getCategorie() {
    	categories = categorieService.getCategorie(libelle);
    	
    }

  
    
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
