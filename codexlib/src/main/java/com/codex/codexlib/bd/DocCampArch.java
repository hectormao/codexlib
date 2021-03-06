package com.codex.codexlib.bd;
// Generated 11/03/2017 08:16:33 AM by Hibernate Tools 3.5.0.Final

import java.util.Date;

import com.codex.codexplt.ant.Columna;
import com.codex.codexplt.ant.Tabla;

/**
 * DocCampArch generated by hbm2java
 */
@Tabla
public class DocCampArch implements java.io.Serializable {

	private long campArchIdn;
	private DocArchivoVersion docArchivoVersion;
	@Columna(orden=1)
	private DocCampo docCampo;
	
	@Columna(orden=2)
	private String campArchValor;
	
	
	private String audiUsuario;
	
	
	private Date audiFechModi;
	
	private boolean audiSiAnul;
	private String audiMotiAnul;
	private String audiChecksum;

	public DocCampArch() {
	}

	public DocCampArch(long campArchIdn, DocArchivoVersion docArchivoVersion, DocCampo docCampo, String campArchValor,
			String audiUsuario, Date audiFechModi, boolean audiSiAnul) {
		this.campArchIdn = campArchIdn;
		this.docArchivoVersion = docArchivoVersion;
		this.docCampo = docCampo;
		this.campArchValor = campArchValor;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
	}

	public DocCampArch(long campArchIdn, DocArchivoVersion docArchivoVersion, DocCampo docCampo, String campArchValor,
			String audiUsuario, Date audiFechModi, boolean audiSiAnul, String audiMotiAnul, String audiChecksum) {
		this.campArchIdn = campArchIdn;
		this.docArchivoVersion = docArchivoVersion;
		this.docCampo = docCampo;
		this.campArchValor = campArchValor;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
		this.audiMotiAnul = audiMotiAnul;
		this.audiChecksum = audiChecksum;
	}

	public long getCampArchIdn() {
		return this.campArchIdn;
	}

	public void setCampArchIdn(long campArchIdn) {
		this.campArchIdn = campArchIdn;
	}

	public DocArchivoVersion getDocArchivoVersion() {
		return this.docArchivoVersion;
	}

	public void setDocArchivoVersion(DocArchivoVersion docArchivoVersion) {
		this.docArchivoVersion = docArchivoVersion;
	}

	public DocCampo getDocCampo() {
		return this.docCampo;
	}

	public void setDocCampo(DocCampo docCampo) {
		this.docCampo = docCampo;
	}

	public String getCampArchValor() {
		return this.campArchValor;
	}

	public void setCampArchValor(String campArchValor) {
		this.campArchValor = campArchValor;
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

}
