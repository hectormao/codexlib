package com.codex.codexlib.evt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.zkoss.zul.Filedownload;

import com.codex.codexlib.bd.DocArchivoVersion;
import com.codex.codexlib.ngc.SistemaArchivoNgc;
import com.codex.codexlib.utl.ConstantesData3000;
import com.codex.codexplt.exc.PltException;
import com.codex.codexplt.utl.CampoEvento;
import com.codex.codexplt.utl.ConstantesAdmin;

public class DescargarArchivoEvt extends CampoEvento{

	@Override
	public void ejecutar() throws Exception{
		DocArchivoVersion version = (DocArchivoVersion) argumentos.get(ConstantesAdmin.ARG_SELECCION);
		if(version == null){
			throw new PltException(ConstantesAdmin.ERR0007);
		}
		
		Path ruta = Paths.get(version.getArchVersRuta());
		
		if(! Files.exists(ruta)){
			throw new PltException(ConstantesData3000.ERR1003);
		}
		
		byte[] data = Files.readAllBytes(ruta);
		
		
		
		WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();
		SistemaArchivoNgc sistemaArchivoNgc = (SistemaArchivoNgc) wc.getBean(ConstantesData3000.BEAN_SISTEMA);
		
		
		String checksum = sistemaArchivoNgc.calcularChecksum(data);
		
		if(! checksum.equals(version.getArchVersChecksum())){
			throw new PltException(ConstantesData3000.ERR1004);
		}
		
		Filedownload.save(data, null, ruta.getFileName().toString());
		
	}
	
		
	
}
