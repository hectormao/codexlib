package com.codex.codexlib.ngc;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.codex.codexlib.bd.DocAcl;
import com.codex.codexlib.bd.DocArchivo;
import com.codex.codexlib.bd.DocArchivoVersion;
import com.codex.codexlib.bd.DocCampArch;
import com.codex.codexlib.bd.DocCampTipo;
import com.codex.codexlib.bd.DocCampo;
import com.codex.codexlib.bd.DocSerieDoc;
import com.codex.codexlib.bd.DocSerieSist;
import com.codex.codexlib.bd.DocSistArch;
import com.codex.codexlib.bd.DocTipoAlma;
import com.codex.codexlib.utl.Anexo;
import com.codex.codexplt.bd.PltUsuario;

public interface SistemaArchivoNgc {
	/**
	 * Obtiene los directorios hijos
	 * @param padre
	 * @param usuario 
	 * @return
	 */
	public List<DocSistArch> getHijos(DocSistArch padre, PltUsuario usuario, String buscar)  throws Exception;

	/**
	 * Obtiene los campos ya registrados en el sistema ordenados por su nombre
	 * @return
	 */
	public List<DocCampo> getCampos() throws Exception;

	/**
	 * obtiene los campos reslacionados a este tipo de archivo ordenados por su orden
	 * @param docTipoArchivo
	 * @return
	 * @throws Exception
	 */
	public List<DocCampTipo> getCamposTipo(DocSerieDoc docTipoArchivo) throws Exception;
	
	/**
	 * Registra en la base de datos un nuevo tipo de documento
	 * @param docTipoArchivo
	 * @param listaCrear
	 * @param listaActualizar
	 * @param listaEliminar
	 * @param listaCrearCampo
	 * @throws Exception
	 */
	public void registrarTipoDocumentos(DocSerieDoc docTipoArchivo, List<Object> listaCrear, List<Object> listaActualizar, List<Object> listaEliminar, List<DocCampo> listaCrearCampo, DocSerieSist serieSistema) throws Exception;
	
	/**
	 * Actualiza en la base de datos un tipo de documento
	 * @param docTipoArchivo
	 * @param listaCrear
	 * @param listaActualizar
	 * @param listaEliminar
	 * @param listaCrearCampo
	 * @throws Exception
	 */
	public void actualizarTipoDocumentos(DocSerieDoc docTipoArchivo, List<Object> listaCrear, List<Object> listaActualizar, List<Object> listaEliminar, List<DocCampo> listaCrearCampo) throws Exception;
	
	/**
	 * Obtiene los tipos de documentos registrados
	 * @return
	 */
	public List<DocSerieDoc> getTipos() throws Exception;
	
	/**
	 * Obtiene un archivo por su nombre en un directorio
	 * @param directorio
	 * @param nombreArchivo
	 * @return
	 */
	public DocArchivo getArchivo(DocSistArch directorio, String nombreArchivo) throws Exception;
	
	/**
	 * carga un archivo en la plataforma
	 * @param docArchivo
	 * @param version
	 * @param permisos 
	 * @param archivo
	 */
	public void cargarArchivo(DocArchivo docArchivo, DocArchivoVersion version, byte[] data, List<DocCampArch> listaMeta, List<DocAcl> permisos, List<Anexo> anexos) throws Exception;
	
	/**
	 * Anula o elimina un tipo de archivo del sistema
	 * @param docTipoArchivo
	 * @throws Exception
	 */
	public void anularTipoDocumentos(DocSerieDoc docTipoArchivo) throws Exception ;

	/**
	 * Crea un nuevo directorio
	 * @param directorio
	 * @param permisos
	 */
	public long registrarDirectorio(DocSistArch directorio,	List<DocAcl> permisos) throws Exception;
	
	
	
	/**
	 * Calcula el checksum de un archivo
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String calcularChecksum(byte[] data) throws Exception;
	
	/**
	 * Modifica los datos de un directorio
	 * @param directorio
	 * @param permisos
	 */
	public void modificarDirectorio(DocSistArch directorio, List<DocAcl> permisosNuevos, List<DocAcl> permisosEdicion, List<DocAcl> permisosEliminacion,List<DocAcl> permisosAnterior) throws Exception;
	
	/**
	 * Obtiene un directorio a partir de su id
	 * @param sistArchIdn
	 * @return
	 */
	public DocSistArch getDirectorio(long sistArchIdn)  throws Exception;

	/**
	 * Anula un directorio
	 * @param directorio
	 */
	public void anularDirectorio(DocSistArch directorio) throws Exception;

	/**
	 * Copiar un archivo a un destino
	 * @param archivo
	 * @param nuevoNombre
	 * @param destino
	 * @throws Exception
	 */
	public void copiarArchivo(DocArchivo archivo, String nuevoNombre, DocSistArch destino) throws Exception;

	
	/**
	 * Copiar un directorio a un destino
	 * @param directorio
	 * @param nuevoNombre
	 * @param destino
	 * @throws Exception
	 */
	public void copiarDirectorio(DocSistArch directorio, String nuevoNombre, DocSistArch destino)  throws Exception;
	
	/**
	 * Obtiene un directorio hijo que tenga ese nombre
	 * @param destino
	 * @param nombre
	 * @return
	 */
	public DocSistArch getHijo(DocSistArch destino, String nombre)  throws Exception ;
	
	
	/**
	 * Obtiene la ruta absoluta del archivo a partir del id de la version
	 * @param idVersion
	 * @return
	 */
	public Path getArchivo(Long idVersion)  throws IOException;
	
	/**
	 * Obtiene una serie Documental por su id
	 * @param idSerie
	 * @return
	 * @throws IOException
	 */
	public DocSerieDoc getSerieDoc(long idSerie) throws IOException;
	
	/**
	 * Obtiene las series hijas
	 * @param padre
	 * @param usuario 
	 * @return
	 */
	public List<DocSerieDoc> getHijosSerie(DocSerieDoc padre, PltUsuario usuario)  throws Exception;
	
	/**
	 * Obtiene las series asociadas a la entidad
	 * @param entidad
	 * @return
	 * @throws Exception
	 */
	public List<DocSerieDoc> getSeriesEntidad(DocSistArch entidad)  throws Exception;
	
	
	/**
	 * Obtiene los tipos de almacenamiento de una serie documental
	 * @param docSerieDoc
	 * @return
	 */
	public List<DocTipoAlma> getTiposAlmacenamientoSerie(DocSerieDoc docSerieDoc);
	
	
	/**
	 * Retorna las series documentales asociadas a un directorio
	 * @param dir
	 * @return
	 */
	public List<DocSerieDoc> getSeriesDirectorio(DocSistArch dir);
    /**
     * returna todas las entidades
     * @return
     */
	public List<DocSistArch> getEntidades();
	
	
	
	/**
	 * metodo para optener todas las series documentales para relacionar sistema de 
	 * archivos con series
	 * @return
	 */
	public List<DocSerieDoc> getSeriesDocumentales(DocSistArch padre);
	/**
	 * metodo que registra las relaciones entre series y sistemas de archivos
	 * @param docSerieSist
	 */
	public void registrarSeriesSistema(DocSerieSist docSerieSist);
	/**
	 * metodo para obtener los permisos y rol a ser editados
	 */
	public List<DocAcl> getObtenerPermisosRol(long id); 
	/**
	 * metodo para traer las series documentales del sistema de archivos
	 * @param sisArchi
	 * @return
	 */
	public List<DocSerieSist> getSeriesSisEditar(long sisArchi);
	/**
	 * limpiar series documentales
	 */
	public void ClearSeriesDocumentales(List<DocSerieSist> lista);
	
	
	public void registrarAcl(DocAcl acl) throws Exception;
	
	public void eliminarAcl(DocAcl acl) throws Exception;

	public List<DocAcl> getAccesoEntidadesUsuario(PltUsuario usu);
	
}
