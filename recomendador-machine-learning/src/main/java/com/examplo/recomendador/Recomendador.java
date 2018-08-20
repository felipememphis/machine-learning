package com.examplo.recomendador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 *
 * Recomendador baseado nas preferências dos usuários
 * Algoritmo User base similarity
 */
public class Recomendador {

	public static void main(String[] args) throws IOException, TasteException {

		File file = new File("src/main/resources/dados.csv");
		
		DataModel model = new FileDataModel(file);
		
		 //escolhe o algoritmo de similaridade no grau de similaridade de entre os usuários
		 UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		 
		 //definir o grau de próximidade entre eles
		 UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		 
		 //criação do recomendador passeado nos parâmetros definidos anteriormente
		 UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		 
		 List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		 for (RecommendedItem recommendation : recommendations) {
		   System.out.println(recommendation);
		 }
	}

}
