<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div class="row">
        
	<div class="col-md-6">
		<div class="form-group" id="tipdocumento-div">
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
		<div class="form-group" id="codbbva-div">
		    <label for=idcourier" class="col-md-12 control-label required">Courier </label>
		    <div class="col-md-12">
		        <select class="form-control" id="idcourier" name="idcourier">                     	
		   		</select>
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
                
		<div class="form-group" id="estado-div">
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