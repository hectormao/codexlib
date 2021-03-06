package com.codex.codexlib.cnt;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.codex.codexlib.bd.DocSerieDoc;
import com.codex.codexlib.bd.DocSerieSist;
import com.codex.codexlib.bd.DocSistArch;
import com.codex.codexlib.ngc.SistemaArchivoNgc;
import com.codex.codexlib.utl.ConstantesData3000;
import com.codex.codexplt.bd.PltUsuario;
import com.codex.codexplt.ngc.PlataformaNgc;
import com.codex.codexplt.utl.ConstantesAdmin;
import com.codex.codexplt.utl.WindowComposer;
import com.codex.codexplt.vo.Formulario;
import com.codex.codexplt.vo.FormularioHijo;

public class SerieDocumentalCnt extends WindowComposer {
	
	private Tree trSerie;
	
	private Logger logger;
	
	
	private SistemaArchivoNgc sistemaArchivoNgc;
	private PlataformaNgc plataformaNgc;
	
	private Div divTrabajo;
	
	private Menupopup menuSerie;
	private Toolbarbutton btnNuevo;
	private Toolbarbutton btnEditar;
	private Toolbarbutton btnEliminar;
	
	
	private DocSerieDoc seleccion = null;
	
	
	
	private Window winTablaDatos;
	
	private class ItemSerie {
		public DocSerieDoc serie;
		public Treeitem item;
		public Treecell celda;
	}
	
	@Override
	public void doAfterCompose(Window winSerieDocumental) throws Exception{
		
		super.doAfterCompose(winSerieDocumental);
		
		logger = Logger.getLogger(this.getClass());
		
		
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/codex/codexplt/zul/tablaDatos.zul") ;
		java.io.Reader zulReader = new java.io.InputStreamReader(zulInput) ;
		
		Map<String,Object> parametros = new HashMap<String, Object>();
		parametros.put(ConstantesAdmin.ARG_USUARIO,usuario);
		parametros.put(ConstantesAdmin.ARG_FORMULARIO,formulario);
		parametros.put(ConstantesAdmin.ARG_CLASE,DocSerieDoc.class);
		
		
		winTablaDatos = (Window) Executions.createComponentsDirectly(zulReader,"zul",divTrabajo,parametros) ;
		winTablaDatos.setTitle(null);
		winTablaDatos.setBorder("none");
		winTablaDatos.doEmbedded();
		
		
		List<FormularioHijo> hijos = formulario.getHijos();
		if(hijos != null && ! hijos.isEmpty()){
			for(FormularioHijo hijo : hijos){
				if(hijo.getTipo().equals(ConstantesAdmin.HIJO_BOTON)){
					
					crearMenuItem(hijo.getHijo());
					
				}
			}
		}
		
		String nombreFormulario = (String) btnNuevo.getAttribute(ConstantesAdmin.ATRIBUTO_FORMULARIO);
		final Formulario frmNuevoDirectorio = getFormulario(nombreFormulario);
		if(frmNuevoDirectorio == null){
			btnNuevo.setDisabled(true);
			btnNuevo.setVisible(false);
		} else {
			
			btnNuevo.setImage(frmNuevoDirectorio.getUrlIcono());
			btnNuevo.setTooltip(frmNuevoDirectorio.getTooltip());
			
			btnNuevo.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					
					 EventListener<Event> eventoCerrar = new EventListener<Event>() {

							@Override
							public void onEvent(Event arg0) throws Exception {
								DocSerieDoc res = (DocSerieDoc) arg0.getData();
								if(res != null ){
									/*if(res.getSerieDocPadre() == null){
										trSerie.setSelectedItem(null);
									}*/
									onSelect$trSerie(arg0);
								}
								
							}
						};
					
					
					Treeitem itemSeleccionado = trSerie.getSelectedItem();
					
					Object seleccion =  (itemSeleccionado != null ? itemSeleccionado.getValue() : null);
					
					
					abrirFormulario(frmNuevoDirectorio, seleccion, eventoCerrar);
					
					
				}
				
			});
			
		}
		
		
		String nombreFormularioEditar = (String) btnEditar.getAttribute(ConstantesAdmin.ATRIBUTO_FORMULARIO);
		final Formulario frmEditarDirectorio = getFormulario(nombreFormularioEditar);
		if(frmEditarDirectorio == null){
			btnEditar.setDisabled(true);
			btnEditar.setVisible(false);
		} else {
			
			btnEditar.setImage(frmEditarDirectorio.getUrlIcono());
			btnEditar.setTooltip(frmEditarDirectorio.getTooltip());
			btnEditar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					
					 EventListener<Event> eventoCerrar = new EventListener<Event>() {

							@Override
							public void onEvent(Event arg0) throws Exception {
								String res = (String) arg0.getData();
								if(res != null && res.equals(ConstantesAdmin.EXITO)){
									
									
									
									Treeitem li = trSerie.getSelectedItem();
									if(li != null){
										
										DocSerieDoc serieTmp = li.getValue();
										
										DocSerieDoc serie = sistemaArchivoNgc.getSerieDoc(serieTmp.getSerieDocIdn());
										
										Treerow fila = (Treerow) li.getFirstChild();
										Treecell celda = (Treecell) fila.getFirstChild();
										li.setValue(serie);
										celda.setLabel(serie.getSerieDocNombre());
										celda.setTooltiptext(serie.getSerieDocDescripcion());
									}
									
									
									//onSelect$trFileSystem(arg0);
								}
								
							}
						};
					
					
					Treeitem itemSeleccionado = trSerie.getSelectedItem();
					
					DocSerieDoc seriePadre = (DocSerieDoc) (itemSeleccionado != null ? itemSeleccionado.getValue() : null);
					
					
					abrirFormulario(frmEditarDirectorio, seriePadre, eventoCerrar);
					
					
				}
				
			});
			
		}
		
		String nombreFormularioEliminar = (String) btnEliminar.getAttribute(ConstantesAdmin.ATRIBUTO_FORMULARIO);
		final Formulario frmEliminarDirectorio = getFormulario(nombreFormularioEliminar);
		if(frmEliminarDirectorio == null){
			btnEliminar.setDisabled(true);
			btnEliminar.setVisible(false);
		} else {
			
			btnEliminar.setImage(frmEliminarDirectorio.getUrlIcono());
			btnEliminar.setTooltip(frmEliminarDirectorio.getTooltip());
			btnEliminar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					
					 EventListener<Event> eventoCerrar = new EventListener<Event>() {

							@Override
							public void onEvent(Event arg0) throws Exception {
								String res = (String) arg0.getData();
								if(res != null && res.equals(ConstantesAdmin.EXITO)){
									Treeitem li = trSerie.getSelectedItem();
									if(li != null){										
										Component padre = li.getParent();										
										padre.removeChild(li);
									}
								}
								
							}
						};
					
					
					Treeitem itemSeleccionado = trSerie.getSelectedItem();
					
					DocSerieDoc seriePadre = (DocSerieDoc) (itemSeleccionado != null ? itemSeleccionado.getValue() : null);
					
					
					abrirFormulario(frmEliminarDirectorio, seriePadre, eventoCerrar);
					
					
				}
				
			});
			
		}
		
		btnNuevo.setDisabled(true);
		btnEditar.setDisabled(true);
		btnEliminar.setDisabled(true);
		
		cargarArbol();
		//actualizarTablaDatos((DocSerieDoc) null);
	}
	
	
	
	private void crearMenuItem(final Formulario formularioHijo){		
		Menuitem item = new Menuitem();					
		
		String nombre = formularioHijo.getNombre();
		String leyenda = Labels.getLabel(nombre);
		if(leyenda == null){
			leyenda = nombre;
		}
		
		item.setLabel(leyenda);
		item.setImage(formularioHijo.getUrlIcono());
		item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				
				Map<String,Object> parametros = new HashMap<String, Object>();
				
				parametros.putAll(argumentos);
				parametros.put(ConstantesAdmin.ARG_FORMULARIO,formularioHijo);
				parametros.put(ConstantesAdmin.OBJETO_PADRE,seleccion);							
				
				java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream(formularioHijo.getUrl()) ;
				java.io.Reader zulReader = new java.io.InputStreamReader(zulInput) ;
				
				
				Window winCargar = (Window) Executions.createComponentsDirectly(zulReader,"zul",divTrabajo,parametros) ;
				
				String nombre = formularioHijo.getNombre();
				
				String titulo = Labels.getLabel(nombre);
				if(titulo == null){
					titulo = formularioHijo.getNombre();
				}
				
				winCargar.setTitle(titulo);
				
				winCargar.addEventListener(Events.ON_CLOSE, new EventListener<Event>() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						String res = (String) arg0.getData();
						if(res != null && res.equals(ConstantesAdmin.EXITO)){
							
							Treeitem tiSeleccion = trSerie.getSelectedItem();							
							DocSerieDoc serie = tiSeleccion != null ? (DocSerieDoc) tiSeleccion.getValue() : null;
							if(serie != null){							
								actualizarTablaDatos(serie);
							}
						}
						
					}
				});
				
				winCargar.doModal();							
			}
		});
		
		menuSerie.appendChild(item);
		
		
		
	}
	
	private void cargarArbol() throws Exception {
		if(logger.isDebugEnabled()) logger.debug("Consultando arbol ...");
		
		
		/*String leyenda = Labels.getLabel("data3000.seriesDoc");
		if(leyenda == null || (leyenda != null && leyenda.trim().length() <= 0)){
			leyenda = "Series";
		}
		
		
		Treechildren raizSerie = new Treechildren();
		trSerie.appendChild(raizSerie);
		Treeitem itemSerie = new Treeitem(leyenda);
		itemSerie.getTreerow().setClass("submenu");
		raizSerie.appendChild(itemSerie);
		
		Treechildren raiz = new Treechildren();		
		itemSerie.appendChild(raiz);*/
		
		Treechildren raizSerie = new Treechildren();
		trSerie.appendChild(raizSerie);
		
		List<DocSistArch> entidades = sistemaArchivoNgc.getEntidades();
		for(DocSistArch entidad : entidades){
			
			Treeitem itemEntidad = new Treeitem(entidad.getSistArchNombre());
			itemEntidad.setValue(entidad);
			itemEntidad.getTreerow().setClass("submenu");
			raizSerie.appendChild(itemEntidad);
			
			Treechildren raizEntidad = new Treechildren();		
			itemEntidad.appendChild(raizEntidad);
			
			cargarArbol(null, raizEntidad, entidad);
			
			
		}
		
		
		
		
	}
	
	private void cargarArbol(DocSerieDoc padre, Treechildren arbolPadre, DocSistArch entidad) throws Exception{
		if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Cargando hijos para ...").append(padre != null ? padre.getSerieDocNombre() : "Raiz").toString());
		List<DocSerieDoc> listaHijos = null;
		if(entidad != null){
			listaHijos = sistemaArchivoNgc.getSeriesEntidad(entidad);
		} else {
			listaHijos = sistemaArchivoNgc.getHijosSerie(padre, (PltUsuario) usuario);
		}
		
		
		Map<Long,ItemSerie> mapaSerie = (Map<Long, ItemSerie>) arbolPadre.getAttribute(ConstantesData3000.ATRIBUTO_MAPA_DIR);
		List<ItemSerie> listaSerie = (List<ItemSerie>) arbolPadre.getAttribute(ConstantesData3000.ATRIBUTO_LISTA_DIR);
		
		
		if(mapaSerie == null){
			mapaSerie = new HashMap<Long, ItemSerie>();
			arbolPadre.setAttribute(ConstantesData3000.ATRIBUTO_MAPA_DIR,mapaSerie);
		}
		
		Map<Long,ItemSerie> mapaSerAux = new HashMap<Long, ItemSerie>();
		mapaSerAux.putAll(mapaSerie);
		
		//int idx = 0;
		
		for(final DocSerieDoc hijo : listaHijos){
			
			
			ItemSerie serAux = mapaSerAux.remove(hijo.getSerieDocIdn());
			
			if(serAux == null){	
				Treeitem item = new Treeitem();
				item.setValue(hijo);
				Treerow fila = new Treerow();
				Treecell celda = new Treecell(hijo.getSerieDocCodigo() + " - " + hijo.getSerieDocNombre());
				celda.setTooltiptext(hijo.getSerieDocDescripcion());
				fila.appendChild(celda);
				item.appendChild(fila);
				fila.setContext(menuSerie);
				fila.addEventListener(Events.ON_RIGHT_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						seleccion = hijo;
						
					}
				});
				
				
				serAux = new ItemSerie();
				serAux.serie = hijo;
				serAux.celda = celda;
				serAux.item = item;
				
				mapaSerie.put(hijo.getSerieDocIdn(), serAux);
				
				//arbolPadre.appendChild(item);
				//buscando insertar en arbol de forma ordenada por el nombre de la serie
				boolean inserto = false;
				for(Treeitem itemHijo : arbolPadre.getItems()){
					DocSerieDoc hijoComparar = itemHijo.getValue();
										
					if(hijoComparar != null && hijoComparar.toString().compareTo(hijo.toString()) > 0){
						if(logger.isDebugEnabled()) logger.debug(new StringBuilder("hijoComparar: ").append(hijoComparar.getSerieDocNombre()).append(" vs. hijo: ").append(hijo.getSerieDocNombre()).append(" resultado: ").append(hijoComparar.getSerieDocNombre().compareTo(hijo.getSerieDocNombre())).toString());
						if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Insertando antes de: ").append(hijoComparar.getSerieDocNombre()).toString());
						arbolPadre.insertBefore(item, itemHijo);
						inserto = true;
						break;
					} 
				}
				
				if(! inserto){
					arbolPadre.appendChild(item);
				}
				
			} else {
				if(! serAux.serie.getSerieDocNombre().equals(hijo.getSerieDocNombre())){
					//cambiar nombre
					serAux.celda.setLabel(hijo.getSerieDocNombre());
					serAux.item.setValue(hijo);
					serAux.serie = hijo;				
				}
				
				if(! serAux.serie.getSerieDocDescripcion().equals(hijo.getSerieDocDescripcion())){
					//cambiar tooltip
					serAux.celda.setTooltiptext(hijo.getSerieDocDescripcion());
					serAux.item.setValue(hijo);
					serAux.serie = hijo;
				}
			}
		}
		
		//Eliminando hijos que han sido borrados de la BD
		for(ItemSerie serie : mapaSerAux.values()){
			Component hijo = serie.item.getParent();
			hijo.removeChild(serie.item);
			mapaSerie.remove(serie.serie.getSerieDocIdn());
		}
		
	}
	
	
	
	public void onSelect$trSerie(Event event) throws Exception {
		
		
		
		Treeitem tiSeleccion = trSerie.getSelectedItem();
		
		Object valorSeleccion = tiSeleccion != null ? tiSeleccion.getValue() : null; 
		
		if(valorSeleccion instanceof DocSerieDoc){
			DocSerieDoc serie = (DocSerieDoc) valorSeleccion;
			actualizarTablaDatos(serie);
			if(tiSeleccion != null){
				
				Treechildren hijos = tiSeleccion.getTreechildren();
				if(hijos == null){
					hijos = new Treechildren();
					tiSeleccion.appendChild(hijos);
				}		
				cargarArbol(serie, hijos,null);
							
				
			}
			if(serie.getSerieDocTipo().equals(ConstantesData3000.TIPO_DOCUMENTO)){
				btnNuevo.setDisabled(true);
			} else {
				btnNuevo.setDisabled(false);
			}
			
			
			btnEditar.setDisabled(false);
			btnEliminar.setDisabled(false);
		} else if(valorSeleccion instanceof DocSistArch) {
			DocSistArch entidad = (DocSistArch) valorSeleccion;
			actualizarTablaDatos(entidad);
			if(tiSeleccion != null){
				
				Treechildren hijos = tiSeleccion.getTreechildren();
				if(hijos == null){
					hijos = new Treechildren();
					tiSeleccion.appendChild(hijos);
				}		
				cargarArbol(null, hijos,entidad);
							
				
			}
			btnNuevo.setDisabled(false);
			btnEditar.setDisabled(true);
			btnEliminar.setDisabled(true);
		} else {
			btnNuevo.setDisabled(true);
			btnEditar.setDisabled(true);
			btnEliminar.setDisabled(true);
		}
		
		
		
		
		 
		
	}




	private void actualizarTablaDatos(DocSerieDoc serie) {
		
		Map<String,Object> datos = new HashMap<String, Object>();
		datos.put(ConstantesAdmin.ACCION, ConstantesAdmin.EVENTO_REFRESCAR);
		datos.put(ConstantesAdmin.OBJETO_PADRE, serie);
		if(serie == null){
			datos.put(ConstantesAdmin.NOMBRE_ATRIBUTO_PADRE, "serieDocPadre");
			datos.put(ConstantesAdmin.FILTRAR_PADRE_NULL, true);
		}
		
		
		
		Events.sendEvent(Events.ON_USER, winTablaDatos, datos);
		
	}
	
	private void actualizarTablaDatos(DocSistArch entidad) {
		
		Map<String,Object> datos = new HashMap<String, Object>();
		datos.put(ConstantesAdmin.ACCION, ConstantesAdmin.EVENTO_REFRESCAR);
		datos.put(ConstantesAdmin.OBJETO_PADRE, null);
		
		datos.put(ConstantesAdmin.ARG_WHERE, "serieDocIdn in (select ent.docSerieDoc.serieDocIdn from " + DocSerieSist.class.getName() + " ent where ent.docSistArch.sistArchIdn = " + entidad.getSistArchIdn() + ")");
		
		Events.sendEvent(Events.ON_USER, winTablaDatos, datos);
		
		
	}



	





	public SistemaArchivoNgc getSistemaArchivoNgc() {
		return sistemaArchivoNgc;
	}




	public void setSistemaArchivoNgc(SistemaArchivoNgc sistemaArchivoNgc) {
		this.sistemaArchivoNgc = sistemaArchivoNgc;
	}

	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}
	
	
	
}
