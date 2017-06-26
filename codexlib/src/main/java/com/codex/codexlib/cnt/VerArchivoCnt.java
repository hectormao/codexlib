package com.codex.codexlib.cnt;


import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import com.codex.codexlib.bd.DocArchivoVersion;
import com.codex.codexplt.utl.ConstantesAdmin;
import com.codex.codexplt.utl.WindowComposer;

public class VerArchivoCnt extends WindowComposer {
	
	private Window winVerArchivo;
	private Iframe frmContenido;
	
	private DocArchivoVersion version;
	
	@Override
	public void doAfterCompose(Window winVerArchivo) throws Exception{		
		super.doAfterCompose(winVerArchivo);
		version = (DocArchivoVersion) argumentos.get(ConstantesAdmin.ARG_SELECCION);
		if(version != null){
			Path path = Paths.get(version.getArchVersRuta());
			String mimeType = Files.probeContentType(path);
			String url = null;
			if(mimeType == null || (mimeType != null && mimeType.toLowerCase().startsWith("image/")) ){
				url = new StringBuilder("GetArchivo?idVersion=").append(version.getArchVersIdn()).toString();
			} else {
				url = "https://docs.google.com/gview?url=" + Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + ":" + Executions.getCurrent().getServerPort() + Executions.getCurrent().getContextPath() + "/GetArchivo?idVersion=" + version.getArchVersIdn() + "&embedded=true";				
			}
			
			frmContenido.setSrc(url);
			
			
			
		}
		
	}

}
