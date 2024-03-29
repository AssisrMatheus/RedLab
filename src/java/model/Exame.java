/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author adowt
 */
@Entity
@Table(catalog = "RedLab", schema = "")
@NamedQueries({
    @NamedQuery(name = "Exame.findAll", query = "SELECT e FROM Exame e"),
    @NamedQuery(name = "Exame.findById", query = "SELECT e FROM Exame e WHERE e.id = :id"),
    @NamedQuery(name = "Exame.findByCusto", query = "SELECT e FROM Exame e WHERE e.custo = :custo"),
    @NamedQuery(name = "Exame.findByDescricao", query = "SELECT e FROM Exame e WHERE e.descricao = :descricao"),
    @NamedQuery(name = "Exame.findByTempoJejum", query = "SELECT e FROM Exame e WHERE e.tempoJejum = :tempoJejum"),
    @NamedQuery(name = "Exame.findByDataEntrega", query = "SELECT e FROM Exame e WHERE e.dataEntrega = :dataEntrega"),
    @NamedQuery(name = "Exame.findByHoraEntrega", query = "SELECT e FROM Exame e WHERE e.horaEntrega = :horaEntrega"),
    @NamedQuery(name = "Exame.findByIsEntregue", query = "SELECT e FROM Exame e WHERE e.isEntregue = :isEntregue")})
public class Exame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private double custo;
    @Column(length = 255)
    private String descricao;
    @Column(length = 8)
    private String tempoJejum;
    @Column(length = 10)
    private String dataEntrega;
    @Column(length = 8)
    private String horaEntrega;
    private Boolean isEntregue;
    @JoinColumn(name = "loginPessoa", referencedColumnName = "login")
    @ManyToOne
    private Pessoa loginPessoa;

    public Exame() {
    }
    
    public Exame(HttpServletRequest request, Pessoa p) {
        this.populatePessoaUsingParameters(request, p);
    }
    
    private void populatePessoaUsingParameters(HttpServletRequest request, Pessoa p) {
        this.setDescricao((request.getParameter("descricao") == null) ? "" : request.getParameter("descricao"));
        this.setCusto((request.getParameter("custo")==null) ? 0 : Double.parseDouble(request.getParameter("custo")) );
        this.setIsEntregue((request.getParameter("entregue")==null) ? false : true);
        this.setTempoJejum((request.getParameter("jejum")==null) ? "": request.getParameter("jejum"));
        this.setDataEntrega((request.getParameter("dataEntrega")==null) ? "": request.getParameter("dataEntrega"));
        this.setHoraEntrega((request.getParameter("horaEntrega")==null) ? "": request.getParameter("horaEntrega"));
        
        this.setLoginPessoa(p);
    }

    public Exame(Integer id) {
        this.id = id;
    }

    public Exame(Integer id, double custo) {
        this.id = id;
        this.custo = custo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempoJejum() {
        return tempoJejum;
    }

    public void setTempoJejum(String tempoJejum) {
        this.tempoJejum = tempoJejum;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Boolean getIsEntregue() {
        return isEntregue;
    }

    public void setIsEntregue(Boolean isEntregue) {
        this.isEntregue = isEntregue;
    }

    public Pessoa getLoginPessoa() {
        return loginPessoa;
    }

    public void setLoginPessoa(Pessoa loginPessoa) {
        this.loginPessoa = loginPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exame)) {
            return false;
        }
        Exame other = (Exame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Exame[ id=" + id + " ]";
    }
    
}
