package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Cliente;
import persistence.ClienteDao;

@RequestScoped
@ManagedBean(name = "mb")
public class ManagerBean {
	
	private Cliente cliente;
	private List<Cliente> clientes = new ArrayList<>();

	public ManagerBean() {
		this.cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		try {
			this.clientes = new ClienteDao().findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public void cadastrar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ClienteDao().create(this.cliente);
			fc.addMessage(null, new FacesMessage("Dados Gravados"));
		}catch(Exception e) {
			fc.addMessage(null, new FacesMessage("Error: " + e.getMessage()));
		}
	}
	
	public void alterar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ClienteDao().update(this.cliente);
			fc.addMessage(null, new FacesMessage("Dados Alterados"));
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error: " + e.getMessage()));
		}
	}
	
	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ClienteDao().delete(this.cliente);
			fc.addMessage(null, new FacesMessage("Cliente excluído"));
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage("Error: " + e.getMessage()));
		}
	}
}
