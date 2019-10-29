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

@Named(value = "agregar")
@ViewScoped
public class Agregar implements Serializable {
    private Categoria nuevaCategoria;

    public Agregar() {
    nuevaCategoria = new Categoria();
    }

    public String agregarCategoria() {
        try {
            CategoriaDAO catDao = new CategoriaDAOMySQL();
            catDao.agregar(nuevaCategoria);
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage("Hay problemas para acceder a la base de datos: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Hubo un error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return "index?faces-redirect=true";
    }
    public Categoria getNuevaCategoria() {
        return nuevaCategoria;
    }
    public void setNuevaCategoria(Categoria nuevaCategoria) {
        this.nuevaCategoria = nuevaCategoria;
    }
}
