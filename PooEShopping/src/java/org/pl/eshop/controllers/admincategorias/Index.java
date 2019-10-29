package org.pl.eshop.controllers.admincategorias;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.pl.eshop.dto.Categoria;
import org.pl.eshop.dto.CategoriaDAO;
import org.pl.eshop.dto.CategoriaDAOMySQL;

@Named(value = "index")
@ViewScoped
public class Index implements Serializable {
    
    private List<Categoria> categorias;
    private Categoria categoriaEliminar;
    
    public Index() {
        try {
            CategoriaDAO catDao = new CategoriaDAOMySQL();
            this.categorias = catDao.obtenerTodas();
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage("Hay problemas para acceder a la base de datos: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Hubo un error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String eliminarCategoria() {
        try {
            CategoriaDAO catDao = new CategoriaDAOMySQL();
            catDao.eliminar(categoriaEliminar);
        
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage("Hay problemas para acceder a la base de datos: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Hubo un error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return "index?faces-redirect=true";
    }
    public List<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    public Categoria getCategoriaEliminar() {
        return categoriaEliminar;
    }
    public void setCategoriaEliminar(Categoria categoriaEliminar) {
        this.categoriaEliminar = categoriaEliminar;
    }
}
