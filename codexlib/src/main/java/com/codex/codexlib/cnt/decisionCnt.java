package com.codex.codexlib.cnt;


import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Window;

import com.codex.codexlib.ngc.SistemaArchivoNgc;
import com.codex.codexlib.utl.ConstantesData3000;
import com.codex.codexplt.bd.PltUsuario;
import com.codex.codexplt.exc.PltException;
import com.codex.codexplt.ngc.PlataformaNgc;
import com.codex.codexplt.ngc.UsuarioNgc;
import com.codex.codexplt.utl.ConstantesAdmin;
import com.codex.codexplt.utl.WindowComposer;

public class decisionCnt extends WindowComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Window winTomarDecision;
	
	private PlataformaNgc plataformaNgc;
	
	private Radio chxDependencia;
	
	private Radio chxDirectorio;
	
	private String desicion;
	
	
	 
	@Override
	public void doAfterCompose(Window winTomarDecision) throws Exception{
		super.doAfterCompose(winTomarDecision);
	}
	
	public void onCreate$winTomarDecision(Event event) throws Exception{
//		chxDependencia.setChecked(true);
	}
	
	
	public void onClick$btnAceptar(Event event) throws Exception{
	if(chxDependencia.isChecked()){
		desicion = ConstantesData3000.SISTEMA_ARCHIVO_DEPENDENCIA;
		Events.sendEvent(Events.ON_CLOSE,winTomarDecision,desicion);
	}else if(chxDirectorio.isChecked()){
		desicion = ConstantesData3000.SISTEMA_ARCHIVO_DIRECTORIO;
		Events.sendEvent(Events.ON_CLOSE,winTomarDecision,desicion);
	}else{
		Events.sendEvent(Events.ON_CLOSE,winTomarDecision,"NO");
	}
	
	}
	
    public void onClick$btnCancelar(Event event) throws Exception{
    	Events.sendEvent(Events.ON_CLOSE,winTomarDecision,"NO");
	}

	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}
    
}
