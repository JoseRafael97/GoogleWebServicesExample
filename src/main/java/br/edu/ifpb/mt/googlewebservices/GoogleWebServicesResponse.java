package br.edu.ifpb.mt.googlewebservices;

import java.io.Serializable;
import org.joda.time.DateTime;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

/**
 * Classe que estabelece comunicação com Google webservices.
 * @author rafaelfeitosa
 *
 */
public class GoogleWebServicesResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private GeoApiContext apiContext;

	public GoogleWebServicesResponse() {
		apiContext =  new GeoApiContext().setApiKey("AIzaSyBabGbB2fahenqfzll0JBa9XjiAD6Mdc7k");
	}

	/**
	 * Método que consulta distância entre um ponto de origem e varios outros pontos.
	 * @param origem
	 * @param destinations
	 * @return
	 * @throws Exception
	 */
	public DistanceMatrix consultaDistancia(String origem, String[] destinations) throws Exception {

		DistanceMatrix matrix = DistanceMatrixApi.newRequest(apiContext)
		        .origins(origem)
		        .destinations(destinations)
		        .mode(TravelMode.DRIVING)
		        .language("pt-BR")
		        .avoid(RouteRestriction.TOLLS)
		        .units(Unit.IMPERIAL)
		        .departureTime(new DateTime().plusMinutes(2))
		        .await();
		
		return matrix;
	
	}


}
