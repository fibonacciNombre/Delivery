<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div class="row">
	<div class="col-md-12">

		<div class="form-group" id="idperfil-div">
		    <label for="idperfil" class="col-sm-5 col-sm-offset-1 control-label required">Perfil </label>
		    <div class="col-sm-5">
		    	<select class="form-control" id="cboperfil" name="cboperfil">                        	
		   		</select>
		   		<input type="hidden" class="form-control" id="idperfil" name="idperfil" >
		    </div>
		</div>
		
		<div class="form-group" id="codusuario-div">
		    <label for="codusuario" class="col-sm-5 col-sm-offset-1 control-label required">Código de usuario </label>
		    <div class="col-sm-5">
		        <input type="text" class="form-control" id="codusuario" name="codusuario" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
        
        <div class="form-group" id="contrasena-div">
		    <label for="contrasena" class="col-sm-5 col-sm-offset-1 control-label required">Contraseña </label>
		    <div class="col-sm-5">
		        <input type="password" class="form-control" id="contrasena" name="contrasena" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="estado-div">
		    <label for="idpestado" class="col-sm-5 col-sm-offset-1 control-label required">Estado </label>
		    <div class="col-sm-5">
		        <select class="form-control" id="idpestado" name="idpestado">                        	
		        </select>
		        <div class="result"></div>
		    </div>
		</div>
            
		<div class="form-group" id="comentarios-div">
		    <label for="comentarios" class="col-sm-5 col-sm-offset-1 control-label">Comentarios </label>
		    <div class="col-sm-5">
		    	<textarea class="form-control" id="comentario" name="comentario" maxlength="300"  wrap="hard" style="min-width: 100%; max-width: 100%; min-height: 50px; max-height: 50px;" ></textarea>
		    </div>
		</div>
     	
     	<div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
			<label for="*" class="col-sm-5 col-sm-offset-1 control-label">
				<span>(<label class="required"></label>) Campos obligatorios</span>
			</label>                	
		</div>
             
	</div>           
</div>