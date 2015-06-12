package bbva.delivery.tarjetas.util;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import bbva.delivery.tarjetas.perfil.bean.UsuarioLDAP;

public class MainTest {

	private static String[] userAttributes = { "cn", "givenname", "uid", "sn",
			"documentNumber", "securityQuestion", "securityAnswer", "mail" };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DirContext ctxDetail 	= null;
		String preffixName 		= null;
		String suffixName 		= null;
		UsuarioLDAP usuarioLdap = null;

		try {

			System.out.println("***************************************************");
			System.out.println("BUSCANDO AL USUARIO EN EL LDAP - INGRESO COMO ADMIN");
			System.out.println("***************************************************");

			preffixName = "uid=";

			suffixName = "ou=personas,o=rimac";

			// OBTENER CONEXION ADMIN			
			Hashtable<String, Object> env 	= null;
			String ctxFactory 				= null;
			String ldapAuthentication 		= null;
			String ldapAdmin				= null;
			String ldapPassword				= null;
			
			String ldapHost 	= "ldap://172.25.32.56:389";			
			ctxFactory 			= "com.sun.jndi.ldap.LdapCtxFactory";			
			ldapAuthentication 	= "simple";
			ldapAdmin			= "cn=root";			
			ldapPassword 		= "rimac..1479";			
			env 				= new Hashtable<String, Object>();
			
			System.out.println("LDAP		: " + ldapHost	);
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
			env.put(Context.PROVIDER_URL, ldapHost);
			env.put(Context.SECURITY_AUTHENTICATION, ldapAuthentication);
			env.put(Context.SECURITY_PRINCIPAL, ldapAdmin);
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
			
			ctxDetail = new InitialDirContext(env);
			
			SearchControls controls = new SearchControls();
			
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setReturningAttributes(userAttributes);

			String searchFilter = "("+preffixName+"*"+")";
			 
			NamingEnumeration<SearchResult> answer = ctxDetail.search(suffixName, searchFilter, controls);
			
			int count = 0;
			List<UsuarioLDAP> lstUsrLdap	= new ArrayList<UsuarioLDAP>(); 
			while (answer.hasMore()) {
				
				count++;
				usuarioLdap			= new UsuarioLDAP();
				
				Attributes attr 	= answer.next().getAttributes();
				Attribute cn 		= attr.get("cn");
				Attribute givenname = attr.get("givenname");
				Attribute uid 		= attr.get("uid");
				Attribute sn 		= attr.get("sn");
				Attribute docNumber	= attr.get("documentnumber");
				Attribute securQues = attr.get("securityquestion");
				Attribute securAnsw = attr.get("securityanswer");
				Attribute mail		= attr.get("mail");
				
				usuarioLdap.setValido(true);
				
				if (cn!=null) 
					usuarioLdap.setCn((String) attr.get("cn").get());
				
				if (givenname!=null) 
					usuarioLdap.setGivenName((String) attr.get("givenname").get());
				
				if (uid!=null) 
					usuarioLdap.setUid((String) attr.get("uid").get());
				
				if (sn!=null)
					usuarioLdap.setSn((String) attr.get("sn").get());
				
				if (docNumber!=null)
					usuarioLdap.setDocumentNumber((String) attr.get("documentnumber").get());
				
				if (securQues!=null)
					usuarioLdap.setSecurQues((String) attr.get("securityQuestion").get());
				
				if (securAnsw!=null)
					usuarioLdap.setSecurAnsw((String) attr.get("securityAnswer").get());
				
				if(mail!=null)
					usuarioLdap.setMail((String) attr.get("mail").get());
				
				lstUsrLdap.add(usuarioLdap);
				System.out.println("answer.hasMore()	: " + count + "				" + usuarioLdap.getUid() + "		"  + usuarioLdap.getMail()); 
			}
			System.out.println("SIZE	:" + lstUsrLdap.size());

		} catch (Exception e) {

			System.out.println("ERROR OBTENER DATA USUARIO.");

			e.printStackTrace();
		}
	}

}