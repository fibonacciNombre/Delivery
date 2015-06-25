package bbva.delivery.tarjetas.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

@SuppressWarnings("rawtypes")
public class CargaEntregaTarjeta {
	
	private BigDecimal idecarga;
	private String numerotarjeta;
	private String ultimosdigitos;
	private String dnicliente;
	private String nomcliente;
	private String loccliente;
	private String dircliente;
	private String distcliente;
	private String tipoprod;
	private String subtipoprod;
	private BigDecimal montocred;
	private String ubientrega;
	private BigDecimal latoficina;
	private BigDecimal lngoficina;
	private BigDecimal latdomicilio;
	private BigDecimal lngdomicilio;
	private String fecentregaprog;
	private String horentregaprog;
	private String direntrega;
	private String distentrega;
	private String proventrega;
	private String depentrega;
	private String courierasoc;
	private String dnicourier;
	private BigDecimal grupocarga;

	private List cursor;
	
	public CargaEntregaTarjeta(){
		
	}
 
	public CargaEntregaTarjeta(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}
	
	public String getNumerotarjeta() {
		return numerotarjeta;
	}

	public void setNumerotarjeta(String numerotarjeta) {
		this.numerotarjeta = numerotarjeta;
	}

	public String getUltimosdigitos() {
		return ultimosdigitos;
	}

	public void setUltimosdigitos(String ultimosdigitos) {
		this.ultimosdigitos = ultimosdigitos;
	}

	public String getDnicliente() {
		return dnicliente;
	}

	public void setDnicliente(String dnicliente) {
		this.dnicliente = dnicliente;
	}

	public String getNomcliente() {
		return nomcliente;
	}

	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}

	public String getLoccliente() {
		return loccliente;
	}

	public void setLoccliente(String loccliente) {
		this.loccliente = loccliente;
	}

	public String getDircliente() {
		return dircliente;
	}

	public void setDircliente(String dircliente) {
		this.dircliente = dircliente;
	}

	public String getDistcliente() {
		return distcliente;
	}

	public void setDistcliente(String distcliente) {
		this.distcliente = distcliente;
	}

	public String getTipoprod() {
		return tipoprod;
	}

	public void setTipoprod(String tipoprod) {
		this.tipoprod = tipoprod;
	}

	public String getSubtipoprod() {
		return subtipoprod;
	}

	public void setSubtipoprod(String subtipoprod) {
		this.subtipoprod = subtipoprod;
	}

	public BigDecimal getMontocred() {
		return montocred;
	}

	public void setMontocred(BigDecimal montocred) {
		this.montocred = montocred;
	}

	public String getUbientrega() {
		return ubientrega;
	}

	public void setUbientrega(String ubientrega) {
		this.ubientrega = ubientrega;
	}

	 
	public BigDecimal getLatoficina() {
		return latoficina;
	}

	public void setLatoficina(BigDecimal latoficina) {
		this.latoficina = latoficina;
	}

	public BigDecimal getLngoficina() {
		return lngoficina;
	}

	public void setLngoficina(BigDecimal lngoficina) {
		this.lngoficina = lngoficina;
	}

	public BigDecimal getLatdomicilio() {
		return latdomicilio;
	}

	public void setLatdomicilio(BigDecimal latdomicilio) {
		this.latdomicilio = latdomicilio;
	}

	public BigDecimal getLngdomicilio() {
		return lngdomicilio;
	}

	public void setLngdomicilio(BigDecimal lngdomicilio) {
		this.lngdomicilio = lngdomicilio;
	}

	public String getFecentregaprog() {
		return fecentregaprog;
	}

	public void setFecentregaprog(String fecentregaprog) {
		this.fecentregaprog = fecentregaprog;
	}

	public String getHorentregaprog() {
		return horentregaprog;
	}

	public void setHorentregaprog(String horentregaprog) {
		this.horentregaprog = horentregaprog;
	}

	public String getDirentrega() {
		return direntrega;
	}

	public void setDirentrega(String direntrega) {
		this.direntrega = direntrega;
	}

	public String getDistentrega() {
		return distentrega;
	}

	public void setDistentrega(String distentrega) {
		this.distentrega = distentrega;
	}

	public String getProventrega() {
		return proventrega;
	}

	public void setProventrega(String proventrega) {
		this.proventrega = proventrega;
	}

	public String getDepentrega() {
		return depentrega;
	}

	public void setDepentrega(String depentrega) {
		this.depentrega = depentrega;
	}

	public String getCourierasoc() {
		return courierasoc;
	}

	public void setCourierasoc(String courierasoc) {
		this.courierasoc = courierasoc;
	}

	public String getDnicourier() {
		return dnicourier;
	}

	public void setDnicourier(String dnicourier) {
		this.dnicourier = dnicourier;
	}
	public BigDecimal getGrupocarga() {
		return grupocarga;
	}

	public void setGrupocarga(BigDecimal grupocarga) {
		this.grupocarga = grupocarga;
	}

	public BigDecimal getIdecarga() {
		return idecarga;
	}

	public void setIdecarga(BigDecimal idecarga) {
		this.idecarga = idecarga;
	} 

	public List getCursor() {
		return cursor;
	}
 
	public void setCursor(List cursor) {
		this.cursor = cursor;
	}
 
	@Override
	public String toString() {
		return "CargaEntregaTarjeta [numerotarjeta=" + numerotarjeta
				+ ", ultimosdigitos=" + ultimosdigitos + ", dnicliente="
				+ dnicliente + ", nomcliente=" + nomcliente + ", loccliente="
				+ loccliente + ", dircliente=" + dircliente + ", distcliente="
				+ distcliente + ", tipoprod=" + tipoprod + ", subtipoprod="
				+ subtipoprod + ", montocred=" + montocred + ", ubientrega="
				+ ubientrega + ", latoficina=" + latoficina + ", lngoficina="
				+ lngoficina + ", latdomicilio=" + latdomicilio
				+ ", lngdomicilio=" + lngdomicilio + ", fecentregaprog="
				+ fecentregaprog + ", horentregaprog=" + horentregaprog
				+ ", direntrega=" + direntrega + ", distentrega=" + distentrega
				+ ", proventrega=" + proventrega + ", depentrega=" + depentrega
				+ ", courierasoc=" + courierasoc + ", dnicourier=" + dnicourier
				+ "]";
	} 
	
}
