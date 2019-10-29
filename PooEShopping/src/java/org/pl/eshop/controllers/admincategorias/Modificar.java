package org.pl.eshop.controllers.admincategorias;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.pl.eshop.dto.Categoria;
import org.pl.eshop.dto.CategoriaDAO;
import org.pl.eshop.dto.CategoriaDAOMySQL;

@Named(value = "modificar")
@ViewScoped
public class Modificar implements Serializable {
    
    private Categoria categoriaModificar;
    private Integer idCategoria;

    public Modificar() {
    }
    
    public void initCategoria () {
        try {
            CategoriaDAO catDao = new CategoriaDAOMySQL();
            this.categoriaModificar = catDao.obtenerPorId(idCategoria);
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage("Hay problemas para acceder a la base de datos: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Hubo un error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
     }

    public String guardarCategoria() {
        try {
            CategoriaDAO catDao = new CategoriaDAOMySQL();
            catDao.modificar(categoriaModificar);
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage("Hay problemas para acceder a la base de datos: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Hubo un error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return "index?faces-redirect=true";
    }
    public Categoria getCategoriaModificar() {
        return categoriaModificar;
    }
    public void setCategoriaModificar(Categoria categoriaModificar) {
        this.categoriaModificar = categoriaModificar;
    }
    public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
