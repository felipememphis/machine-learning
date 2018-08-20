package com.examplo.recomendador;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;

public class Avaliador {

	public static void main(String[] args) throws IOException, TasteException {
		
		//usar como base um único número para random
		RandomUtils.useTestSeed();
		
		//usuário, item, nota
		File file = new File("src/main/resources/dados.csv");
		DataModel model = new FileDataModel(file);
		
		AverageAbsoluteDifferenceRecommenderEvaluator avaliador = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new RecomendadorProdutosBuilder();
		double erro = avaliador.evaluate(builder, null, model, 0.9, 1.0);
		
		//grau de erro para cima e para baixo
		System.out.println(erro);
	}
}
