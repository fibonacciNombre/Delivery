<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div class="row">	 
           
	<div class="col-md-6">
		<div class="form-group" id="idperfil-div">
	        <label for="idperfil" class="col-md-12 control-label required">Perfil </label>
	        <div class="col-md-12">
	        	<select class="form-control" id="idperfil" name="idperfil">                       	
	       		</select>
	        </div>
	    </div>
            
		<div class="form-group" id="idcourier-div">
		    <label for=idcourier" class="col-md-12 control-label">Courier </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idcourier" name="idcourier">                        	
		   		</select>
		        <div class="result"></div>
		    </div>
		</div>
       
		<div class="form-group" id="idptipodocumento-div">
		    <label for="idptipodocumento" class="col-md-12 control-label required">Tipo de documento </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idptipodocumento" name="idptipodocumento">                      	
		   		</select>
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="nrodocumento-div">
		    <label for="nrodocumento" class="col-md-12 control-label required">Nro. documento </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">
		        <div class="result"></div>
		    </div>
		</div>
             
		<div class="form-group" id="nombres-div">
		    <label for="nombres" class="col-md-12 control-label required">Nombres </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="nombres" name="nombres" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="apepaterno-div">
		    <label for="apepaterno" class="col-md-12 control-label required">Apellido paterno </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="apepaterno" name="apepaterno" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="apematerno-div">
		    <label for="apematerno" class="col-md-12 control-label required">Apellido materno </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="apematerno" name="apematerno" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
	</div>
     
   	<div class="col-md-6">
		<div class="form-group" id="codusuario-div">
		    <label for="codusuario" class="col-md-12  control-label required">Código de usuario </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="codusuario" name="codusuario" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="contrasena-div">
		    <label for="contrasena" class="col-md-12 control-label required">Contraseña </label>
		    <div class="col-md-12">
		        <input type="password" class="form-control" id="contrasena" name="contrasena" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		      
		<div class="form-group" id="telfmovil-div">
		    <label for="telfmovil" class="col-md-12 control-label required">Teléfono móvil </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="telfmovil" name="telfmovil" maxlength="9">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="correo-div">
		    <label for="correo" class="col-md-12 control-label required">Correo electrónico </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="correo"  name="correo" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="indrnvcontrasena-div">
		    <label for="indrnvcontrasena" class="col-md-12 control-label required">Renovar Contraseña </label>
		    <div class="col-md-12">
		        <select class="form-control" id="indrnvcontrasena" name="indrnvcontrasena">                      	
		   		</select>
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="estado-div">
		    <label for="idpestado" class="col-md-12 control-label required">Estado </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idpestado" name="idpestado">              	
		        </select>
		        <div class="result"></div>
		    </div>
		</div>
		
		<div class="form-group" id="comentarios-div">
		    <label for="comentario" class="col-md-12 control-label">Comentarios </label>
		    <div class="col-md-12">
		    	<textarea class="form-control" id="comentario" name="comentario" maxlength="300"  wrap="hard" style="min-width: 100%; max-width: 100%; min-height: 50px; max-height: 50px;" ></textarea>
			</div>
		</div>
   	</div>
    	
	<div class="col-md-12">
		<div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
			<label for="*" class="col-md-6  control-label">
				<span>(<label class="required"></label>) Campos obligatorios</span>
			</label>                	
		</div>	                
	</div>
	            
</div>