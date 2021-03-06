package com.codex.codexlib.bd;
// Generated 11/03/2017 08:16:33 AM by Hibernate Tools 3.5.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codex.codexplt.ant.Columna;
import com.codex.codexplt.ant.Tabla;

/**
 * DocSerieDoc generated by hbm2java
 */
@Tabla(orderBy="serieDocCodigo asc, serieDocNombre asc")
public class DocSerieDoc implements java.io.Serializable {

	private long serieDocIdn;
	
	
	@Columna(orden=2)
	private String serieDocNombre;
	
	@Columna(orden=3)
	private String serieDocDescripcion;
	
	@Columna(orden=6)
	private String audiUsuario;
	
	@Columna(orden=7)
	private Date audiFechModi;
	
	private boolean audiSiAnul;
	private String audiMotiAnul;
	private String audiChecksum;
	private DocSerieDoc serieDocPadre;
	
	@Columna(orden=4)
	private String serieDocTipo;
	
	@Columna(orden=5)
	private String serieDocSoporte;
	
	@Columna(orden=1)
	private String serieDocCodigo;
	
	private Set<DocCampTipo> docCampTipos = new HashSet<DocCampTipo>(0);
	private Set<DocArchivo> docArchivos = new HashSet<DocArchivo>(0);
	private Set<DocTipoAlma> docTipoAlmas = new HashSet<DocTipoAlma>(0);

	public DocSerieDoc() {
	}

	public DocSerieDoc(long serieDocIdn, String serieDocNombre, String audiUsuario, Date audiFechModi,
			boolean audiSiAnul) {
		this.serieDocIdn = serieDocIdn;
		this.serieDocNombre = serieDocNombre;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
	}

	public DocSerieDoc(long serieDocIdn, String serieDocNombre, String serieDocDescripcion,
			String audiUsuario, Date audiFechModi, boolean audiSiAnul, String audiMotiAnul, String audiChecksum,
			DocSerieDoc serieDocPadre, String serieDocTipo, Set<DocCampTipo> docCampTipos, Set<DocArchivo> docArchivos,
			Set<DocTipoAlma> docTipoAlmas) {
		this.serieDocIdn = serieDocIdn;
		
		this.serieDocNombre = serieDocNombre;
		this.serieDocDescripcion = serieDocDescripcion;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
		this.audiMotiAnul = audiMotiAnul;
		this.audiChecksum = audiChecksum;
		this.serieDocPadre = serieDocPadre;
		this.serieDocTipo = serieDocTipo;
		this.docCampTipos = docCampTipos;
		this.docArchivos = docArchivos;
		this.docTipoAlmas = docTipoAlmas;
	}

	public long getSerieDocIdn() {
		return this.serieDocIdn;
	}

	public void setSerieDocIdn(long serieDocIdn) {
		this.serieDocIdn = serieDocIdn;
	}

	
	public String getSerieDocNombre() {
		return this.serieDocNombre;
	}

	public void setSerieDocNombre(String serieDocNombre) {
		this.serieDocNombre = serieDocNombre;
	}

	public String getSerieDocDescripcion() {
		return this.serieDocDescripcion;
	}

	public void setSerieDocDescripcion(String serieDocDescripcion) {
		this.serieDocDescripcion = serieDocDescripcion;
	}

	public String getAudiUsuario() {
		return this.audiUsuario;
	}

	public void setAudiUsuario(String audiUsuario) {
		this.audiUsuario = audiUsuario;
	}

	public Date getAudiFechModi() {
		return this.audiFechModi;
	}

	public void setAudiFechModi(Date audiFechModi) {
		this.audiFechModi = audiFechModi;
	}

	public boolean isAudiSiAnul() {
		return this.audiSiAnul;
	}

	public void setAudiSiAnul(boolean audiSiAnul) {
		this.audiSiAnul = audiSiAnul;
	}

	public String getAudiMotiAnul() {
		return this.audiMotiAnul;
	}

	public void setAudiMotiAnul(String audiMotiAnul) {
		this.audiMotiAnul = audiMotiAnul;
	}

	public String getAudiChecksum() {
		return this.audiChecksum;
	}

	public void setAudiChecksum(String audiChecksum) {
		this.audiChecksum = audiChecksum;
	}

	public DocSerieDoc getSerieDocPadre() {
		return this.serieDocPadre;
	}

	public void setSerieDocPadre(DocSerieDoc serieDocPadre) {
		this.serieDocPadre = serieDocPadre;
	}

	public String getSerieDocTipo() {
		return this.serieDocTipo;
	}

	public void setSerieDocTipo(String serieDocTipo) {
		this.serieDocTipo = serieDocTipo;
	}

	public Set<DocCampTipo> getDocCampTipos() {
		return this.docCampTipos;
	}

	public void setDocCampTipos(Set<DocCampTipo> docCampTipos) {
		this.docCampTipos = docCampTipos;
	}

	public Set<DocArchivo> getDocArchivos() {
		return this.docArchivos;
	}

	public void setDocArchivos(Set<DocArchivo> docArchivos) {
		this.docArchivos = docArchivos;
	}

	public Set<DocTipoAlma> getDocTipoAlmas() {
		return this.docTipoAlmas;
	}

	public void setDocTipoAlmas(Set<DocTipoAlma> docTipoAlmas) {
		this.docTipoAlmas = docTipoAlmas;
	}

	public String getSerieDocSoporte() {
		return serieDocSoporte;
	}

	public void setSerieDocSoporte(String serieDocSoporte) {
		this.serieDocSoporte = serieDocSoporte;
	}

	public String getSerieDocCodigo() {
		return serieDocCodigo;
	}

	public void setSerieDocCodigo(String serieDocCodigo) {
		this.serieDocCodigo = serieDocCodigo;
	}
	
	@Override
	public String toString(){
		return getSerieDocCodigo() + " - " + getSerieDocNombre();
	}
	

}
