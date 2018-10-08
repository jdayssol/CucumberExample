package com.jdayssol.java8;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 	Les Map ne sont pas compatibles avec les Stream. Elles ont quand même le droit à leur lot de nouvelles fonctionalités.
 */
public class MapSample {

	@Test
	public void putIfAbsentMap()
	{
		//putIfAbsent Associe une valeur avec une clef seulement si la clef n’existe pas encore ou si la valeur associée à la clef vaut null.
		// Renvoie vide en cas d'association, sinon renvoi l'ancienne valeur.
		Map<String, String> map = new HashMap<>();

		assert map.putIfAbsent("key", null) == null;
		assert map.get("key") == null;

		assert map.putIfAbsent("key", "value") == null;
		assert map.get("key").equals("value");

		assert map.putIfAbsent("key", "new-value").equals("value");
		assert map.get("key").equals("value");
		
		
	}
	
	@Test
	public void forEachMap()
	{
		Map<String, Integer> map = new HashMap<>();
		map.put("un", 1);
		map.put("deux", 2);
		map.put("trois", 3);
	
		map.forEach((key, value) -> System.out.printf("%s(%d) ", key, value));

	}

	
	@Test
	public void computeMap()
	{
		Map<String, Integer> map = new HashMap<>();
		map.put("un", 1);
		map.put("deux", 2);
		map.put("trois", 3);
		
		//Compute : Si la Function retourne null, le couple est supprimé de la map. 
		assert map.compute("un", (key, value) -> null) == null;
		assert map.compute("deux", (key, value) -> value == null ? null : value + 2) == 4;
		
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("un", 1);
		map2.put("deux", 2);
		map2.put("trois", 3);
		map2.put("quatre", null);
		
		// computeIfAbsent : Tente la création d’une valeur pour une clef si celle-ci n’est associée à aucune valeur (ou si elle est null) en utilisant une Function.
		assert map2.computeIfAbsent("un", key -> null) == 1;
		assert map2.computeIfAbsent("quatre", key -> 4) == 4;
		
		Map<String, Integer> map3 = new HashMap<>();
		map3.put("un", 1);
		map3.put("deux", 2);
		map3.put("trois", 3);
		map3.put("quatre", null);
		
		// computeIfPresent Tente de mettre à jour la valeur associée à une clef si la valeur en question est non-null avec une BiFunction.
		// Si la Function retourne null, le couple est supprimé de la  Map.
		assert map3.computeIfPresent("trois", (key, value) -> value + 5) == 8;
		assert map3.computeIfPresent("un", (key, value) -> null) == null;
		assert map3.computeIfPresent("quatre", (key, value) -> value + 5) == null;
		
		// Remove supprime l’Entry pour la clef en argument si et seulement si la valeur associée à cette clef est égale à celle en argument.
		assert map3.remove("un", 1) == true;
		assert map3.remove("deux", 3) == false;

	}
	
	@Test
	public void mergeMap(){
		/*
		 * Associe la valeur non-null en argument à une clef 
		 * si cette clef n’est pas déjà associée à une valeur (ou si la valeur est null),
		 * sinon applique la BiFunction sur la valeur existante.
		 */
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "un");
		map.put(2, "deux");

		assert map.merge(1, " exemple", String::concat).equals("un exemple");
		assert map.merge(2, "exemples", (key, value) -> null) == null;
	}
	
	@Test
	public void replaceMap(){
		/*
		 * Remplace une valeur associée à une clef si cette valeur est égale à celle en argument.
		 */
		Map<String, Integer> map = new HashMap<>();
		map.put("un", 1);
		map.put("deux", 2);

		assert map.replace("un", 1, 5) == true;
		assert map.replace("deux", 3, 5) == false;
		
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("un", 1);
		map2.put("deux", 2);

		map2.replaceAll((key, value) -> "un".equals(key) ? value + 1 : value + 2);

		assert map2.get("un") == 2;
		assert map2.get("deux") == 4;
	}
}
