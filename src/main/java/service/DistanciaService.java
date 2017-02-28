package service;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.DistanceMatrix;

import br.edu.ifpb.mt.exceptions.NaoExisteMensageiro;
import br.edu.ifpb.mt.googlewebservices.GoogleWebServicesResponse;
import br.edu.ifpb.mt.model.Endereco;
import br.edu.ifpb.mt.model.Mensageiro;

public class DistanciaService {

	private List<Mensageiro> mensageiros;
	
	//@Autowired
	private GoogleWebServicesResponse googleWebServicesResponse = new GoogleWebServicesResponse();


	public DistanciaService() {
		mensageiros = new ArrayList<>();
		addMensageiros();
	}

	public List<Mensageiro> filtraMensageiroPorArea(Endereco enderecoDoador) throws NaoExisteMensageiro {
		List<Mensageiro> mensageirosSelecionados = new ArrayList<>();
		for (Mensageiro m : mensageiros) {
			if ((m.getEndereco().getBairro().equals(enderecoDoador.getBairro())
					&& m.getEndereco().getCidade().equals(enderecoDoador.getCidade()))
					&& m.getEndereco().getEstado().equals(enderecoDoador.getEstado())) {

				mensageirosSelecionados.add(m);
			}

		}

		if (mensageirosSelecionados.isEmpty()) {
			for (Mensageiro m : mensageiros) {
				if ((m.getEndereco().getCidade().equals(enderecoDoador.getCidade()))
						&& m.getEndereco().getEstado().equals(enderecoDoador.getEstado())) {

					mensageirosSelecionados.add(m);
				}

			}
		} else {
			return mensageirosSelecionados;
		}

		throw new NaoExisteMensageiro("Não existe nenhum mensageiro nessa área.");

	}

	public Mensageiro verificaMensageiroMaisProximo(Endereco endereco) throws Exception {
		List<Mensageiro> mensageirosSelecionados = filtraMensageiroPorArea(endereco);

		Mensageiro mensageiroMaisProximo = null;
		long menorDistacia = 0;
		
		if (mensageirosSelecionados != null) {
			String[] mensageiro = new String[mensageirosSelecionados.size()];

			for (int i = 0; i < mensageirosSelecionados.size(); i++) {
				mensageiro[i] = mensageirosSelecionados.get(i).getEndereco().getRua()+
						","+mensageirosSelecionados.get(i).getEndereco().getBairro()+","+
						mensageirosSelecionados.get(i).getEndereco().getCidade()+","+
						mensageirosSelecionados.get(i).getEndereco().getEstado();
			}
			
			DistanceMatrix matrix = googleWebServicesResponse.consultaDistancia(endereco.getRua()+","+endereco.getBairro()
			+","+endereco.getCidade()+","+endereco.getEstado(), mensageiro);
			
			for(int i = 0; i<=matrix.rows.length; i++){
				System.out.println("Rua:"+matrix.rows[0].elements[i]+", Distancia: "+matrix.rows[0].elements[i].distance.inMeters);
				
				if(menorDistacia > matrix.rows[0].elements[i].distance.inMeters || menorDistacia == 0){
					menorDistacia = matrix.rows[0].elements[i].distance.inMeters;
					mensageiroMaisProximo = mensageirosSelecionados.get(i);
				
				}
			}
			
			
		}
				
		return mensageiroMaisProximo;
	}

	private void addMensageiros() {
		Endereco end1 = new Endereco("Leopoldino José Da Silva", "Centro", "Monteiro", "Paraíba");
		mensageiros.add(new Mensageiro(end1));

		Endereco end2 = new Endereco("Ac Rodovia PB 264", "Serrote", "Monteiro", "Paraíba");
		mensageiros.add(new Mensageiro(end2));

		Endereco end3 = new Endereco("R. Abelardo Pereira dos Santos", "Centro", "Monteiro", "Paraíba");
		mensageiros.add(new Mensageiro(end3));

	}
	
	public static void main(String[] args) {
		DistanciaService distanciaService = new DistanciaService();
		try {
			Mensageiro msg = distanciaService.verificaMensageiroMaisProximo(new Endereco("Praça João Pessoa", "Centro", "Monteiro", "Paraíba"));
		
			System.out.println(msg.getEndereco().getRua());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
