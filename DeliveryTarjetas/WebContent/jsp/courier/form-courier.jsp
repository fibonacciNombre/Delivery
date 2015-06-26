<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div class="row">
     
    <div class="col-md-6">
		<div class="form-group" id="codbbva-div">
		    <label for="codbbva" class="col-md-12 control-label required">Código Bbva </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id=codbbva name="codbbva" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
        
		<div class="form-group" id="tipdocumento-div">
		    <label for="idptipodocumento" class="col-md-12 control-label required">Tipo de documento </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idptipodocumento" name="idptipodocumento">                        	
		   		</select>
		        <div class="result"></div>
		    </div>
		</div>
        
		<div class="form-group" id="nrodocumento-div">
		    <label for="nrodocumentocou" class="col-md-12 control-label required">Nro. documento </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="nrodocumentocou"  name="nrodocumentocou" maxlength="11">
		        <div class="result"></div>
		    </div>
		</div>
        
		<div class="form-group" id="rznsocial-div">
		    <label for="rznsocial" class="col-md-12 control-label required">Razón social </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
        
		<div class="form-group" id="direccion-div">
		    <label for="direccion" class="col-md-12 control-label required">Dirección completa </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control" id="direccion" name="direccion" maxlength="200">
		        <div class="result"></div>
		    </div>
		</div>
	</div>

 	<div class="col-md-6">            
		<div class="form-group" id="telfmovil-div">
		    <label for="telfmovil" class="col-md-12 control-label required">Teléfono móvil </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control phone-group" id="telfmovil" name="telfmovil" maxlength="9">
		        <div class="result"></div>
		    </div>
		</div>
             
		<div class="form-group" id="telffijo-div">
		    <label for="telffijo" class="col-md-12 control-label required">Teléfono fijo </label>
		    <div class="col-md-12">
		        <input type="text" class="form-control phone-group" id="telffijo"  name="telffijo" maxlength="9">
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
             
             
		<div class="form-group" id="idpestado-div">
		    <label for="idpestado" class="col-md-12 control-label required">Estado </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idpestado" name="idpestado">                        	
		        </select>
		        <div class="result"></div>
		    </div>
		</div>
	</div>
         
	<div class="col-md-12">
		<div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
			<label for="*" class="col-md-12 control-label">
				<span>(<label class="required"></label>) Campos obligatorios</span>
			</label>                	
		</div>                
	</div>
            
</div>